package org.kainos.ea.api;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.OrderValidator;
import org.kainos.ea.db.OrderDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    // create a method which returns a list of orders
    private OrderDAO orderDao = new OrderDAO();
    private OrderValidator orderValidator = new OrderValidator();

//    public List<Order> getAllOrders() {
//        List<Order> orderList = orderDao.getAllOrders();
//
////         instantiates a new arrayList of Orders which is empty
////        List<Order> orderList = new ArrayList<>();
//
//        // create two order objects
//        Order order1 = new Order(1, 1, new Date());
//        Order order2 = new Order(2, 1, new Date());
//        // add these object orders to the list and return the list from the method
//        orderList.add(order1);
//        orderList.add(order2);
//
//        return orderList;
//    }


    // 21. Update your `OrderService` and `Order` classes
    // to print out the `OrderID`, `CustomerID` and `OrderDate` of all orders
//    public List<Order> getOrderID_CustomerID_OrderDate() {
//        List<Order> orderList = orderDao.getAllOrders();
//        System.out.println(orderList.toString());
//        return orderList;
//    }
//        public List<Order> getOrderID_CustomerID_OrderDate() {
//            List<Order> orderList = orderDao.getAllOrders();
//        for(Order o : orderList){
//        System.out.println(o.getOrderID() + " " + o.getCustomerID() + " " + o.getOrderDate());
//    }

    //using a stream to do the same thing
    public List<Order> getAllOrders() throws FailedToGetOrdersException {
        List<Order> orderList = null;
        try {
            orderList = orderDao.getAllOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        orderList.stream().forEach(System.out::println);
        return orderList;
    }

    // add comparable to the order class and add the compareTo method
//    public List<Order> getAllOrders() {
//        List<Order> orderList = orderDao.getAllOrders();
//        orderList.stream().forEach(System.out::println);
//        //using comparator
//        orderList.stream().sorted(Comparator.comparing(Order::getOrderDate))
//                .forEach(System.out::println);
//        return orderList;
//    }


    //22. update your orderservice and order classes to print out order the list by order date descending
    public List<Order> getOrderID_CustomerID_OrderDate() {
        List<Order> orderList = null;
        try {
            orderList = orderDao.getAllOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(orderList.toString());
        return orderList;
    }

    public List<Order> print_ordered_list() {
        List<Order> orderList = null;
        try {
            orderList = orderDao.getAllOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(orderList);
        Collections.reverse(orderList);
        for (Order order : orderList) {
            System.out.println(order);
        }
        return orderList;
    }

    public Order getOrderById(int id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try {
            Order order = orderDao.getOrderById(id);

            if (order == null) {
                throw new OrderDoesNotExistException();
            }
            return order;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetOrdersException();
        }

        //Update your `OrderService` to only show orders from the last week

        // public List<Order> orders_from_last_week() {
        //get current date
        //Date dateToday = DateUtils.MILLIS_PER_DAY;
//        Date today = new Date();
//        Calendar cal = new GregorianCalendar();
//        cal.add(Calendar.DAY_OF_MONTH, -7);
//        Date olddate = cal.getTime();
//        List<Order> orderList = orderDao.getAllOrders();
//        orderList.stream().filter(order -> order.getOrderDate().after(olddate)).sorted().forEach(System.out::println);
//        return orderList;


//        orderList
//                .stream()
//                .filter(order -> order.getCustomerID() == 1)
//                .sorted(Comparator.comparing(Order:getOrderID_CustomerID_OrderDate()
//                .forEach(System.out::println);
//        return orderList;

//
//        long dateTodayInMilliseconds = dateToday.getTime();
//        List<Order> orderList = orderDao.getAllOrders();
//        orderList.stream().filter(order -> order.getOrderDate().after((dateTodayInMilliseconds -  ) ) );

//        for(Order order : orderList){
//            System.out.println(order);
//        }

        //get customer with most recent order

//        public List<Order> orders_most() {
//        List<Order> orderList = orderDao.getAllOrders();
//
//        orderList
//                .stream()
//                .filter(order -> order.getCustomerID() == 1)
//                .sorted(Comparator.comparing(Order::getOrderDate))
//                .forEach(System.out::println);
//
//            System.out.println("Newest Order date: " + Collections.max((orderList)));
//            System.out.println("Oldest Order date: " + Collections.min((orderList)));
//            System.out.println("Total Count "+ orderList.size());
//
//            Integer mostOrders = orderList.stream()
//                    .collect(Collectors.groupingBy(Order::getCustomerID,Collectors.counting()))
//                    .entrySet().stream().max(Map.Entry.comparingByValue())
//                    .map(Map.Entry::getKey).orElse(null);
//
//            System.out.println("Customer with most orders: "+mostOrders);
//
//            Integer leastOrders = orderList.stream()
//                    .collect(Collectors.groupingBy(Order::getCustomerID,Collectors.counting()))
//                    .entrySet().stream().min(Map.Entry.comparingByValue())
//                    .map(Map.Entry::getKey).orElse(null);
//
//            System.out.println("Customer with most orders: "+leastOrders);

        // Update your `OrderService` to show the total count of all orders
//    public List<Order> getAllOrders() {
//        List<Order> orderList = orderDao.getAllOrders();
//        System.out.println(orderList.size());
//
//        return orderList;
//    }


        //Update your `OrderService` to show the customer ID with the most orders
//    public List<Order> getAllOrders(){
//        List<Order> orderList = orderDao.getAllOrders();
//        orderList.stream().max(Comparator.comparing(Order::getCustomerID));
//
//        System.out.println();
//        return orderList;
//    }
    }

    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException {

        try {

            String validation = orderValidator.isValidOrder(order);

            if (validation != null) {
                throw new InvalidOrderException(validation);
            }

            int id = orderDao.createOrder(order);

            if (id == -1) {
                throw new FailedToCreateOrderException();
            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateOrderException();
        }
    }

    public void deleteOrderByID(int id) throws OrderDoesNotExistException, FailedToDeleteOrderException {
        try {
            Order orderToDelete = orderDao.deleteOrderById(id);

            if (orderToDelete == null) {
                throw new OrderDoesNotExistException();
            }

            orderDao.deleteOrderById(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToDeleteOrderException();
        }
    }

}
