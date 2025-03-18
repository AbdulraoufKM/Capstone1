package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    private final UserService userService;

    @Autowired
    @Lazy
    public ProductService(UserService userService) {
        this.userService = userService;
    }

    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Product> WishList = new ArrayList<>();


    public ArrayList<Product> getProducts() {
        return products;
    }


    public boolean add(Product product){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())){
                return false;
            }
        }
        products.add(product);
        return true;
    }

    public boolean update(Product product, String id){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)){
                products.set(i,product);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id){
        for (Product product1 : products){
            if (product1.getId().equals(id)){
                products.remove(product1);
                return true;
            }
        }
        return false;
    }

    public int price(String id){
        for (Product product1 : products){
            if (product1.getId().equals(id)){
                return product1.getPrice();
            }
        }
        return 0;
    }

    public Product getProductById (String id){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                return products.get(i);
            }
        }
        return null;
    }

    //==================Extra Endpoint ============================
    //#2

    public String addProductToWishList (String userId, String productId){

        User user = userService.getUserById(userId);
        if (user == null) {
            return " User Id not found ";
        }

        Product product = getProductById(productId);
        if (product == null) {
            return " Product Id not found ";
        }

        if (WishList.contains(product)) {
            return "Product is already in your wishlist";
        }

        WishList.add(product); // Add Product to wish List
        return " Product added successfully";
    }


    public ArrayList<Product> getWishList (String userId){
        User user = userService.getUserById(userId);
        if (user == null) {
            return null;
        }
        return WishList;
    }

    //#4

    public String changeProductName(String userId, String productId, String newName) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return "User not found";
        }

        if (!user.getRole().equals("Admin")) {
            return " Only Admins can change product names";
        }

        Product product = getProductById(productId);
        if (product == null) {
            return "Product not found";
        }

        product.setName(newName);
        return "Product name updated successfully";
    }











}
