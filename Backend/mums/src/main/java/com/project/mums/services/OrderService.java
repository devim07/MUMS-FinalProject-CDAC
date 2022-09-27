package com.project.mums.services;

import java.util.List;
import java.util.Map;

import com.project.mums.payload.OrderDto;

public interface OrderService {
	OrderDto createOrder (OrderDto orderDto) ;
	OrderDto updateOrder (OrderDto orderDto, int orderno) ;
	OrderDto getOrderById (int orderno) ;
	List<OrderDto> getAllOrder();
//	void deleteOrder (int orderno);
	List<OrderDto> getAllOrderOfCust(int custno);
	List<OrderDto> getAllOrderOfSalesman(String salesno);
	Map<String, Integer> getAllOrderOfAllSalesman();
}