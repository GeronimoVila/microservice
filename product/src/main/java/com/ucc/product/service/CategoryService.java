package com.ucc.product.service;

import com.ucc.product.model.entities.Category;
import com.ucc.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public ResponseEntity<Object> newCategory(Category category){
        categoryRepository.save(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}