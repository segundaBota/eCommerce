package org.example.jle.ecommerce.controller.converter;

import org.example.jle.ecommerce.domain.dto.price.PriceResponseDTO;
import org.example.jle.ecommerce.model.PriceResponse;
import org.hibernate.service.spi.ServiceException;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unexpectedValueMappingException = ServiceException.class)
public interface RestPriceConverter {

    PriceResponse asPriceResponse (PriceResponseDTO priceResponseDTO);
}
