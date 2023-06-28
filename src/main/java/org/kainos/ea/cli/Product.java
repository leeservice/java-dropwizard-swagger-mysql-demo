package org.kainos.ea.cli;

import java.sql.Date;

public class Product implements Comparable<Product>{

    private int productID;
    private String name;
    private String description;
    private double price;

    public Product(int productID, String name, String description, double price, Date createdDate) {
        setProductID(productID);
        setName(name);
        setDescription(description);
        setPrice(price);
    }
    public Product(int productID, String name, String description, double price) {
        setProductID(productID);
        setName(name);
        setDescription(description);
        setPrice(price);
    }


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    @Override
//    public int compareTo(Product product) {
//        return this.getName().compareTo(product.getName());
//    }

    //for comparator
    @Override
    public int compareTo(Product product) {
        return Double.compare(this.getPrice(), product.getPrice());
    }

    //to string modification for printing out objects
    @Override
    public String toString() {
        return "Product name: "+this.getName() + ", Product Price: £"+ this.getPrice();
    }
}
