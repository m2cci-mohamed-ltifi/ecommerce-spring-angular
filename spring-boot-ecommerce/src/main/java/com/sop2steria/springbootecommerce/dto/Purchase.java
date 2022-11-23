package com.sop2steria.springbootecommerce.dto;

import com.sop2steria.springbootecommerce.entity.Address;
import com.sop2steria.springbootecommerce.entity.Customer;
import com.sop2steria.springbootecommerce.entity.Order;
import com.sop2steria.springbootecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address billingAddress;
    private Address shippingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
