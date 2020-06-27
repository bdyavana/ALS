package com.ibm.orderservice.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.orderservice.domain.Order;

@Component
public class OrderMapper {
	
	public Order convertOrdersDTOToOrders (OrderDTO aOrdersDTO) {
		Order aOrders = new Order();
		aOrders.setOrderId(aOrdersDTO.getOrderId());
		aOrders.setProductId(aOrdersDTO.getProductId());
		aOrders.setUserId(aOrdersDTO.getUserId());
		return aOrders;
	}
	
	public OrderDTO convertOrdersToOrdersDTO (Order aOrders) {
		OrderDTO aOrdersDTO = new OrderDTO();
		aOrdersDTO.setOrderId(aOrders.getOrderId());
		aOrdersDTO.setProductId(aOrders.getProductId());
		aOrdersDTO.setUserId(aOrders.getUserId());
		return aOrdersDTO;
	}
	
	public OrderDTO convertOrderListToOrderDTOList(List<Order> aOrderList) {
		OrderDTO aOrdersDTO = new OrderDTO();
		aOrdersDTO.setOrderList(aOrderList);
		return aOrdersDTO;
	}

}
