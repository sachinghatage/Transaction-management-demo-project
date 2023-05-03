package net.redfox.springboot.service.impl;

import net.redfox.springboot.dto.OrderRequest;
import net.redfox.springboot.dto.OrderResponse;
import net.redfox.springboot.entity.Order;
import net.redfox.springboot.entity.Payment;
import net.redfox.springboot.exception.PaymentException;
import net.redfox.springboot.repository.OrderRepository;
import net.redfox.springboot.repository.PaymentRepository;
import net.redfox.springboot.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order=orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment=orderRequest.getPayment();
        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Card Type Not Supported");
        }
        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setOrderTackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");
        return orderResponse;
    }
}
