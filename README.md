# Payment Transactions

## Introduction
The Payment Transactions application is a collection of resources for building RESTful HTTP+JSON APIs to retrieve the transaction of payments in different ways.
  - retrieveAllPaymentTransactions
  - retrievePaymentTransactionsByUserId
  - retrievePaymentTransactionsByUserIdAndTransactionType
  - retrievePaymentTransactionsByTransactionType
  - createPaymentTransactions

## Steps
 - Download application from below GitHug link and import as Maven project.
   - https://github.com/Ayyappan13/paymentTransactions
 - After successfully imported, run the application
 - data.sql file would be executed and insert set of records into "Transacations" table in H2 Inbuild Database.
    -  http://localhost:8080/h2-console
  - Test application using REST client(Postman) by referring JSON file or URI's below

## Links
URI's for get/post Payment Transactions.

  - http://localhost:8080/retrieveAllPaymentTransactions?date={yyyy-MM-dd}
     - {yyyy-MM-dd} -> today's date (2020-08-11) 
  - http://localhost:8080/retrievePaymentTransactionsByUserId?userId={userId}
     - {userId} -> abc@gmail.com/9876543210/xyz@gmail.com 
  - http://localhost:8080/retrievePaymentTransactionsByUserIdAndTransactionType?userId={userId}&transactionType={transactionType}
     - {userId} -> abc@gmail.com
     - {transactionType} -> Billing/Invoicing/Subscriptions
  - http://localhost:8080/retrievePaymentTransactionsByTransactionType?transactionType={transactionType}
     - {transactionType} -> Billing/Invoicing/Subscriptions
  - http://localhost:8080/createPaymentTransactions (Refer swaggger-UI to set RequestBody)

            {
                "userId": "asdf13@gmail.com",
                "transactionType": "invoice",
                "transactionDate": "2020-08-09"
            }


Swagger Specifications
   - http://localhost:8080/v2/api-docs
  - http://localhost:8080/swagger-ui.html

H2 Console - Inbuild Database  
   - http://localhost:8080/h2-console
      - Driver Class : org.h2.Driver
      - JDBC URL : jdbc:h2:mem:testdb      
      - User Name : sa
      - Password :
   - Click on "Connect"                 
      
Logs  
Logs are in JSON format. It will be logged in Console(local) and log file as well.
   - ../paymentTransactions/logs/PaymentTransactions.log
      - "timestamp" : "2020-08-11 03:42:23.500"
      - "level" : "info"
      - "Service" : "PaymentTransactions"
      - "TraceId" : "3e0294a632d3503d" (Sleuth Id)                  
      - "x-api-id" : "1234567890" (which will come from API gateway)
      - "Resource" : "URI's" (retrievePaymentTransactionsByUserIdAndTransactionType)
      - "Class" : ""Name of the class is being invoked.
      - "message" : ""to be printed from log.info()          

Refer "logback-spring.xml" in "resources" for more details

Docker
     -  Docker file is available in below path
     -    -../dockerfile 
               

  

