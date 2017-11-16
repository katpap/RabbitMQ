package com.rabbit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rabbit.service.IConsumerService;
import com.rabbit.model.PhoneMessage;
import com.rabbit.util.PhoneCountryCatalog;

@Service	
public class ConsumerService implements IConsumerService{

private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);
    
private  ConcurrentHashMap<String, List<String>> countryPhonesMap = new ConcurrentHashMap<>();

private final AtomicInteger msgCounter = new AtomicInteger();


	public AtomicInteger getCounter() {
		return new AtomicInteger(msgCounter.intValue());
	}

	@Override
	public void receive(PhoneMessage message){
		msgCounter.incrementAndGet();
		groupMessage(message);
	}

	public void groupMessage(PhoneMessage message){
		System.out.println("Total messages received: " + msgCounter);
		countryPhonesMap.computeIfAbsent(PhoneCountryCatalog.getCountryByCallingCode(message.getTelephoneNumber()), k -> new ArrayList<>()).add(message.getTelephoneNumber());
				
	}
	
	public ConcurrentHashMap<String, List<String>> getPhoneMessageReport(){
		return countryPhonesMap;
	}
}
