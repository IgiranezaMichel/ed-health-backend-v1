package com.ed_health_backend_v1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ed_health_backend_v1.repository.PaymentRepository;

@Service
public class PaymentServices {
@Autowired private PaymentRepository paymentRepository;
}
