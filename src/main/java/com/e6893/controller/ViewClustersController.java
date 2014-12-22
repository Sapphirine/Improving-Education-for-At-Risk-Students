package com.e6893.controller;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.e6893.model.Feature;
import com.e6893.utils.Logger;
import com.e6893.utils.MahoutUtil;

/**
 * View Clusters page MVC Controller
 * 
 * @author Jairo Pava
 *
 */
@Controller
public class ViewClustersController {
	
	@RequestMapping(value = "viewClusters", method = RequestMethod.GET)
	public String viewClusters(Model model, HttpSession session) {
		Logger.log("--> ViewClustersController.viewClusters");
		
		Feature feature = (Feature) session.getAttribute("feature");
		Logger.log(feature.toString());
		
		String studentName = feature.getName();
		double[] studentGrades = { feature.getkReading(),
									feature.getkMath(),
									feature.getfReading(),
									feature.getfMath(),
									feature.gettReading(),
									feature.gettMath(),
									feature.gettScience()};
		
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("servlet-config.xml");
		MahoutUtil util = context.getBean("mahoutUtil", MahoutUtil.class);
	   
		util.calculateClusters(studentName, studentGrades);
		util.writeJSON(util.getDistanceFromCenters());
		
		model.addAttribute("distanceFromCenters", util.getDistanceFromCenters());
		
		return "viewClusters";
	}
}
