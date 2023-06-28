package org.kainos.ea.cli;

import org.joda.time.DateTime;

import java.util.Date;

public class Order implements Comparable<Order>{
    private int orderID;
    private int customerID;
    private Date orderDate;


    // constructors are methods which allow us to create an object which has the type Order
    // the order does not have a return type - this is unique to constructor methods
    // the below has 3 params
    public Order(int orderID, int customerID, Date orderDate) {
        setOrderID(orderID);
        setCustomerID(customerID);
        setOrderDate(orderDate);
    }

    public int getOrderID() {
        return orderID;
    }

    // setters take a value as part of their method signiture - int orderID is a parameter
    // their void return type is because the methods do not return any data whereas
    // getter methods have all return types which correlate to the data type of the variable
    // - e.g. getOrder id returns an int
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    //for 21 we created a tostring to help print out the object
    @Override
    public String toString() {
        return "OrderID = "+ this.getOrderID() + ", CustomerID = "+ this.getCustomerID()+ ", "+ this.getOrderDate();
    }

    @Override
    public int compareTo(Order order) {
        return this.getOrderDate().compareTo(order.getOrderDate());
    }

}


