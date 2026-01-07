package org.example.jle.ecommerce.ext.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "brand_id")
    private Integer brandId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "price_list")
    private Integer priceList;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    private Integer priority;
    private Double price;
    private String currency;
}
