package com.paymenttransactions.controller;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import com.paymenttransactions.constants.PaymentTransactionConstants;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.paymenttransactions.model.Transactions;
import com.paymenttransactions.service.PaymentTransactionService;

import static com.paymenttransactions.constants.PaymentTransactionConstants.*;
import static org.springframework.http.MediaType.*;

@RestController
public class PaymentTransactionController {
	
	private static final Logger log = LoggerFactory.getLogger(PaymentTransactionController.class);

	//private PaymentTransactionService paymentTransactionService;

/*	@Autowired
	public PaymentTransactionController(PaymentTransactionService paymentTransactionService){
		this.paymentTransactionService = paymentTransactionService;
	}*/

	@Autowired
	private PaymentTransactionService paymentTransactionService;

	@GetMapping("/retrieveAllPaymentTransactions")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	//public List<Transactions> retrieveAllPaymentTransactions(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
	public ResponseEntity<?> retrieveAllPaymentTransactions(@Param("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

		MDC.put("Resource", RETRIEVE_ALL_PAYMENT_TRANSACTIONS);

		log.info("retrieveAllPaymentTransactions : {}",date);
		
		List<Transactions> transactions = paymentTransactionService.getAllPaymentTransactions(date);

		//ResponseEntity<List<Transactions>> responseEntity;

		//ResponseEntity.status(HttpStatus.OK).body(transactions);


		//return (ResponseEntity<List<Transactions>>) transactions;
		//return ResponseEntity.created(location).body(transactions);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@GetMapping("/retrievePaymentTransactionsByUserId")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseEntity<?> retrievePaymentTransactionsByUserId(@Param("userid") String userId) {

		MDC.put("Resource", RETRIEVE_PAYMENT_TRANSACTIONS_BY_USER_ID);

		log.info("retrievePaymentTransactionsByUserId : {}",userId);
		
		List<Transactions> transactions = paymentTransactionService.getPaymentTransactionsByUserId(userId);
		 
		//return transactions;
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}	
	
	@GetMapping("/retrievePaymentTransactionsByUserIdAndTransactionType")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseEntity<?> retrievePaymentTransactionsByUserIdAndTransactionType(@Param("userId") String userId, @Param("transactionType") String transactionType) {

		MDC.put("Resource", RETRIEVE_PAYMENT_TRANSACTIONS_BY_USER_ID_TRAN_TYPE);
		log.info("retrievePaymentTransactionsByUserIdAndTransactionType : {}, {}",userId,transactionType);
		
		List<Transactions> transactions = paymentTransactionService.getPaymentTransactionsByUserIdAndTransactionType(userId, transactionType);

		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}
	
	@GetMapping("/retrievePaymentTransactionsByTransactionType")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseEntity<?> retrievePaymentTransactionsByTransactionType(@Param("transactionType") String transactionType) {

		MDC.put("Resource", RETRIEVE_PAYMENT_TRANSACTIONS_BY_TRAN_TYPE);
		log.info("retrievePaymentTransactionsByTransactionType : {}",transactionType);
		
		List<Transactions> transactions = paymentTransactionService.getPaymentTransactionsByTransactionType(transactionType);

		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}	

	
	@PostMapping("/createPaymentTransactions")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseEntity<?> createPaymentTransactions(@Valid @RequestBody Transactions transactions) {

		MDC.put("Resource", CREATE_PAYMENT_TRANSACTIONS);
		log.info("createPaymentTransactions");
		
		Transactions transacationSuccess = paymentTransactionService.createPaymentTransactions(transactions);
		
		//return new ResponseEntity<>("User - "+transacationSuccess.getUserId()+" transaction is success",HttpStatus.CREATED);

		return new ResponseEntity<>("User "+transacationSuccess.getUserId()+" is successfully created", HttpStatus.CREATED);
		
	}
}
