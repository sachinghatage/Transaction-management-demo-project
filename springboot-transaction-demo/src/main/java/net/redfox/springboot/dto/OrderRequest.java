package net.redfox.springboot.dto;

import lombok.Getter;
import lombok.Setter;
import net.redfox.springboot.entity.Order;
import net.redfox.springboot.entity.Payment;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
