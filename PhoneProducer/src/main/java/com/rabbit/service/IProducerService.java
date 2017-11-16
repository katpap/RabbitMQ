package com.rabbit.service;

public interface IProducerService {
	
	void sendMessage(String message) throws IllegalArgumentException;
	
	void sendMessage();
}
