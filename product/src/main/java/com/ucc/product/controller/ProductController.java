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

    // ********************* PRODUCTS **********************************
    //OBTENER TODOS LOS PRODUCTOS
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    //OBTENER PRODUCTOS POR ID
    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    //CREAR PRODUCTO
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product){
        this.productService.createProduct(product);
    }

    //BORRAR PRODUCTO
    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProductByID(id);
    }

    //MODIFICAR PRODUCTO POR ID
    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editProductByID(@RequestBody Product product, @PathVariable Long id){
        this.productService.editProductByID(product, id);
    }

    // ********************* DTO **********************************
    //OBTENER EL DTO DE PRODUCTO (SOLO ID, NOMBRE Y CATEGORIA)
    @GetMapping("/infoDTO")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductInfoDTO> getProductsDTO(){
        return productService.getAllInfoProducts();
    }

    //CARGAR EL DTO DE PRODUCTO (SOLO ID, NOMBRE Y CATEGORIA)
    @PostMapping("/DTO")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object>newProducts (@RequestBody ProductDTO product){
        return productService.newProduct(product);
    }

    // ********************* QUERY **********************************
    //OBTENER LOS PRODUCTOS ORDENADOS POR PRECIO DESCENDIENTE
    @GetMapping("/products/price")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductPrice(){
        return productService.getProductByPriceDesc();
    }
}
