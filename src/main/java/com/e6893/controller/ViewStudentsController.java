package com.e6893.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e6893.model.Feature;
import com.e6893.utils.Logger;
import com.e6893.utils.MahoutUtil;

/**
 * View Students page MVC Controller
 * 
 * @author Jairo Pava
 *
 */
@Controller
public class ViewStudentsController {
	
	@RequestMapping(value = "viewStudents", method = RequestMethod.GET, params = {"clusterId"})
	public String viewStudents(@RequestParam(value = "clusterId") int clusterId, Model model, HttpSession session) throws IOException {
		Logger.log("--> ViewStudentsController.viewStudents");
		Logger.log("Cluster Id: " + clusterId);
		
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("servlet-config.xml");
		MahoutUtil util = context.getBean("mahoutUtil", MahoutUtil.class);
		
		Map<String, ArrayList<Double>> studentMap = util.getStudentsByCluster(clusterId);
		
		Feature feature = (Feature) session.getAttribute("feature");
		util.writeCSVs(studentMap, feature);
		
		model.addAttribute("clusterId", clusterId);
		
		return "viewStudents";
	}
}
