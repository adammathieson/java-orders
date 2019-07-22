package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.model.Customers;
import com.lambdaschool.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/order",
                produces = {"application/json"})
    public ResponseEntity<?> allCustomersOrders()
    {
        List<Customers> theCustomers = customerService.findAllCustomer();
        return new ResponseEntity<>(theCustomers, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{custname}",
                produces = {"application/json"})
    public ResponseEntity<?> getCustomerOrdersByName(@PathVariable String custname)
    {
        Customers c = customerService.findCustomerByName(custname);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

}
