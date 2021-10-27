package com.springdrools.demo.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdrools.demo.domain.FixeddepositIntrestRates;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FixedDepositIntrestService {

	@Autowired
	private KieSession session;

	public FixeddepositIntrestRates calculateIntrestPercentage(FixeddepositIntrestRates fd) {
		log.info("Service-- Started Code for Calculating Intrest Percentage ");
		session.insert(fd);
		log.info("Getting Intrest % on Bank name");
		session.fireAllRules();
		return fd;
	}

}
