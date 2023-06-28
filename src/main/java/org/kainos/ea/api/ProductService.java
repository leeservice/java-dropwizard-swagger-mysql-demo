package org.kainos.ea.api;

import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProductValidator;
import org.kainos.ea.db.ProductDAO;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    //Old code for displaying JSON data:
//    List <Product> productList = new ArrayList<>();
//    public List<Product> getAllProducts(){
//        Product product1 = new Product(1,"Coffee Cup","350ml coffee cup", 3.99);
//        Product product2 = new Product(2,"Macbook", "Macbook 10", 615.00);
//        Product product3 = new Product(3,"Macbook", "Macbook 10", 615.00);
//
//        productList.add(product1);
//        productList.add(product2);
//        productList.add(product3);
//
//        return productList; }

    private ProductDAO productDao = new ProductDAO();
    private ProductValidator productValidator = new ProductValidator();

    public List<Product> getAllProducts() throws FailedToGetProductsException {
        List<Product> productList = null;
        try {
            productList = productDao.getAllProducts();
        } catch (SQLException e) {
            throw new FailedToGetProductsException();
        }

        try {
            Product product = productList.get(100000);
        } catch (IndexOutOfBoundsException e){
            System.err.println(e.getMessage());
        }
        return productList;


        // double totalPriceOfProduct = 0;
    //for
//        for(int i = 0; i < productList.size(); i++){
//            totalPriceOfProduct += productList.get(i).getPrice();
//        }

        //foreach
//        for (Product product : productList){
//            totalPriceOfProduct += product.getPrice();
//        }

        //iterator 1
//        Iterator<Product> productIterator = productList.iterator();
//
//        while(productIterator.hasNext()){
//            Product product = productIterator.next();
//            totalPriceOfProduct += product.getPrice();
//        }

        //iterator 2
//        Iterator <Product> productIterator = productList.iterator();
//
//        do {
//            Product product = productIterator.next();
//            totalPriceOfProduct += product.getPrice();
//        } while (productIterator.hasNext());

        // stream
//        totalPriceOfProduct = productList.stream().mapToDouble(product -> product.getPrice()).sum();

//        //for each loop to print all products in db that cost than £100
//        double totalPriceOfCheapProduct = 0;
//        double totalPriceOfExpensiveProduct = 0;
//
//        for(Product product : productList){
//            if (product.getPrice() < 100){
//                totalPriceOfCheapProduct += product.getPrice();
//            } else{
//                totalPriceOfExpensiveProduct += product.getPrice();
//            }
//        }
//
////        System.out.println("Total price of all products: "+ totalPriceOfProduct);
//        System.out.println("Total price of cheap products: £"+ totalPriceOfCheapProduct);
//        System.out.println("Total price of expensive products: £"+ totalPriceOfExpensiveProduct);

        //get price of specific products with a default message for anything we don't select
//        for(Product product: productList) {
//            switch (product.getName()) {
//                case ("Rubber Duck"):
//                    System.out.println("This is the rubber duck price:" + product.getPrice());
//                    break;
//
//                case ("Big mug"):
//                    System.out.println("This is the big mug price:" + product.getPrice());
//                    break;
//
//                case ("Big Cheese"):
//                    System.out.println("This is the big cheese price:" + product.getPrice());
//                    break;
//
//                case ("Party Hat"):
//                    System.out.println("This is the party hat price:" + product.getPrice());
//                    break;
//                default:
//                    System.out.println("This is the other price: £"+ product.getPrice());
//            }
//           }

        // Create a list of integers with at least one duplicate and print out the list

        // michaels examples for streams
//        List<Integer> intList = Arrays.asList(1,2,2,3,4,5);
//        intList.stream().forEach(System.out::println);
//        intList.forEach(System.out::println);
//        for (int myInteger : intList) {
//            System.out.println(myInteger);
//        }

        //Create a set of integers and assign it the values from the list created above and print out the set
        // - the set can only be used to store unique values - prints 1 2 3 4 5 and eliminates the duplicate 2
//        List<Integer> intList = Arrays.asList(1,2,2,3,4,5);
//        Set<Integer> intSet = new HashSet<>(intList);
//
//        intSet.stream().forEach(System.out::println);

        //if we do the following, we need to update the productList to implement the comparable interface
        // this allows us to search through a sorted product List
//        Collections.sort(productList);
//        productList.stream().forEach(System.out::println);

        // print the minimum item in the list
//        System.out.println(Collections.min(productList));
//
//        // print maximum item in the list
//        System.out.println(Collections.max(productList));
//
//        //print only products with a value higher than 10
//        productList.stream().filter(product -> product.getPrice() > 10).forEach(System.out::println);

        // create a new list with prouducts and print only products with a price lower than 10
//        List<Product> cheapProducts =
//        productList.stream().filter(product -> product.getPrice() < 10).collect(Collectors.toList());
//        cheapProducts.forEach(System.out::println);
    }

    public Product getProductById(int id) throws FailedToGetProductsException, ProductDoesNotExistException {
    try {
        Product product = productDao.getProductById(id);

        if (product == null) {
            throw new ProductDoesNotExistException();
        }
        return product;
    } catch (SQLException e){
        System.err.println(e.getMessage());
        throw new FailedToGetProductsException();
    }
    }

    public int createProduct(ProductRequest product) throws FailedToCreateProductException, InvalidProductException {

        try {

            String validation = productValidator.isValidProduct(product);

            if(validation != null){
                throw new InvalidProductException(validation);
            }

            int id = productDao.createProduct(product);

        if(id == -1) {
            throw new FailedToCreateProductException();
        }
        return id;
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateProductException();
        }

    }

    public void updateProduct(int id, ProductRequest product) throws FailedToUpdateProductException, ProductDoesNotExistException, InvalidProductException {

        try {
            String validation = productValidator.isValidProduct(product);

            // do we need this when we have already checked the id exists in db?
            // we could remove the validation check and go ahead with the productDao.updateProduct
            if (validation != null) {
                throw new InvalidProductException(validation);
            }

            Product productToUpdate = productDao.getProductById(id);

            if (productToUpdate == null) {
                throw new ProductDoesNotExistException();
            }

            productDao.updateProduct(id, product);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateProductException();
        }

    }

    public void deleteProduct(int id) throws ProductDoesNotExistException, FailedToDeleteProductException {
        try {
            Product productToDelete = productDao.getProductById(id);

            if (productToDelete == null) {
                throw new ProductDoesNotExistException();
            }

            productDao.deleteProduct(id);
        } catch(SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToDeleteProductException();
        }
    }

}
