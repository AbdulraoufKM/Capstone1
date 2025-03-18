package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    public boolean add(Merchant merchant){
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(merchant.getId())){
                return false;
            }
        }
        merchants.add(merchant);
        return true;
    }

    public boolean update(Merchant merchant, String id){
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(id)){
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id){
        for (Merchant merchant1 : merchants){
            if (merchant1.getId().equals(id)){
                merchants.remove(merchant1);
                return true;
            }
        }
        return false;
    }
}
