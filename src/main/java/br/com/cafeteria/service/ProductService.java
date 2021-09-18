package br.com.cafeteria.service;

import br.com.cafeteria.dto.ProductDto;
import br.com.cafeteria.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductEntity> findById(Long id);

    ProductDto createNewProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    void deleteProduct(Long id);
}
