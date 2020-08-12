package com.paymenttransactions.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class PaymentTransactionsFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(PaymentTransactionsFilter.class);
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Enumeration<String> headerNames = httpRequest.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                if(httpRequest.getHeader(headerNames.nextElement()) == "X-API-KEY") {
                    MDC.put("x-api-id", httpRequest.getHeader("X-API-KEY"));
                }else{
                    MDC.put("x-api-id", "1234567890");
                }
            }
        }
        log.info("x-api-id from Filter : {}",MDC.get("x-api-id"));
        //doFilter
        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
    }
}
