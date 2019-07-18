package com.lambdaschool.orders.services;


import com.lambdaschool.orders.model.Customers;
import com.lambdaschool.orders.repos.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImp implements CustomerService
{
    @Autowired
    private CustomersRepository custrepos;

    @Autowired
    private AgentsService agentsService;

    @Override
    public List<Customers> findAllCustomer()
    {
        List<Customers> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customers findCustomerByName(String name)
    {
        Customers customer = custrepos.findByCustname(name);

        if (customer == null)
        {
            throw new EntityNotFoundException("Customer " + name + "not found");
        }
        return customer;
    }

    @Transactional
    @Override
    public Customers save(Customers customers)
    {
        Customers newCustomer = new Customers();

        newCustomer.setCustname(customers.getCustname());
        newCustomer.setCustcity(customers.getCustcity());
        newCustomer.setWorkinarea(customers.getWorkinarea());
        newCustomer.setCustcountry(customers.getCustcountry());
        newCustomer.setGrade(customers.getGrade());
        newCustomer.setOpeningamt(customers.getOpeningamt());
        newCustomer.setReceiveamt(customers.getReceiveamt());
        newCustomer.setPaymentamt(customers.getPaymentamt());
        newCustomer.setOutstandingamt(customers.getOutstandingamt());
        newCustomer.setPhone(customers.getPhone());
        newCustomer.setAgents(agentServic);
        return custrepos.save(newCustomer);
    }

    @Override
    public Customers update(Customers customers, long id)
    {
        return null;
    }

    @Override
    public void delete(long id)
    {
        if (custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }
}
