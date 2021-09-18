package br.com.cafeteria.repository;

import br.com.cafeteria.entity.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrderEntity, Long> {

    ProductOrderEntity findFirstByOrderIdAndProductId(Long orderId, Long productId);
}
