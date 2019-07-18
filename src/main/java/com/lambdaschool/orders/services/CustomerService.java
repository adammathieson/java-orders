package com.lambdaschool.orders.services;

import com.lambdaschool.orders.model.Customers;

import java.util.List;

public interface CustomerService
{
    List<Customers> findAllCustomer();

    Customers findCustomerByName(String name);

    Customers save(Customers customers);

    Customers update(Customers customers, long id);

    void delete(long id);

}

