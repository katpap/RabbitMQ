package com.rabbit.controller;


import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rabbit.service.IProducerService;

@RestController
public class PhoneController {
	
	private static final Logger log = LoggerFactory.getLogger(PhoneController.class);
	
	@Autowired
	IProducerService producerService;
	
	
	@RequestMapping(value="/phones/{phoneNumber}", method=RequestMethod.POST)
	public String addPhone(@PathVariable String phoneNumber, HttpServletResponse response){
		
		try{
			
			producerService.sendMessage(phoneNumber);
			
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		
		}catch(Exception e){
			log.error(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		return "";
	}

}
