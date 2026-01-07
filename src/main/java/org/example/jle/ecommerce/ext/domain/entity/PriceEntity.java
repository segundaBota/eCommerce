package org.example.jle.ecommerce.ext.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
public class PriceEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Integer brandId;
    private Integer productId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private Double price;
    private String currency;
}
