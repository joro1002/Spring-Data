package com.example.xmlprocessing.entities;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sales extends BaseEntity{
    private int discount;
    private Cars car;
    private Customers customer;

    public Sales(int discount) {
        this.discount = discount;
    }

    public Sales() {
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @OneToOne
    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    @ManyToOne
    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
}
