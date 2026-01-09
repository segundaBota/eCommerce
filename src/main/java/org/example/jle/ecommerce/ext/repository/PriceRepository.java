package org.example.jle.ecommerce.ext.repository;

import org.example.jle.ecommerce.ext.domain.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    @Query(value =
            "SELECT * FROM PRICES " +
                "WHERE brand_id = :brandId " +
                "AND product_id = :productId " +
                "AND :date BETWEEN start_date AND end_date",
            nativeQuery = true)
    List<PriceEntity> findApplicablePrice(LocalDateTime date, Integer productId, Integer brandId);
}
