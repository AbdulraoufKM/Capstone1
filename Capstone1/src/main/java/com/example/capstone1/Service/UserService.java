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


    public int buyProduct(String userId , String productId , String merchantId ) {


        for(User user1 : users) {
            if (!user1.getId().equals(userId)) {
                return 0;
            }
        }

        for (Product product1 : productService.products) {
            if(!product1.getId().equals(productId)) {
                return 1;
            }
        }

        for (Merchant merchant1 : merchantService.merchants) {
            if (!merchant1.getId().equals(merchantId)) {
                return 2;
            }
        }
        for (MerchantStock merchantStock1 : merchantStockService.merchantStocks) {
            if(merchantStock1.getStock() != 0) {
                merchantStock1.setStock(merchantStock1.getStock()-1);
            }else return 3;
        }

        for (User user1 : users) {
            if (user1.getId().equals(userId)) {
                for (Product product1 : productService.products) {
                    if(product1.getId().equals(productId)) {
                        if(user1.getBalance() >= product1.getPrice()){
                            user1.setBalance(user1.getBalance()-product1.getPrice());
                            getBuy.add(product1);
                        }else return 4;
                    }
                }
            }
        }


        return 5;
    }

    //============================Endpoint==========================

    //============================Extra point=========================
    //#1:

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

    //#3
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

}
