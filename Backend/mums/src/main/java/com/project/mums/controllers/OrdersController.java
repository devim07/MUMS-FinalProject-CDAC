package com.project.mums.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("http://localhost:3000")
public class OrdersController {
	
	@Autowired
	public OrderService orderService;
	
	
	
	@GetMapping("")
	public ResponseEntity<List<OrderDto>> getAllOrder(){
		return ResponseEntity.ok(this.orderService.getAllOrder());
	}
	
	
	
	@GetMapping("/{orderno}")
	public ResponseEntity<OrderDto> getOrderById(@PathVariable int orderno){
		return ResponseEntity.ok(this.orderService.getOrderById(orderno));
	}
	
	
	@GetMapping("/customer/{custno}")
	public ResponseEntity<List<OrderDto>> getAllOrderOfCust(@PathVariable int custno){
		return ResponseEntity.ok(this.orderService.getAllOrderOfCust(custno));
	}
	
	
	@GetMapping("/salesman/{salesno}")
	public ResponseEntity<List<OrderDto>> getAllOrderOfSalesman(@PathVariable String salesno){
		return ResponseEntity.ok(this.orderService.getAllOrderOfSalesman(salesno));
	}
	
	
	@GetMapping("/salesman/all")
	public ResponseEntity<Map<String,Integer>> getAllOrderOfAllSalesman(){
		return ResponseEntity.ok(this.orderService.getAllOrderOfAllSalesman());
	}
	
	
	@GetMapping("/customer/all")
	public ResponseEntity<Map<String,Integer>> getAllOrderOfAllCustomer(){
		return ResponseEntity.ok(this.orderService.getAllOrderOfAllCustomer());
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