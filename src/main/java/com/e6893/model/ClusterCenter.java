package com.e6893.model;

/**
 * This class is used to represent a cluster center
 * 
 * @author Jairo Pava
 *
 */
public class ClusterCenter {
	
	/**
	 * The Id of the cluster
	 */
	private String id;
	
	/**
	 * The number of points in the cluster
	 */
	private long numObservations;
	
	/**
	 * The distance of the cluster's center to the student selected in the web application
	 */
	private double distanceToStudent;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getNumObservations() {
		return numObservations;
	}
	public void setNumObservations(long numObservations) {
		this.numObservations = numObservations;
	}
	public double getDistanceToStudent() {
		return distanceToStudent;
	}
	public void setDistanceToStudent(double distanceToStudent) {
		this.distanceToStudent = distanceToStudent;
	}
}
