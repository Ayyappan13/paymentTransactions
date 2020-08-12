package com.paymentTransactions.util

import com.paymenttransactions.model.Transactions
import spock.lang.Specification

import java.text.SimpleDateFormat

class DataUtil extends Specification {


    static List<Transactions> buildPaymentTransactionsResponse(String requestType){
        Transactions transactions = new Transactions();

        List<Transactions> list = new ArrayList<>();

        Date date1 = new Date()

        switch (requestType){
            case "VALID":
                transactions.setId(1001)
                transactions.setUserId("abc@gmail.com")
                transactions.setTransactionType("Billing")
                transactions.setTransactionDate(date1)
                transactions.setTransactionDate(date1)
                break
            case "NO_DATA_FOUND":
                    break
        }
        list.add(transactions)

        return list
    }

    static Transactions buildPaymentTransactionsRequest(){
        Transactions transactions = new Transactions();
        Date date1 = new Date()
        transactions.setId(10001)
        transactions.setUserId("abc@gmail.com")
        transactions.setTransactionType("Billing")
        transactions.setTransactionDate(date1)
        transactions.setTransactionDate(date1)

        return transactions
    }

    public void dateFormat(){

        Date date = new Date();
        String DATE_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        System.out.println("Today is " + sdf.format(date));

    }

}
