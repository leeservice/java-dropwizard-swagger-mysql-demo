package org.kainos.ea.core;

import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.ProductRequest;

import java.util.Date;

public class OrderValidator {
    public String isValidOrder(OrderRequest order){
        Date cutOffYear = new java.sql.Date(2022,1,01);

        if(order.getCustomerID() <= 0){
            return "Customer ID is less or equal to 0";
        }

        if (order.getOrderDate().after(cutOffYear )){
            return "Order Date is older than 2022-01-01";
        }

        return null;
    }

}
