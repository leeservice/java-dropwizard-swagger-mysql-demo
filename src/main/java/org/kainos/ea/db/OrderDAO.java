package org.kainos.ea.db;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderDAO {
    private static Connection conn;

    private static Connection getConnection() throws SQLException {
        String user, password, host, name;

        if (conn != null && !conn.isClosed()) {
            return conn;
        }
        try {
            FileInputStream propsStream = new FileInputStream("db.properties");

            Properties props = new Properties();
            props.load(propsStream);

            user = props.getProperty("user");
            password = props.getProperty("password");
            host = props.getProperty("host");
            name = props.getProperty("name");

            if (user == null || password == null || host == null)
                throw new IllegalArgumentException("Properties file must exist " + "and must contain user, password, name and host properties");

            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name + "?useSSL=false", user, password);
            return conn;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Order> getAllOrders() throws SQLException {
            Connection c = getConnection();
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT OrderID, CustomerID, OrderDate from `Order`;");

            List<Order> orderList = new ArrayList<>();

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getDate("OrderDate")
                );

                orderList.add(order);
            }
            return orderList;
    }

    public Order getOrderById(int id) throws SQLException {
        Connection c = getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT OrderID, CustomerID, DispatchDate, OrderDate,ArrivedDate" +" FROM `Order` where OrderID = " + id);

        while (rs.next()) {
            return new Order(
                    rs.getInt("OrderID"),
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            );

        }
        return null;
    }

    public int createOrder(OrderRequest order) throws SQLException {
        Connection c = getConnection();

        String insertStatement = "INSERT INTO `Order` (CustomerID, OrderDate) VALUES (?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1,order.getCustomerID());
        //set date was causing issues - so we're casting the date - but we're not sure if this works
        st.setDate(2, (Date) order.getOrderDate());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public Order deleteOrderById(int id) throws SQLException{
        Connection c = getConnection();

        String deleteStatement = "DELETE FROM `Order` WHERE OrderID = ?";

        PreparedStatement st = c.prepareStatement(deleteStatement);

        st.setInt(1, id);

        st.executeUpdate();
        return null;
    }




}
