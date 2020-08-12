package com.paymentTransactions.service

import com.paymentTransactions.util.DataUtil
import com.paymenttransactions.controller.PaymentTransactionController
import com.paymenttransactions.model.Transactions
import com.paymenttransactions.repository.PaymentRepository
import com.paymenttransactions.service.PaymentTransactionService
import com.sun.xml.internal.ws.api.ha.StickyFeature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.http.MediaType.APPLICATION_XML
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class PaymentTransactionServiceTest extends Specification{



    MockMvc mockMvc

    PaymentRepository paymentRepository
    PaymentTransactionService paymentTransactionService

    def setup(){
        paymentRepository = Mock()
        paymentTransactionService = new PaymentTransactionService(paymentRepository: paymentRepository)


        mockMvc = standaloneSetup(paymentTransactionService).build()
    }

    def "getPaymentTransactionsByUserIdAndTransactionType"(){

        given:
        def userId = "abc@gmail.com"
        def tranType = "Billing"
        def actualResponse = DataUtil.buildPaymentTransactionsResponse("VALID")
        paymentRepository.findAllByUserIdAndTransactionTypeIgnoreCase(_ as String, _ as String) >> DataUtil.buildPaymentTransactionsResponse("VALID")

        when:
        def response = paymentTransactionService.getPaymentTransactionsByUserIdAndTransactionType(userId, tranType)

        then:
        actualResponse.size() == response.size()
        actualResponse.get(0).getUserId() == response.get(0).getUserId()
    }

    def "getPaymentTransactionsByUserIdAndTransactionType - NoDataFound"(){

        given:
        def userId = "a@gmail.com"
        def tranType = "Billing"

        def actualResponse = DataUtil.buildPaymentTransactionsResponse("NO_DATA_FOUND")
        paymentRepository.findAllByUserIdAndTransactionTypeIgnoreCase(_ as String, _ as String) >> DataUtil.buildPaymentTransactionsResponse("NO_DATA_FOUND")

        when:
        def response = paymentTransactionService.getPaymentTransactionsByUserIdAndTransactionType(userId, tranType)

        then:
        response.get(0).getUserId() != userId

    }

    def "getAllPaymentTransactions"(){

        given:
        def userId = "abc@gmail.com"
        def tranType = "Billing"
        def reqDate = new Date()
        def actualResponse = DataUtil.buildPaymentTransactionsResponse("VALID")
        paymentRepository.findAllByTransactionDateOrderByUserId(reqDate) >> DataUtil.buildPaymentTransactionsResponse("VALID")

        when:
        def response = paymentTransactionService.getAllPaymentTransactions(reqDate)

        then:
        actualResponse.size() == response.size()
        actualResponse.get(0).getUserId() == response.get(0).getUserId()
    }

    def "getAllPaymentTransactions - NoDataFound"(){

        given:
        def userId = "test@gmail.com"
        def tranType = "Billing"
        def reqDate = new Date()
        def actualResponse = DataUtil.buildPaymentTransactionsResponse("NO_DATA_FOUND")
        paymentRepository.findAllByTransactionDateOrderByUserId(reqDate) >> DataUtil.buildPaymentTransactionsResponse("NO_DATA_FOUND")

        when:
        def response = paymentTransactionService.getAllPaymentTransactions(reqDate)

        then:
        response.get(0).getUserId() != userId
    }

    def "getPaymentTransactionsByTransactionType"(){

        given:
        def userId = "abc@gmail.com"
        def tranType = "Billing"
        def actualResponse = DataUtil.buildPaymentTransactionsResponse("VALID")
        paymentRepository.findAllByTransactionType(_ as String) >> DataUtil.buildPaymentTransactionsResponse("VALID")

        when:
        def response = paymentTransactionService.getPaymentTransactionsByTransactionType(tranType)

        then:
        actualResponse.size() == response.size()
        actualResponse.get(0).getTransactionType() == response.get(0).getTransactionType()
    }

    def "getPaymentTransactionsByTransactionType - NoDataFound"(){

        given:
        def userId = "abc@gmail.com"
        def tranType = "test"
        def reqDate = new Date()
        def actualResponse = DataUtil.buildPaymentTransactionsResponse("NO_DATA_FOUND")
        paymentRepository.findAllByTransactionType(tranType) >> DataUtil.buildPaymentTransactionsResponse("NO_DATA_FOUND")

        when:
        def response = paymentTransactionService.getPaymentTransactionsByTransactionType(tranType)

        then:
        response.get(0).getUserId() != userId
    }

    def "getPaymentTransactionsByUserId"(){

        given:
        def userId = "abc@gmail.com"
        def tranType = "Billing"
        def actualResponse = DataUtil.buildPaymentTransactionsResponse("VALID")
        paymentRepository.findAllByUserIdIgnoreCase(_ as String) >> DataUtil.buildPaymentTransactionsResponse("VALID")

        when:
        def response = paymentTransactionService.getPaymentTransactionsByUserId(userId)

        then:
        actualResponse.size() == response.size()
        actualResponse.get(0).getTransactionType() == response.get(0).getTransactionType()
    }

    def "getPaymentTransactionsByUserId - NoDataFound"(){

        given:
        def userId = "abc@gmail.com"
        def tranType = "Billing"
        def actualResponse = DataUtil.buildPaymentTransactionsResponse("NO_DATA_FOUND")
        paymentRepository.findAllByUserIdIgnoreCase(_ as String) >> DataUtil.buildPaymentTransactionsResponse("NO_DATA_FOUND")

        when:
        def response = paymentTransactionService.getPaymentTransactionsByUserId(userId)

        then:
        response.get(0).getUserId() != userId
    }

    def "createPaymentTransactions"(){
        given:
        def userId = "abc@gmail.com"
        def tranType = "Billing"
        def actualResponse = DataUtil.buildPaymentTransactionsRequest()
        paymentRepository.save(_ as Transactions) >> DataUtil.buildPaymentTransactionsRequest()

        when:
        def response = paymentTransactionService.createPaymentTransactions(actualResponse)

        then:
        response.getUserId() == userId
    }

}
