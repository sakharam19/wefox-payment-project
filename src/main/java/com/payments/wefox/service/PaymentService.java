package com.payments.wefox.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payments.wefox.entity.Accounts;
import com.payments.wefox.entity.Payments;
import com.payments.wefox.repository.AccountRepository;
import com.payments.wefox.repository.PaymentRepository;

/**
 * 
 * @author Sakharam
 *
 */
@Service
public class PaymentService 
{

	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Transactional
	public void createPaymentRequest(Payments payments) 
	{
		Payments paymentReturn = new Payments();
		payments.setCreatedOn(LocalDateTime.now());
		paymentReturn = paymentRepository.save(payments);
		if(null!=paymentReturn)
		{
			updateLastPaymentDate(paymentReturn);
		}
	}

	@Transactional
	private void updateLastPaymentDate(Payments paymentReturn) 
	{
		Accounts account = new Accounts();
		account = accountRepository.findByAccountId(paymentReturn.getAccountId());
		if(null != account)
		{
			account.setLastPaymentDate(LocalDateTime.now());
			
			accountRepository.updateById(account.getAccountId());
		}
	}

	@Transactional
	public Payments getPaymentInformation(Payments payments) 
	{
		payments = paymentRepository.getById(payments.getPaymentId());
		return payments;
	}

	@Transactional
	public void removePaymentsData() 
	{
		paymentRepository.deleteAll();
	}

}
