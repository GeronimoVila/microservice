package com.ucc.product.service;

import com.ucc.product.exceptions.Product.ProductNotExistException;
import com.ucc.product.model.entities.Product;
import com.ucc.product.model.dto.ProductDTO;
import com.ucc.product.model.dto.ProductInfoDTO;
import com.ucc.product.model.mappers.ProductsMappers;
import com.ucc.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductsMappers productsMappers;

    // ********************* PRODUCTS **********************************
    //OBTENER TODOS LOS PRODUCTOS
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //OBTENER PRODUCTOS POR ID
    public Product getProductById(Long id){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new ProductNotExistException("No se encontro el producto" + id);
        }else {
            return productOptional.get();
        }
    }

    //CREAR PRODUCTO
    public void createProduct(Product product){
        productRepository.save(product);
    }

    //BORRAR PRODUCTO
    public void deleteProductByID(Long id){
        productRepository.deleteById(id);
    }

    //MODIFICAR PRODUCTO POR ID
    public void editProductByID(Product product, Long id){
        Product existProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        existProduct.setName(product.getName());
        existProduct.setDescription(product.getDescription());
        existProduct.setStock(product.getStock());
        existProduct.setStatus(product.getStatus());
        existProduct.setPrice(product.getPrice());

        productRepository.save(existProduct);
    }

    // ********************* DTO **********************************
    //OBTENER EL DTO DE PRODUCTO (SOLO ID, NOMBRE Y CATEGORIA)
    public List<ProductInfoDTO> getAllInfoProducts(){
        return productRepository.findAll()
                .stream()
                .map(productEntity -> new ProductInfoDTO(productEntity.getId(), productEntity.getName(), productEntity.getCategory()))
                .collect(Collectors.toList());
    }

    //CARGAR EL DTO SIN MAPPER
//    public ResponseEntity<Object> newProduct(ProductDTO productDTO){
//        Product productEntity = new Product();
//        productEntity.setName(productDTO.getName());
//        productEntity.setPrice(productDTO.getPrice());
//        productEntity.setStatus(Boolean.TRUE);
//        productRepository.save(productEntity);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }


    //CARGAR EL DTO DE PRODUCTO
    public ResponseEntity<Object> newProduct(ProductDTO productDTO){
        Product productEntity = productsMappers.productsDTOtoProductsEntity(productDTO);
        productRepository.save(productEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ********************* QUERY **********************************
    //OBTENER LOS PRODUCTOS ORDENADOS POR PRECIO DESCENDIENTE
    public List<Product> getProductByPriceDesc(){
        return productRepository.findAllOrderByPriceDesc();
    }

}
