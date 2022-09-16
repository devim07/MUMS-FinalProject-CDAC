package com.project.mums.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mums.payload.OrderDto;
import com.project.mums.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrdersController {
	
	@Autowired
	public OrderService orderService;
	
	
	
	@GetMapping("")
	public ResponseEntity<List<OrderDto>> getAllOrder(){
		return ResponseEntity.ok(this.orderService.getAllOrder());
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> getOrderById(@PathVariable int id){
		return ResponseEntity.ok(this.orderService.getOrderById(id));
	}
	
	
	
	@PostMapping( "/")
	public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto){
		OrderDto createdOrder=this.orderService.createOrder(orderDto);
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderDto orderDto, @PathVariable int id){
		return ResponseEntity.ok(this.orderService.updateOrder(orderDto, id));
	}
	

}
