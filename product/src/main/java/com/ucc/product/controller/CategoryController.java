package com.ucc.product.controller;

import com.ucc.product.model.entities.Category;
import com.ucc.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping(path = "api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    //METODO PARA OBTENER TODAS LAS CATEGORIAS
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    //METODO PARA CARGAR CATEGORIAS NUEVAS
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> newCategories (@RequestBody Category category){
        return categoryService.newCategory(category);
    }
}
