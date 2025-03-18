package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {



    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    //=================CRUD=================
    public boolean add(MerchantStock merchant){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(merchant.getId())){
                return false;
            }
        }
        merchantStocks.add(merchant);
        return true;
    }

    public boolean update(MerchantStock merchant, String id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(id)){
                merchantStocks.set(i,merchant);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id){
        for (MerchantStock merchant1 : merchantStocks){
            if (merchant1.getId().equals(id)){
                merchantStocks.remove(merchant1);
                return true;
            }
        }
        return false;
    }


    //======================Endpoint========================



    public boolean addStockToMerchant(String productId, String merchantId, int amount) {
        for (MerchantStock stock1 : merchantStocks) {
            if (stock1.getProductId().equals(productId) && stock1.getMerchantId().equals(merchantId)) {
                int newStock = stock1.getStock() + amount;
                stock1.setStock(newStock);
                return true;
            }
        }
        return false;
    }

    public MerchantStock checkStock(String productId, String merchantId) {
        for (MerchantStock stock1 : merchantStocks) {
            if (stock1.getProductId().equals(productId) && stock1.getMerchantId().equals(merchantId)) {
                return stock1;
            }
        }
        return null;
    }




    public boolean reduceStock() {
        for (MerchantStock stock1 : merchantStocks) {
            stock1.setStock(stock1.getStock()-1); ;
            return true;
        }
        return false;
    }
}
