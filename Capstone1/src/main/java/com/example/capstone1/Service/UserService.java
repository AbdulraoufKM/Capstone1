package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MerchantService merchantService;
    private final ProductService productService;
    private final MerchantStockService merchantStockService;



    ArrayList<User> users = new ArrayList<>();
    ArrayList<Product> getBuy = new ArrayList<>();



    //======================CRUD============================
    public ArrayList<User> getUsers() {
        return users;
    }
    public ArrayList<Product> getPurchasedProducts() {
        return getBuy;
    }


    public boolean addUser(User user){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())){
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public boolean updateUser(User user , String id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for (User user1 : users){
            if (user1.getId().equals(id)){
                users.remove(user1);
                return true;
            }
        }
        return false;
    }


    public User getUserById(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                return users.get(i);
            }
        }
        return null;
    }


    //============================Endpoint==========================

    //#3: discount for members
    public int buyProduct(String userId, String productId, String merchantId) {
        User user = getUserById(userId);
        if (user == null) {
            return 0;
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            return 1;
        }

        Merchant merchant = merchantService.getMerchantById(merchantId);
        if (merchant == null) {
            return 2;
        }

        MerchantStock merchantStock = merchantStockService.checkStock(productId, merchantId);
        if (merchantStock == null || merchantStock.getStock() <= 0) {
            return 3;
        }

        double price = product.getPrice();

        if (user.isMember()) {
            price = price - (price * 0.10);
        }

        if (user.getBalance() < price) {
            return 4;
        }

        user.setBalance(user.getBalance() - price);
        merchantStock.setStock(merchantStock.getStock() - 1);

        getBuy.add(product);

        return 5;
    }

    //#4: Range price
    public ArrayList<Product> getRangPriceCategory(String categoryId, int p1, int p2) {
        ArrayList<Product> products = new ArrayList<>();

        if (p1 > p2 || p1 < 0 || p2 < 0) {
            return new ArrayList<>();
        }

        for (Product product1 : productService.products) {
            if (product1.getCategoryID().equals(categoryId) &&
                    product1.getPrice() >= p1 && product1.getPrice() <= p2) {
                products.add(product1);
            }
        }

        return products;
    }
















    //============================Extra point=========================
    public boolean changeUsername(String id,String oldName,String newName){
        for (User user : users) {
            if (user.getId().equals(id)) {
                if (user.getUsername().equals(oldName)) {
                    user.setUsername(newName);
                    return true;
                }
            }
        }
        return false;
    }




}
