package com.rabbit.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableRabbit
@EnableScheduling
@ComponentScan(basePackages={"com.rabbit.service", "com.rabbit.controller"})
public class MessagingApplication{

	public static final String EXCHANGE_NAME = "app.phone.exchange";
	public static final String QUEUE_NAME = "app.phone.queue";
	public static final String ROUTING_KEY = QUEUE_NAME;


	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME);
	}

//	@Bean
//	public Binding declareBinding() {
//		return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
//	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		rabbitTemplate.setExchange(EXCHANGE_NAME);
		rabbitTemplate.setQueue(QUEUE_NAME);
		rabbitTemplate.setRoutingKey(ROUTING_KEY);
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	
	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);
	}
	

}
