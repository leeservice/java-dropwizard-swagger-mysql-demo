package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class OrderRequest {
//    private int orderID;
    private int customerID;
    private Date orderDate;

    @JsonCreator
    public OrderRequest(
            @JsonProperty("customerId") int customerID,
            @JsonProperty("orderDate") Date orderDate){
        this.customerID = customerID;
        this.orderDate = orderDate;
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

//    public void setOrderID(int orderID){
//        this.orderID = orderID;
//    }
//
//    public int getOrderID(){
//        return orderID;
//    }





}
