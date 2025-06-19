package com.ucc.product.controller;

import com.ucc.product.model.entities.Product;
import com.ucc.product.model.dto.ProductDTO;
import com.ucc.product.model.dto.ProductInfoDTO;
import com.ucc.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product){
        this.productService.createProduct(product);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProductByID(id);
    }

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editProductByID(@RequestBody Product product, @PathVariable Long id){
        this.productService.editProductByID(product, id);
    }

    @GetMapping("/infoDTO")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductInfoDTO> getProductsDTO(){
        return productService.getAllInfoProducts();
    }

    @PostMapping("/DTO")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object>newProducts (@RequestBody ProductDTO product){
        return productService.newProduct(product);
    }

    @GetMapping("/products/price")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductPrice(){
        return productService.getProductByPriceDesc();
    }

    @GetMapping("/products/{id}/price")
    @ResponseStatus(HttpStatus.OK)
    public Double getProductPrice(@PathVariable Long id) {
        return productService.getProductById(id).getPrice();
    }

    @GetMapping("/products/{id}/stock")
    @ResponseStatus(HttpStatus.OK)
    public Integer getProductStock(@PathVariable Long id) {
        return productService.getProductById(id).getStock();
    }

    @PutMapping("/products/{id}/stock")
    @ResponseStatus(HttpStatus.OK)
    public void updateStock(@PathVariable Long id, @RequestBody Integer quantity) {
        productService.updateProductStock(id, quantity);
    }

}
