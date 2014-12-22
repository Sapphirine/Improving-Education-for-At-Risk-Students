package com.e6893.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MVC Controller for parallel coordinates graphs
 * 
 * @author Jairo Pava
 *
 */
@Controller
public class ParallelCoordinatesController {

	@RequestMapping(value = "reading")
	public String displayReadingChart() {
		return "reading";
	}
	
	@RequestMapping(value = "math")
	public String displayMathChart() {
		return "math";
	}
	
	@RequestMapping(value = "science")
	public String displayScienceChart() {
		return "science";
	}
	
	@RequestMapping(value = "projections")
	public String displayProjectionsChart() {
		return "projections";
	}
	
	@RequestMapping(value = "clusterGraph")
	public String displayClusterGraph() {
		return "clusterGraph";
	}
}
