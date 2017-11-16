package com.rabbit.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.service.IConsumerService;

@RestController
public class PhoneController {
	private static final Logger log = LoggerFactory.getLogger(PhoneController.class);
	
	@Autowired
	IConsumerService iConsumerService;
	
	//retrieves the report
	@RequestMapping(value="/phones/report", method=RequestMethod.GET)
	public ConcurrentHashMap<String, List<String>> getMessageReport(HttpServletResponse response){
		
		try{
			response.setStatus(HttpServletResponse.SC_OK);
			return iConsumerService.getPhoneMessageReport();
		
		}catch(Exception e){
			log.error(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return null;
	}

}
