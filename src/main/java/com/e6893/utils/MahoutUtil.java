package com.e6893.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.mahout.clustering.AbstractCluster;
import org.apache.mahout.clustering.Cluster;
import org.apache.mahout.clustering.canopy.CanopyDriver;
import org.apache.mahout.clustering.classify.WeightedPropertyVectorWritable;
import org.apache.mahout.clustering.iterator.ClusterWritable;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.apache.mahout.common.HadoopUtil;
import org.apache.mahout.common.distance.ManhattanDistanceMeasure;
import org.apache.mahout.common.iterator.sequencefile.PathFilters;
import org.apache.mahout.common.iterator.sequencefile.PathType;
import org.apache.mahout.common.iterator.sequencefile.SequenceFileDirValueIterable;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.NamedVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.json.JSONArray;
import org.json.JSONObject;

import com.e6893.model.ClusterCenter;
import com.e6893.model.Feature;

/**
 * This class interacts with the Mahout library to perform clustering and
 * to read/write from HDFS * 
 * 
 * @author Jairo Pava
 *
 */
public class MahoutUtil {
	
	/**
	 * Location of webapp folder
	 */
	private static final String WRITER_BASE = "/media/bigdata/Second/workspace/StudentTracker/src/main/webapp/";
    
	/**
	 * Location of ECLS-K data
	 */
	private static final String BASE_PATH = "/media/bigdata/Second/ICPSR_28023/DS0001";
    
	/**
	 * Location of cluster points
	 */
	private static final String POINTS_PATH = BASE_PATH + "/points";
    
	/**
	 * Location of cluster points files
	 */
	private static final String POINTS_FILE_PATH = POINTS_PATH + "/pointsFile"; 

	/**
	 * Location of clusters
	 */
	private static final String CLUSTERS_PATH = BASE_PATH + "/clusters";
    
	/**
	 * Clustering algorithm output folder
	 */
	private static final String OUTPUT_PATH = BASE_PATH + "/output";
    
    /**
     * ECLS-K CSV
     */
	private static final String CSV_PATH = BASE_PATH + "/c1c7_rms_assesments.csv";
    
	/**
	 * T1 for canopy clustering algorithm
	 */
    private static final double T1 = 200.0;
    
    /**
     * T2 for canopy clustering algorithm
     */
    private static final double T2 = 150.0;
    
    /**
     * Name of student selected in the web application
     */
    private static String studentId = "";
    
    /**
     * Features for student selected in the web application
     */
    private static double[] studentGrades;
    
    /**
     * Maps between students Ids and their vectors
     */
    private Map<String, Vector> vectorMap;
    
    /**
     * Sorted list of vetors
     */
    private List<Vector> vectors;
    
    /**
     * Sorted list of cluster centers by their distance to the selected student
     */
    private ArrayList<ClusterCenter> distanceFromCenters;
    
    /**
     * Map between student Id and their features
     */
    private Map<String, ArrayList<Double>> studentData;
    
    /**
     * Initializes this class
     */
	@PostConstruct
	public void init() {
		Logger.log("--> MahoutUtil.init");
		
        // Create input directories for data
        final File pointsDir = new File(POINTS_PATH);
        if (!pointsDir.exists()) {
            pointsDir.mkdir();
        }
        
        try {
	        // read the point values and generate vectors from input data
        	vectorMap = new HashMap<String, Vector>();
        	studentData = new HashMap<String, ArrayList<Double>>();
            vectors = readVectors();
        } catch(Exception e) {
        	Logger.log("Exception caught:");
        	Logger.log(e.toString());
        	e.printStackTrace();
        }
        
        Logger.log("<-- MahoutUtil.init");
	}
	
	/**
	 * Calculates clusters in ECLS-K data and determines how far each cluster 
	 * is from the given student
	 * @param studentId Id of the given student
	 * @param studentGrades Grades for the given student
	 */
	public void calculateClusters(String studentId, double[] studentGrades) {
		this.studentId = studentId;
		this.studentGrades = studentGrades;
		
		final Configuration configuration = new Configuration();
		
		// add vector that were are interested in
        Vector studentVector = new NamedVector(new DenseVector(studentGrades), studentId);
        vectors.add(studentVector);
 
        try {
	        // Write data to sequence hadoop sequence files
	        writePointsToFile(configuration, vectors);
	 
	        // Write initial centers for clusters
	        List<Cluster> clusters = writeClusterInitialCenters(configuration, vectors);
	        
	        // Calculate distance from cluster centers to point that we are interested in
	        distanceFromCenters = calculateDistanceFromCenter(studentVector, clusters);
	 
	        // Run K-means algorithm
	        final Path inputPath = new Path(POINTS_PATH);
	        final Path clustersPath = new Path(CLUSTERS_PATH + "/clusters-0-final");
	        final Path outputPath = new Path(OUTPUT_PATH);
	        HadoopUtil.delete(configuration, outputPath);
	 
	        KMeansDriver.run(configuration, inputPath, clustersPath, outputPath, 0.001, 10, true, 0, false);
        } catch(Exception e) {
        	Logger.log("Exception caught:");
        	Logger.log(e.toString());
        	e.printStackTrace();
        }
	}
	
	/**
	 * Calculates the distance between the given vector and the given cluster centers
	 * @param vector
	 * @param clusters
	 * @return
	 */
	private ArrayList<ClusterCenter> calculateDistanceFromCenter(Vector vector, List<Cluster> clusters) {
    	ManhattanDistanceMeasure mdm = new ManhattanDistanceMeasure();
    	
    	HashMap<String,Double> map = new HashMap<String,Double>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<String,Double> sortedMap = new TreeMap<String,Double>(bvc);
        ArrayList<ClusterCenter> cList = new ArrayList<ClusterCenter>(); 
        HashMap<String, Cluster> clustersMap = new HashMap<String, Cluster>();
    	
    	Logger.log("Calculating cluster centers");
    	
    	for(Cluster cluster : clusters) {
    		double distance = mdm.distance(vector, cluster.getCenter());
    		map.put(cluster.getId() + "", distance);
    		clustersMap.put(cluster.getId() + "", cluster);
    		Logger.log("Cluster:" + cluster.getId() + 
    				" Distance:" + distance + 
    				" Num Observations:" + cluster.getNumObservations() + 
    				" Total Observations:" + cluster.getTotalObservations());
    	}
    	
    	sortedMap.putAll(map);
    	
    	for(Map.Entry<String, Double> entry : sortedMap.entrySet()) {
        	String clusterId = entry.getKey();
        	Double distance = entry.getValue();
        	Cluster cluster = clustersMap.get(clusterId);
        	
        	ClusterCenter cc = new ClusterCenter();
        	cc.setDistanceToStudent(distance);
        	cc.setId(clusterId);
        	cc.setNumObservations(cluster.getNumObservations());
        	
        	cList.add(cc);
        }
    	
    	return cList;
    }
	
	/**
	 * Writes the vectors to HDFS
	 * 
	 * @param configuration
	 * @param points
	 * @throws IOException
	 */
	private void writePointsToFile(final Configuration configuration, final List<Vector> points)
	        throws IOException {
	 
	        final Path path = new Path(POINTS_FILE_PATH);
	        HadoopUtil.delete(configuration, path);
	 
	        final SequenceFile.Writer writer =
	            SequenceFile.createWriter(
	                configuration,
	                SequenceFile.Writer.file(path),
	                SequenceFile.Writer.keyClass(IntWritable.class),
	                SequenceFile.Writer.valueClass(VectorWritable.class));
	 
	        int recNum = 0;
	        final VectorWritable vec = new VectorWritable();
	 
	        for (final Vector point : points) {
	            vec.set(point);
	            writer.append(new IntWritable(recNum++), vec);
	        }
	 
	        writer.close();
	    }
	 
	/**
	 * Writes to the clusters centers to the file system
	 * 
	 * @param configuration
	 * @param points
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
    private List<Cluster> writeClusterInitialCenters(final Configuration configuration, final List<Vector> points)
        throws ClassNotFoundException, IOException, InterruptedException {
    	final Path inputPath = new Path(POINTS_FILE_PATH);
    	final Path outputPath = new Path(CLUSTERS_PATH);
    	Configuration conf = new Configuration();
    	
    	HadoopUtil.delete(conf, outputPath);
    	   	
    	//CanopyDriver.run(inputPath, outputPath, new ManhattanDistanceMeasure(), T1, T2, false, 0, false);
    	CanopyDriver.buildClusters(conf, inputPath, outputPath, new ManhattanDistanceMeasure(), T1, T2, 0, true);
    	
    	return loadClustersWritable(outputPath);
    }

    /**
     * Reads clusters from given path
     * 
     * @param clustersIn
     * @return
     */
    private List<Cluster> readClustersWritable(Path clustersIn) {
    	List<Cluster> clusters = new LinkedList<Cluster>();
    	Configuration conf = new Configuration();
    	for (ClusterWritable value : new SequenceFileDirValueIterable<ClusterWritable>(clustersIn, PathType.LIST,
    			PathFilters.logsCRCFilter(), conf)) {
    		Cluster cluster = value.getValue();
    		clusters.add(cluster);
    		Logger.log(
    				"Reading Cluster:" + cluster.getId() + 
    				" center:" + AbstractCluster.formatVector(cluster.getCenter(), null) +
    				" numPoints:" + cluster.getNumObservations() +
    				" radius:" + AbstractCluster.formatVector(cluster.getRadius(), null));
    	}
    	return clusters;
    }

    /**
     * Loads clusters from given path
     * 
     * @param output
     * @return
     * @throws IOException
     */
    private List<Cluster> loadClustersWritable(Path output) throws IOException {
    	List<Cluster> clusters = new LinkedList<Cluster>();
    	Configuration conf = new Configuration();
    	FileSystem fs = FileSystem.get(output.toUri(), conf);
    	for (FileStatus s : fs.listStatus(output, new ClustersFilter())) {
    		clusters.addAll(readClustersWritable(s.getPath()));
    	}
    	return clusters;
    }

    /**
     * Returns collection of students in given cluster
     * 
     * @param studentCluster
     * @return
     * @throws IOException
     */
    public Map<String, ArrayList<Double>> getStudentsByCluster(int studentCluster)
        throws IOException {
    	final Configuration configuration = new Configuration();
    	
        final Path input = new Path(OUTPUT_PATH + "/" + Cluster.CLUSTERED_POINTS_DIR + "/part-m-00000");
 
        final SequenceFile.Reader reader =
            new SequenceFile.Reader(
                configuration,
                SequenceFile.Reader.file(input));
 
        final IntWritable key = new IntWritable();
        final WeightedPropertyVectorWritable value = new WeightedPropertyVectorWritable();
        
        final Map<Integer, LinkedList<String>> clusterMap =
        			new HashMap<Integer, LinkedList<String>>();
        
        while (reader.next(key, value)) {            
            int clusterId = key.get();
            
            NamedVector nv = (NamedVector) value.getVector();
            String vectorName = nv.getName();
            
            LinkedList<String> list = clusterMap.get(clusterId);
            
            if(list == null) {
            	list = new LinkedList<String>();
            	clusterMap.put(clusterId, list);
            }
            
            list.add(vectorName);    
        }
        reader.close();
        
        for(Map.Entry<Integer, LinkedList<String>> entry : clusterMap.entrySet()) {
        	Logger.log("Cluster " + entry.getKey() + ": " + entry.getValue());
        }
        
        ManhattanDistanceMeasure mdm = new ManhattanDistanceMeasure();
        Vector studentVector = new NamedVector(new DenseVector(studentGrades), studentId);
        
        List<String> vectorsInCluster = clusterMap.get(studentCluster);
        
        HashMap<String,Double> map = new HashMap<String,Double>();
        ValueComparator bvc =  new ValueComparator(map);
        
        for(String vecId : vectorsInCluster) {   
        	if(vecId.equals(studentId)) continue;        	
        	
        	Vector vector = vectorMap.get(vecId);
        	double distance = mdm.distance(studentVector, vector);
        	
        	map.put(vecId, distance);
        }
        
        TreeMap<String,Double> sortedMap = new TreeMap<String,Double>(bvc);
        sortedMap.putAll(map);
        
        int i = 0;
        HashMap<String, ArrayList<Double>> resultData = new HashMap<String, ArrayList<Double>>();
        for(Map.Entry<String, Double> entry : sortedMap.entrySet()) {
        	if(i++ >= 100) {
        		break;
        	}
        	
        	String id = entry.getKey();
        	Double distance = entry.getValue();
        	
        	ArrayList<Double> features = new ArrayList<Double>(studentData.get(id));
        	features.add(0, distance);
        	resultData.put(id, features);
        }
        
        return resultData;
    }
	 
    /**
     * Read vectors from file
     * @return
     * @throws Exception
     */
    public List<Vector> readVectors() throws Exception {
    	FileInputStream inputStream = null;
		Scanner sc = null;
		
		String delims = "[,]";
		
		double[] features;
		List<Vector> vectors = new LinkedList<Vector>();
		
		try {
		    inputStream = new FileInputStream(CSV_PATH);
		    sc = new Scanner(inputStream, "UTF-8");
		    
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		        String[] tokens = line.split(delims);
		        
		        String id = tokens[0];
		        features = new double[7];
		        
		        ArrayList<Double> fields = new ArrayList<Double>();
		        for(int i = 1; i < tokens.length; i++) {
		        	double token = Double.valueOf(tokens[i]);		        	
		        	
		        	if(i < 8) {
		        		features[i - 1] = token;
		        	}
		        	
		        	fields.add(token);
		        }
		        
		        Vector vector = new NamedVector(new DenseVector(features), id);
		        vectors.add(vector);
		        vectorMap.put(id, vector);
		        studentData.put(id, fields);
		    }
		    
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} finally {
			try {
			    if (inputStream != null) {
			        inputStream.close();
			    }
			}
			catch(Exception e){ /* do nothing */ }
		    
			try {
			    if (sc != null) {
			        sc.close();
			    }
			}
		    catch(Exception e){ /* do nothing */ }
		}
		
		return vectors;
    }
	    
    /**
     * @return distanceFromCenters
     */
    public ArrayList<ClusterCenter> getDistanceFromCenters() {
		return distanceFromCenters;
	}

    
    /**
     * Write CSVs to file system
     * @param map
     * @param feature
     */
	public void writeCSVs(Map<String, ArrayList<Double>> map, Feature feature) {
		PrintWriter rWriter = null;
		PrintWriter mWriter = null;
		PrintWriter sWriter = null;
		PrintWriter pWriter = null;
		
		try {				
			rWriter = new PrintWriter(WRITER_BASE + "reading.csv", "UTF-8");
			mWriter = new PrintWriter(WRITER_BASE + "math.csv", "UTF-8");
			sWriter = new PrintWriter(WRITER_BASE + "science.csv", "UTF-8");
			pWriter = new PrintWriter(WRITER_BASE + "projections.csv", "UTF-8");
			
			rWriter.print("Name");
			rWriter.print(',');
			rWriter.print("Distance");
			rWriter.print(',');
			rWriter.print("Kindergarten");
			rWriter.print(',');
			rWriter.print("First");
			rWriter.print(',');
			rWriter.print("Third");
			rWriter.print(',');
			rWriter.print("Fifth");
			rWriter.print(',');
			rWriter.print("Eighth");
			rWriter.print('\n');
			
			mWriter.print("Name");
			mWriter.print(',');
			mWriter.print("Distance");
			mWriter.print(',');
			mWriter.print("Kindergarten");
			mWriter.print(',');
			mWriter.print("First");
			mWriter.print(',');
			mWriter.print("Third");
			mWriter.print(',');
			mWriter.print("Fifth");
			mWriter.print(',');
			mWriter.print("Eighth");
			mWriter.print('\n');
			
			sWriter.print("Name");
			sWriter.print(',');
			sWriter.print("Distance");
			sWriter.print(',');
			sWriter.print("Third");
			sWriter.print(',');
			sWriter.print("Fifth");
			sWriter.print(',');
			sWriter.print("Eighth");
			sWriter.print('\n');
			
			pWriter.print("Assessment");
			pWriter.print(',');
			pWriter.print("Kindergarten");
			pWriter.print(',');
			pWriter.print("First");
			pWriter.print(',');
			pWriter.print("Third");
			pWriter.print(',');
			pWriter.print("Projected Fifth");
			pWriter.print(',');
			pWriter.print("Projected Eighth");
			pWriter.print('\n');
			
			double totalDistance = 0;
			for(Map.Entry<String, ArrayList<Double>> entry : map.entrySet()) {
				String id = entry.getKey();
				ArrayList<Double> features = entry.getValue();
				
				double distance = features.get(0);
				totalDistance += distance;
				String formattedDistance = new DecimalFormat("#0.000").format(distance);
				
				rWriter.print(id);
				rWriter.print(',');
				rWriter.print(formattedDistance);
				rWriter.print(',');
				rWriter.print(features.get(1));
				rWriter.print(',');
				rWriter.print(features.get(3));
				rWriter.print(',');
				rWriter.print(features.get(5));
				rWriter.print(',');
				rWriter.print(features.get(8));
				rWriter.print(',');
				rWriter.print(features.get(11));
				rWriter.print('\n');
				
				mWriter.print(id);
				mWriter.print(',');
				mWriter.print(formattedDistance);
				mWriter.print(',');
				mWriter.print(features.get(2));
				mWriter.print(',');
				mWriter.print(features.get(4));
				mWriter.print(',');
				mWriter.print(features.get(6));
				mWriter.print(',');
				mWriter.print(features.get(9));
				mWriter.print(',');
				mWriter.print(features.get(12));
				mWriter.print('\n');
				
				sWriter.print(id);
				sWriter.print(',');
				sWriter.print(formattedDistance);
				sWriter.print(',');
				sWriter.print(features.get(7));
				sWriter.print(',');
				sWriter.print(features.get(10));
				sWriter.print(',');
				sWriter.print(features.get(13));
				sWriter.print('\n');
			}

			double totalWeight = 0;
			
			// Calculate inverse weights based on distances
			double[] weights = new double[map.size()];
			int i = 0;
			for(Map.Entry<String, ArrayList<Double>> entry : map.entrySet()) {
				List<Double> features = entry.getValue();
				double distance = features.get(0);
				
//					double weight = (totalDistance - distance) / (2 * totalDistance);
				double weight = distance / totalDistance;
				weights[i++] = weight;
				totalWeight += weight;
				
				Logger.log(weight + " = ( " + totalDistance + " - " + distance + " ) / ( 2 * " + totalDistance + " )");
			}
			
			Logger.log("Total Weight: " + totalWeight);
			
			// Use weights to calculate weighted average for each assessment type
			double fReading = 0,
					fMath = 0,
					fScience = 0,
					eReading = 0,
					eMath = 0,
					eScience = 0;
			i = 0;
			for(Map.Entry<String, ArrayList<Double>> entry : map.entrySet()) {
				List<Double> features = entry.getValue();
				
				fReading += features.get(8) * weights[i];
				fMath += features.get(9) * weights[i];
				fScience += features.get(10) * weights[i];
				eReading += features.get(11) * weights[i];
				eMath += features.get(12) * weights[i];
				eScience += features.get(13) * weights[i];
				
				i++;
			}
			
			// Write to the projection CSV
			pWriter.write("Reading");
			pWriter.write(',');
			pWriter.write(feature.getkReading() + "");
			pWriter.write(',');
			pWriter.write(feature.getfReading() + "");
			pWriter.write(',');
			pWriter.write(feature.gettReading() + "");
			pWriter.write(',');
			pWriter.write(new DecimalFormat("#0.000").format(fReading));
			pWriter.write(',');
			pWriter.write(new DecimalFormat("#0.000").format(eReading ));
			pWriter.write('\n');
			
			pWriter.write("Math");
			pWriter.write(',');
			pWriter.write(feature.getkMath() + "");
			pWriter.write(',');
			pWriter.write(feature.getfMath() + "");
			pWriter.write(',');
			pWriter.write(feature.gettMath() + "");
			pWriter.write(',');
			pWriter.write(new DecimalFormat("#0.000").format(fMath));
			pWriter.write(',');
			pWriter.write(new DecimalFormat("#0.000").format(eMath));
			pWriter.write('\n');
			
			pWriter.write("Science");
			pWriter.write(',');
			pWriter.write("0");
			pWriter.write(',');
			pWriter.write("0");
			pWriter.write(',');
			pWriter.write(feature.gettScience() + "");
			pWriter.write(',');
			pWriter.write(new DecimalFormat("#0.000").format(fScience));
			pWriter.write(',');
			pWriter.write(new DecimalFormat("#0.000").format(eScience));
			pWriter.write('\n');
		} catch(Exception e) {
			Logger.log("Exception caught in Mahout Util");
			e.printStackTrace();
		} finally {
			if(rWriter != null) {
				rWriter.close();
			}
			if(mWriter != null) {
				mWriter.close();
			}
			if(sWriter != null) {
				sWriter.close();
			}
			if(pWriter != null) {
				pWriter.close();
			}
		}
	}

	/**
	 * Write JSON to file system
	 * 
	 * @param distanceFromCenters
	 */
	public void writeJSON(ArrayList<ClusterCenter> distanceFromCenters) {
		PrintWriter jWriter = null;
		
		try {
			jWriter = new PrintWriter(WRITER_BASE + "clusters.json", "UTF-8");
			
			JSONArray nodes = new JSONArray();
			JSONArray links = new JSONArray();
			
			JSONObject student = new JSONObject();
			student.put("name", "Student");
			student.put("group", 1);
			student.put("size", 5);
			
			nodes.put(student);
			
			//Calculate totals
			long totalObservations = 0;
			double totalDistance = 0;
			for(ClusterCenter cc : distanceFromCenters) {
				totalObservations += cc.getNumObservations();
				totalDistance += cc.getDistanceToStudent();
			}
			
			int i = 1;
			for(ClusterCenter cc : distanceFromCenters) {
				JSONObject node = new JSONObject();
				node.put("name", cc.getId());
				node.put("group", 2);
				node.put("size", (cc.getNumObservations() * 1.0 / totalObservations) * 100);
				
				JSONObject link = new JSONObject();
				link.put("source", 0);
				link.put("target", i++);
				link.put("value", (cc.getDistanceToStudent() / totalDistance) * 100);
				
				nodes.put(node);
				links.put(link);
			}
			
			JSONObject root = new JSONObject();
			root.put("nodes", nodes);
			root.put("links", links);
			
			jWriter.print(root.toString());			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(jWriter != null) {
				jWriter.close();
			}
		}
	}			
		
		/**
	     * Used to sort collection by distance measure. Ascending.
	     * 
	     * @author root
	     *
	     */
		private class ValueComparator implements Comparator<String> {

	        Map<String, Double> base;
	        public ValueComparator(Map<String, Double> base) {
	            this.base = base;
	        }

	        // Note: this comparator imposes orderings that are inconsistent with equals.    
	        public int compare(String a, String b) {
	            if (base.get(a) >= base.get(b)) {
	                return 1;
	            } else {
	                return -1;
	            } // returning 0 would merge keys
	        }
	    }
}
