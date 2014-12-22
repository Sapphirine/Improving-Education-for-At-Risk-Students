package com.e6893.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.e6893.model.Feature;
import com.e6893.utils.Logger;

/**
 * Features MVC Controller
 * 
 * @author Jairo Pava
 *
 */
@Controller
@SessionAttributes("feature")
public class FeaturesController {
	
	@RequestMapping(value = "/setFeatures", method = RequestMethod.GET)
	public String displayForm(Model model) {
		Feature feature = new Feature();
		
		//Set feature defaults
		feature.setName("Jairo Pava");
		feature.setkReading(66.125);
		feature.setkMath(53.079);
		feature.setfReading(56.869);
		feature.setfMath(57.739);
		feature.settReading(51.473);
		feature.settMath(51.977);
		feature.settScience(64.249);
		
		model.addAttribute("feature", feature);
		
		return "setFeatures";
	}
	
	@RequestMapping(value = "/setFeatures", method = RequestMethod.POST)
	public String setFeatures(@ModelAttribute("feature") Feature feature) {	
		Logger.log("--> FeaturesController.setFeatures");
		Logger.log(feature.toString());
		
		return "redirect:viewClusters.html";
	}	
}
