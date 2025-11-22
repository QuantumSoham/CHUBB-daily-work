package com.chubb.controller;
import com.chubb.request.*;
import com.chubb.service.OrderService;
import com.chubb.request.Order1;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OrderController {

	@Autowired
	OrderService service;
	@GetMapping("/order")
	String getOrder()
	{
		return "hello there";
	}
	
	@PostMapping("/order")
	float saveOrder(@RequestBody @Valid Order1 order)
	{
		
		log.debug("logger added");
		service.insertOrder(order);
		return order.getPrice();
	}
	

}
