package br.com.cafeteria.service.impl;

import br.com.cafeteria.dto.ProductDto;
import br.com.cafeteria.entity.ProductEntity;
import br.com.cafeteria.repository.ProductRepository;
import br.com.cafeteria.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductDto createNewProduct(ProductDto productDto) {
        ProductEntity productEntity = productRepository.save(new ProductEntity(productDto));
        return new ProductDto(productEntity);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
