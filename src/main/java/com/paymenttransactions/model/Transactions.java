package com.paymenttransactions.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the Payment Transactions")
@Entity
public class Transactions {
	
	@Id
	@GeneratedValue
	@JsonIgnore
	private Integer id;
	
	@Size(min=4, message="User Id should have atleast 4 characters")
	@ApiModelProperty(notes="User Id should have atleast 4 characters")
	private String userId;
	
	@Size(min=7, message="Transaction type should have atleast 7 characters")
	@ApiModelProperty(notes="Transaction type should have atleast 7 characters")
	private String transactionType;
	
	
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date createDate;
	
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(notes="Date of Payment Transaction")
	private Date transactionDate;
	
	@Temporal(TemporalType.TIME)
	@ApiModelProperty(notes="Time of Payment Transaction")
	//@DateTimeFormat(pattern = "HH-mm-ss")
	private Date transactionTime;
	
	public Date getTransactionDate() {
		return transactionDate;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}
	
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Integer getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public Transactions() {
		
	}
	

	/**
	 * @param id
	 * @param userId
	 * @param transactionType
	 * @param createDate
	 */
	public Transactions(Integer id, String userId, String transactionType, Date transactionDate, Date transactionTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
	}




	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", userId=" + userId + ", transactionType=" + transactionType
				+ ", transactionDate=" + transactionDate + ", transactionTime=" + transactionTime + "]";
	}




}
