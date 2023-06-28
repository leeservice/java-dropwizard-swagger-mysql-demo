package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.OrderService;
import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

import static org.reflections.util.ConfigurationBuilder.build;

@Api("Engineering Academy Dropwizard API")
@Path("/api")
public class OrderController {
    private OrderService orderService = new OrderService();

    @GET //accessed via http GET request
    @Path("/orders") //URL ends with /orders
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {
        try {
            return Response.ok(orderService.getAllOrders()).build();
        } catch (FailedToGetOrdersException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET //accessed via http GET request
    @Path("/order_customer_date") //URL ends with /orders
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getOrderID_CustomerID_OrderDate() {
        return orderService.getOrderID_CustomerID_OrderDate();
    }

    @GET //accessed via http GET request
    @Path("/ordered_date_list") //URL ends with /orders
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> print_ordered_list() {
        return orderService.print_ordered_list();
    }

    @GET
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersById(@PathParam("id") int id) {
        try {
            return Response.ok(orderService.getOrderById(id)).build();
        } catch (FailedToGetOrdersException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (OrderDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(OrderRequest order) {

        try {
            return Response.ok(orderService.createOrder(order)).build();
        } catch (FailedToCreateOrderException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (InvalidOrderException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @DELETE
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrder(@PathParam("id") int id) {

        try {
            orderService.deleteOrderByID(id);
            return Response.ok().build();

        } catch ( OrderDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteOrderException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }

    }

}



