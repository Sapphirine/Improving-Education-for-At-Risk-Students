package com.e6893.utils;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

/**
 * Cluster Filter
 * 
 * @author Jairo Pava
 *
 */
final class ClustersFilter implements PathFilter {
	
	public boolean accept(Path path) {
		String pathString = path.toString();
		return pathString.contains("/clusters-");
	}
}