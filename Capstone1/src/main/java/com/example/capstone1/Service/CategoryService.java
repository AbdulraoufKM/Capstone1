package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public boolean add(Category category){
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(category.getId())){
                return false;
            }
        }
        categories.add(category);
        return true;
    }

    public boolean update(Category category, String id){
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(id)){
                categories.set(i,category);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id){
        for (Category category1 : categories){
            if (category1.getId().equals(id)){
                categories.remove(category1);
                return true;
            }
        }
        return false;
    }


}
