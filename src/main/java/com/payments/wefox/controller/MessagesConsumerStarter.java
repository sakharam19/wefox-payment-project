package com.payments.wefox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payments.wefox.kafkaconfig.KafkaControllerService;
import com.payments.wefox.service.PaymentService;

/**
 * 
 * @author Sakharam
 *
 */
@RestController
@RequestMapping
public class MessagesConsumerStarter 
{
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	KafkaControllerService kafkaControllerService;
	
	@GetMapping("/start")
	public String startConsumer()
	{
		paymentService.removePaymentsData();
		kafkaControllerService.start();
		return "Application started processing payment messages";
	}
}
