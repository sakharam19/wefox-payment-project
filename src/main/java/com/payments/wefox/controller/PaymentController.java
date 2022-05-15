package com.payments.wefox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payments.wefox.entity.Payments;
import com.payments.wefox.service.PaymentService;

/**
 * 
 * @author Sakharam
 *
 */
@RestController
@RequestMapping
public class PaymentController 
{
	@Autowired
	PaymentService paymentService; 
	
	@PostMapping("/payment")
	public Payments paymentService(@RequestBody Payments payments)
	{
		return paymentService.getPaymentInformation(payments);
	}
	
	@KafkaListener(topics = "MyTopic", groupId="mygroup",  containerFactory = "paymentListener", id = "paymentTopic")
	public void consumePaymentMessages(Payments payments)
	{
		System.out.println("message "+payments.getCreditCard());
		paymentService.createPaymentRequest(payments);
	}

}
