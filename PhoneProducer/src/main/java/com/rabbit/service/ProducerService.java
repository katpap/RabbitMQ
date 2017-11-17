package com.rabbit.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.rabbit.model.PhoneMessage;
import com.rabbit.util.PhoneCountryCatalog;

@Service	
public class ProducerService implements IProducerService{

private static final Logger log = LoggerFactory.getLogger(ProducerService.class);
    
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 2000)
	public void sendMessage(){
		PhoneMessage msg = new PhoneMessage();
		msg.setId(UUID.randomUUID().toString());
		try {
			
			String telephone = PhoneCountryCatalog.generatePhoneNumber(PhoneCountryCatalog.generateCountryCode());
			msg.setTelephoneNumber(telephone);
			rabbitTemplate.convertAndSend(msg);
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}
    
    public void sendMessage(String phone) throws IllegalArgumentException{
    	
    	PhoneCountryCatalog.isValidPhoneNumber(phone);
    	PhoneMessage phoneMsg = new PhoneMessage();
		phoneMsg.setId(UUID.randomUUID().toString());
		phoneMsg.setTelephoneNumber(phone);
		
		rabbitTemplate.convertAndSend(phoneMsg);
	}
}
