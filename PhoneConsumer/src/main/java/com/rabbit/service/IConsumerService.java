package com.rabbit.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.rabbit.model.PhoneMessage;

public interface IConsumerService {
	
	@RabbitListener(queues="app.phone.queue")
	void receive(PhoneMessage message);
	
	ConcurrentHashMap<String, List<String>> getPhoneMessageReport();
	
}
