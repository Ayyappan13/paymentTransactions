package com.paymentTransactions.controller

import com.paymentTransactions.util.DataUtil
import com.paymentTransactions.util.JsonUtil
import com.paymenttransactions.controller.PaymentTransactionController
import com.paymenttransactions.model.Transactions
import com.paymenttransactions.repository.PaymentRepository
import com.paymenttransactions.service.PaymentTransactionService
import net.minidev.json.JSONUtil
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

import static org.springframework.http.MediaType.APPLICATION_XML
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static  org.springframework.http.MediaType.APPLICATION_JSON


class PaymentTransactionControllerTest extends Specification{

    @Shared
    String BASE_URL = "http://localhost"
    @Shared
    String HOST = System.getProperty("server.host") ? System.getProperty("server.host") : "http://localhost"
    @Shared
    String PORT = System.getProperty("server.port") ? System.getProperty("server.port") : "8080"
    @Shared
    String URL = HOST + ":" + PORT + "/"

    PaymentTransactionService paymentTransactionService
    PaymentTransactionController paymentTransactionController
    HttpServletRequest httpServletRequest
    MockMvc mockMvc

    def setup(){
        paymentTransactionService = Mock()
        paymentTransactionController = new PaymentTransactionController(paymentTransactionService: paymentTransactionService)
        httpServletRequest = Mock()

        mockMvc = standaloneSetup(paymentTransactionController).build()
    }

    def "retrievePaymentTransactionsByUserId"(){
        given:
        def localURL = "http://localhost:8080/retrievePaymentTransactionsByUserId?userId=abc@gmail.com"
        def userId = "abc@gmail.com"
        paymentTransactionService.getPaymentTransactionsByUserId(_ as String) >> DataUtil.buildPaymentTransactionsResponse("VALID")

        when:
        def response = mockMvc.perform( get(localURL)
                .header("Accept","*/*")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andReturn().getResponse()

        def responseString = response.getContentAsString()

        then:
        noExceptionThrown()
        response.getStatus() == 200
        responseString != null
    }

    def "retrievePaymentTransactionsByUserIdAndTransactionType"(){
        given:
        def localURL = "http://localhost:8080/retrievePaymentTransactionsByUserIdAndTransactionType?userId=abc@gmail.com&transactionType=billing"
        def userId = "abc@gmail.com"
        paymentTransactionService.getPaymentTransactionsByUserIdAndTransactionType(_ as String, _ as String) >> DataUtil.buildPaymentTransactionsResponse("VALID")

        when:
        def response = mockMvc.perform( get(localURL)
                .header("Accept","*/*")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andReturn().getResponse()

        def responseString = response.getContentAsString()

        then:
        noExceptionThrown()
        response.getStatus() == 200
        responseString != null
    }

    def "retrievePaymentTransactionsByTransactionType"(){
        given:
        def localURL = "http://localhost:8080/retrievePaymentTransactionsByUserIdAndTransactionType?transactionType=Subscriptions"
        def userId = "abc@gmail.com"
        paymentTransactionService.getPaymentTransactionsByTransactionType(_ as String) >> DataUtil.buildPaymentTransactionsResponse("VALID")

        when:
        def response = mockMvc.perform( get(localURL)
                .header("Accept","*/*")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andReturn().getResponse()

        def responseString = response.getContentAsString()

        then:
        noExceptionThrown()
        response.getStatus() == 200
        responseString != null
    }

    def "retrieveAllPaymentTransactions"(){
        given:
        def localURL = "http://localhost:8080/retrievePaymentTransactionsByUserIdAndTransactionType?date=2020-08-11"
        def userId = "abc@gmail.com"
        paymentTransactionService.getPaymentTransactionsByTransactionType(_ as String) >> DataUtil.buildPaymentTransactionsResponse("VALID")

        when:
        def response = mockMvc.perform( get(localURL)
                .header("Accept","*/*")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andReturn().getResponse()

        def responseString = response.getContentAsString()

        then:
        noExceptionThrown()
        response.getStatus() == 200
        responseString != null
    }

    def "createPaymentTransactions"(){
        given:
        def input = JsonUtil.toJson(DataUtil.buildPaymentTransactionsRequest())
        def output = "User abc@gmail.com is successfully created"
        def localURL = "http://localhost:8080/createPaymentTransactions"
        def userId = "abc@gmail.com"
        paymentTransactionService.createPaymentTransactions(_ as Transactions) >> DataUtil.buildPaymentTransactionsRequest()

        when:
        def response = mockMvc.perform( post(localURL)
                .header("Accept","*/*")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON).content(input))
                .andReturn().getResponse()

        def responseString = response.getContentAsString()

        then:
        noExceptionThrown()
        response.getStatus() == 201
        responseString == output
    }

}
