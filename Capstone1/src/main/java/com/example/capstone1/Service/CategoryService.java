package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    //#5

    public String getCategoryWithMostProducts(ArrayList<Category> categories, ArrayList<Product> products) {
        String mostPopularCategory = "";
        int maxCount = 0;

        for (Category category1 : categories) {
            int count = 0;
            for (Product product1 : products) {
                if (product1.getCategoryID().equals(category1.getId())) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostPopularCategory = category1.getName();
            }
        }

        return mostPopularCategory;
    }

}
