package org.example.jle.ecommerce.ext.repository;

import org.example.jle.ecommerce.ext.domain.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    @Query(value =
            "SELECT * FROM PRICES " +
                "WHERE brand_id = :brandId " +
                "AND product_id = :productId " +
                "AND :date BETWEEN start_date AND end_date " +
                "ORDER BY priority DESC LIMIT 1",
            nativeQuery = true)
    Optional<PriceEntity> findApplicablePrice(LocalDateTime date, Integer productId, Integer brandId);
}
