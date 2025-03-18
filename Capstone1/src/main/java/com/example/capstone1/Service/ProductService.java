package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final UserService userService;

    @Autowired
    @Lazy
    public ProductService(UserService userService) {
        this.userService = userService;
    }

    ArrayList<Product> products = new ArrayList<>();


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

    //================== Endpoint ============================

    //#1 wishlist
    public int addProductToWishlist(String userID, String productID) {
        for (User user : userService.getUsers()) {
            if (user.getId().equals(userID)) {
                if (user.getWishlist() == null) {
                    user.setWishlist(new ArrayList<>());
                }
                for (Product product : user.getWishlist()) {

                    if (product.getId().equals(productID))
                        return 1;
                }
                Product productToAdd = getProductById(productID);
                if (productToAdd != null) {
                    user.getWishlist().add(productToAdd);
                    return 2;
                }
            }
        }
        return 3;
    }

    public ArrayList<Product> getWishlistProducts(String userID) {
        for (User user : userService.getUsers()) {
            if (user.getId().equals(userID)) {
                return user.getWishlist();
            }
        }
        return new ArrayList<>();
    }

    //-------------------------------
    //#2 review
    public String addReviewAndRating(String productId, String review, int rating) {
        Product product = getProductById(productId);
        if (product == null) {
            return "Product not found";
        }
        if (product.getReviews() == null) {
            product.setReviews(new ArrayList<>());
        }
        if (product.getRatings() == null) {
            product.setRatings(new ArrayList<>());
        }

        if (rating < 1 || rating > 5) {
            return "Rating must be between 1 and 5";
        }

        product.getReviews().add(review);
        product.getRatings().add(rating);

        return "Review and rating added successfully";
    }

    public ArrayList<String> getReviews(String productId) {
        Product product = getProductById(productId);
        return (product != null) ? product.getReviews() : new ArrayList<>();
    }

    public double getAverageRating(String productId) {
        Product product = getProductById(productId);
        if (product == null || product.getRatings().isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (int rating : product.getRatings()) {
            sum += rating;
        }

        return sum / product.getRatings().size();
    }




    //-------


    //Extra 2
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
