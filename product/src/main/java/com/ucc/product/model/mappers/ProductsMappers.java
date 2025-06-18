package com.ucc.product.model.mappers;

import com.ucc.product.model.entities.Category;
import com.ucc.product.model.entities.Product;
import com.ucc.product.model.dto.ProductDTO;
import com.ucc.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsMappers {

    private final CategoryRepository categoryRepository;
    public Product productsDTOtoProductsEntity(ProductDTO productDTO){
        Product productEntity = new Product();
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setStatus(Boolean.TRUE);

        Category categoryEntity = categoryRepository.findOneById(productDTO.getCategoryDTO().getId());
        productEntity.setCategory(categoryEntity);
        return productEntity;
    }


    public ProductDTO productEntityToProductDTO(Product productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(productEntity.getName());
        productDTO.setPrice(productEntity.getPrice());
        return productDTO;
    }
}