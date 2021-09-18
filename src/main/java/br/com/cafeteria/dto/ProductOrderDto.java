package br.com.cafeteria.dto;

import br.com.cafeteria.entity.ProductOrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDto {

    private Long id;
    private ProductDto productDto;
    private Integer quantity;

    public ProductOrderDto(ProductOrderEntity productOrderEntity) {
        this.id = productOrderEntity.getId();
        this.productDto = new ProductDto(productOrderEntity.getProduct());
        this.quantity = productOrderEntity.getQuantity();
    }

    public ProductOrderDto(Integer quantity) {
        this.quantity = quantity;
    }
}
