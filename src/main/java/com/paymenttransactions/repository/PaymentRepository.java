package com.paymenttransactions.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paymenttransactions.model.Transactions;

@Repository
public interface PaymentRepository extends JpaRepository<Transactions, Integer>{
	
	List<Transactions> findAllByUserIdIgnoreCase(String userId);
	
	List<Transactions>findAllByTransactionType(String transactionType);
	
	List<Transactions>findAllByTransactionDateOrderByUserId(Date transactionDate);
	
	List<Transactions>findAllByUserIdAndTransactionTypeIgnoreCase(String userId, String transactionType);

	
}
