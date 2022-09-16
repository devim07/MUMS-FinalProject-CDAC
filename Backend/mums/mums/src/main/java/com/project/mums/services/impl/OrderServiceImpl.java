package com.project.mums.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.project.mums.entities.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.mums.exceptions.ResourceNotFoundException;
import com.project.mums.payload.OrderDto;
import com.project.mums.repository.OrderRepo;
import com.project.mums.services.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public OrderDto createOrder(OrderDto orderDto) {
		Order order=this.dtoToOrder(orderDto);
		Order savedOrder=this.orderRepo.save(order);
		return orderToDto(savedOrder);

		
	}

	

	@Override
	public OrderDto updateOrder(OrderDto orderDto, int orderno) {
		Order order=this.orderRepo.findById(orderno)
				.orElseThrow(()->
				new ResourceNotFoundException("Order", "Order ID", orderno)); 
		order.setCustno(orderDto.getCustno());
		order.setOrderUnit(orderDto.getOrderUnit());
		order.setOrderDate(orderDto.getOrderDate());
	    order.setStatus(orderDto.getStatus());
		order.setBatchno(orderDto.getBatchno());
		Order updatedOrder=this.orderRepo.save(order);
		return orderToDto(updatedOrder);
	}

	@Override
	public OrderDto getOrderById(int orderno) {
		Order order=this.orderRepo.findById(orderno)
				.orElseThrow(()->
				new ResourceNotFoundException("Order", "Order ID", orderno)); 
		return orderToDto(order);
	}

	@Override
	public List<OrderDto> getAllOrder() {
		List<Order> orders = this.orderRepo.findAll();
		List<OrderDto> orderDtos = orders.stream().map(order->this.orderToDto(order)).collect(Collectors.toList());
		return orderDtos;
	}

	@Override
	public void deleteOrder(int orderno) {
		Order order = this.orderRepo.findById(orderno)
				.orElseThrow(()->
				new ResourceNotFoundException("Order", "Order ID", orderno));
				this.orderRepo.delete(order);
	}
	
	
	
	private OrderDto orderToDto(Order order) {
		OrderDto orderDto=this.modelMapper.map(order, OrderDto.class);
		return orderDto;
	}

	private Order dtoToOrder(OrderDto orderDto) {
		Order order=this.modelMapper.map(orderDto, Order.class);
		return order;
	}
	
	
	
}

