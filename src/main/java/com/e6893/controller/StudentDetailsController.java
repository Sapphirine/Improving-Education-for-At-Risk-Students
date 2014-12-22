package com.e6893.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.e6893.model.Student;
import com.e6893.utils.HiveUtil;
import com.e6893.utils.Logger;

/**
 * Student Details MVC Controller
 * 
 * @author Jairo Pava
 *
 */
@Controller
public class StudentDetailsController {

	@RequestMapping(value = "studentDetail", method = RequestMethod.GET, params = {"studentId"})
	public String displayStudentDetails(@RequestParam(value = "studentId") String studentId, Model model) {
		Logger.log("--> StudentDetails.displayStudentDetails");
		Logger.log("student id: " + studentId);
		
		Student student = HiveUtil.getStudent(studentId);
		model.addAttribute("studentDetail", student);
		return "studentDetail";
	}
}
