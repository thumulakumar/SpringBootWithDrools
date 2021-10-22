package com.springdrools.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdrools.demo.domain.FixeddepositIntrestRates;
import com.springdrools.demo.service.FixedDepositIntrestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/bank")
public class FixedDepositInterestPercentageController {

	private FixedDepositIntrestService depositIntrestService;

	FixedDepositInterestPercentageController(FixedDepositIntrestService depositIntrestService) {
		this.depositIntrestService = depositIntrestService;
	}

	@PostMapping("/getFDInterestRate")
	public ResponseEntity<FixeddepositIntrestRates> calculateIntrest(@RequestBody FixeddepositIntrestRates fd) {
		log.info("Controller-- Started code for getting Intrest Rate");
		return new ResponseEntity<FixeddepositIntrestRates>(depositIntrestService.calculateIntrestPercentage(fd),
				HttpStatus.OK);
	}

}
