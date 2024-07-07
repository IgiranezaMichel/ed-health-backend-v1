package com.ed_health_backend_v1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ed_health_backend_v1.repository.JobRepository;

@Service
public class JobServices {
@Autowired private JobRepository jobRepository;
}
