package com.ibm.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.orderservice.domain.Order;
import com.ibm.orderservice.dto.OrderDTO;
import com.ibm.orderservice.service.OrderServiceImpl;

@RequestMapping ("/orderservice")
@RestController
public class OrderController {
	
	@Autowired
	OrderServiceImpl aOrderServiceImpl;
	
	@PostMapping("/orders")
	public ResponseEntity<OrderDTO> createOrder(@RequestBody Order aOrder, @RequestHeader("Authorization") String authHeader) {
		OrderDTO createOrder = aOrderServiceImpl.createOrder(aOrder, authHeader);
		return ResponseEntity.ok().body(createOrder);
	}
	
	@GetMapping ("/orders/user/{userid}")
	public ResponseEntity<OrderDTO> findAllOrdersOfUser(@PathVariable (value ="userid") Long userId, @RequestHeader("Authorization") String authHeader) {
		OrderDTO allOrdersOfUser = aOrderServiceImpl.getAllOrdersOfUser(userId, authHeader);
		if(allOrdersOfUser != null) {
			return ResponseEntity.ok().body(allOrdersOfUser);
		} else {
			//TODO -- Add HTTP status not found
			return ResponseEntity.ok().body(allOrdersOfUser);
		}
	}
	
	@GetMapping ("/orders/user/{userid}/order/{orderid}")
	public ResponseEntity<OrderDTO> findOrderByUserIdAndOrderId(@PathVariable (value ="userid") Long userId, @PathVariable (value ="orderid") Long orderId, @RequestHeader("Authorixation") String authHeader) {
		OrderDTO orderDetailsByUserIdAndOrderId = aOrderServiceImpl.getOrderDetailsByUserIdAndOrderId(userId, orderId, authHeader);
		return ResponseEntity.ok().body(orderDetailsByUserIdAndOrderId);
	}
}
