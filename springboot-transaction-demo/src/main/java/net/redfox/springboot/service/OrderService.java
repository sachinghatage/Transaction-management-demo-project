package net.redfox.springboot.service;

import net.redfox.springboot.dto.OrderRequest;
import net.redfox.springboot.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
