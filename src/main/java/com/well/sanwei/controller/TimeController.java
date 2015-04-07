package com.well.sanwei.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/time")
public class TimeController {
	
	@RequestMapping("/now")
	public @ResponseBody String  getCurrentTime(){
		Calendar cal = Calendar.getInstance();
		return cal.toString();
	}
}
