package com.springdrools.demo.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdrools.demo.domain.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OfferService {
	
	@Autowired
	private KieSession session;
	
	public Order getOrderedItemDiscount(Order order) {
		log.info("Service-Started code for check discount amount for the Item");
		session.insert(order);
		log.info("Getting Discount % on Item");
		session.fireAllRules();			
		return order;
	}

}
