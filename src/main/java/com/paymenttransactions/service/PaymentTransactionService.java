package com.paymenttransactions.service;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paymenttransactions.constants.PaymentTransactionConstants;
import com.paymenttransactions.exception.UserNotFoundException;
import com.paymenttransactions.model.Transactions;
import com.paymenttransactions.repository.PaymentRepository;

@Service
public class PaymentTransactionService {
	
	private static final Logger log = LoggerFactory.getLogger(PaymentTransactionService.class);
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public List<Transactions> getPaymentTransactionsByUserIdAndTransactionType(String userId, String transactionType) {
		
		log.info("getPaymentTransactionsByUserIdAndTransactionType : {}, {}",userId,transactionType);
		
		List<Transactions> transactions = paymentRepository.findAllByUserIdAndTransactionTypeIgnoreCase(userId, transactionType);
		 
		if(transactions.isEmpty()) {
			log.info("{} : {}, {}", PaymentTransactionConstants.USER_ID_TRAN_TYPE_NOT_FOUND, userId,transactionType);
			throw new UserNotFoundException(PaymentTransactionConstants.USER_ID_TRAN_TYPE_NOT_FOUND);
		}
		
		return transactions;
	}

	
	public List<Transactions> getAllPaymentTransactions(Date date) {
		
		log.info("getAllPaymentTransactions : {}",date);
		
		List<Transactions> transactions = paymentRepository.findAllByTransactionDateOrderByUserId(date);
		 
		if(transactions.isEmpty()) {
			log.info("{} {}", PaymentTransactionConstants.NOT_AVAILABLE_FOR_DATE, date);
			throw new UserNotFoundException(PaymentTransactionConstants.NOT_AVAILABLE_FOR_DATE+date);
		}
		
		return transactions;
	}
	
	
	public List<Transactions> getPaymentTransactionsByTransactionType(String transactionType) {
		
		log.info("getPaymentTransactionsByTransactionType : {}",transactionType);
		
		List<Transactions> transactions = paymentRepository.findAllByTransactionType(transactionType);
		 
		if(transactions.isEmpty()) {
			log.info("{} {}", PaymentTransactionConstants.NOT_AVAILABLE_FOR_TRAN_TYPE, transactionType);
			throw new UserNotFoundException(PaymentTransactionConstants.NOT_AVAILABLE_FOR_TRAN_TYPE+transactionType);
		}
		
		return transactions;
	}	

	public List<Transactions> getPaymentTransactionsByUserId(String userId) {
		
		log.info("getPaymentTransactionsByUserId : {}",userId);
		
		List<Transactions> transactions = paymentRepository.findAllByUserIdIgnoreCase(userId);
		 
		if(transactions.isEmpty()) {
			log.info("{} {}", PaymentTransactionConstants.NOT_AVAILABLE_FOR_USER, userId);
			throw new UserNotFoundException(PaymentTransactionConstants.NOT_AVAILABLE_FOR_USER+userId);
		}
		
		return transactions;
	}	
	
	public Transactions createPaymentTransactions(Transactions transactions) {
		
		log.info("createPaymentTransactions : {}",transactions);
		
		Transactions transacationSuccess = paymentRepository.save(transactions);
		
		return transacationSuccess;
	}
	
}
