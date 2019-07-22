package com.lambdaschool.orders.services;

import com.lambdaschool.orders.repos.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrdersServiceImp implements OrdersService
{
    @Autowired
    private OrdersRepository ordersrepos;
}
