package com.lambdaschool.orders.services;


import com.lambdaschool.orders.model.Customers;
import com.lambdaschool.orders.model.Orders;
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

    @Override
    public Customers findCustomersById(long id) throws EntityNotFoundException
    {
        return custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
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
        newCustomer.setAgents(agentsService.findAgentById(customers.getAgents().getAgentcode()));

        for (Orders o : customers.getOrders())
        {
            newCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrderdecription()));
        }
        return custrepos.save(newCustomer);
    }

    @Override
    public Customers update(Customers customers, long id)
    {
        Customers customer = custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (customers.getCustname() != null)
        {
            customer.setCustname(customers.getCustname());
        }
        if (customers.getCustcity() != null)
        {
            customer.setCustcity(customers.getCustcity());
        }
        if (customers.getWorkinarea() != null)
        {
            customer.setWorkinarea(customers.getWorkinarea());
        }
        if (customers.getCustcountry() != null)
        {
            customer.setCustcountry(customers.getCustcountry());
        }
        if (customers.getGrade() != null)
        {
            customer.setGrade(customers.getGrade());
        }
        if (customers.getOpeningamt() != 0)
        {
            customer.setOpeningamt(customers.getOpeningamt());
        }
        if (customers.getReceiveamt() != 0)
        {
            customer.setReceiveamt(customers.getReceiveamt());
        }
        if (customers.getPaymentamt() != 0)
        {
            customer.setPaymentamt(customers.getPaymentamt());
        }
        if (customers.getPhone() != null)
        {
            customers.setPhone(customers.getPhone());
        }

        // Adds new orders
        if (customers.getOrders().size() > 0)
        {
            for (Orders o : customers.getOrders())
            customer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), customer, o.getOrderdecription()));
        }

        return custrepos.save(customer);
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
