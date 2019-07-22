package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.model.Customers;
import com.lambdaschool.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/data")
public class DataController
{
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/customer/new", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid
                                            @RequestBody Customers newCustomer) throws URISyntaxException
    {
        newCustomer = customerService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{custid}").buildAndExpand(newCustomer.getCustomercode()).toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}