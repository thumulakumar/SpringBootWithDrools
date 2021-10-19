package com.springdrools.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdrools.demo.domain.Order;
import com.springdrools.demo.service.OfferService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/offer")
@Slf4j
public class OfferController {
	
	private OfferService offerService;
	
	OfferController(OfferService offerService){
		this.offerService = offerService;
	}

	@PostMapping("/orderItem")
	public ResponseEntity<Order> orderItem(@RequestBody Order order){
		log.info("Controller-Started code for check discount amount for the Item");
		return new ResponseEntity<Order>(offerService.getOrderedItemDiscount(order),HttpStatus.OK);
	}
}
