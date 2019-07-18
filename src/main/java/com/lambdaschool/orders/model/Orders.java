package com.lambdaschool.orders.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;

    private double ordamount;
    private double advanceamount;

    @OneToMany
    @JoinColumn(name = "custcode",
                nullable = false)
    @JsonIgnoreProperties("orders")
    private Customers customers;

    public Orders(double ordamount)
    {
        this.ordamount = ordamount;
    }

    public Orders(double ordamount, double advanceamount, Customers customers)
    {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.customers = customers;
    }

    public long getOrdnum()
    {
        return ordnum;
    }

    public void setOrdnum(long ordnum)
    {
        this.ordnum = ordnum;
    }

    public double getOrdamount()
    {
        return ordamount;
    }

    public void setOrdamount(double ordamount)
    {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount()
    {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount)
    {
        this.advanceamount = advanceamount;
    }

    public Customers getCustomers()
    {
        return customers;
    }

    public void setCustomers(Customers customers)
    {
        this.customers = customers;
    }
}
