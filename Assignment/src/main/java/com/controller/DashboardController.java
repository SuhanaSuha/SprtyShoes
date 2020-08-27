package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class DashboardController {

	  @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	    public String dashboard(ModelMap map) 
	    {
		  map.addAttribute("pageTitle", "SPORTY SHOES - DASHBOARD");
	        return "dashboard"; 
	    }		  
}
