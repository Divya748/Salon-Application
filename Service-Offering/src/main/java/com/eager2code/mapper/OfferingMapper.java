package com.eager2code.mapper;

import com.eager2code.model.ServiceOffering;
import com.eager2code.pojo.ServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfferingMapper {

    OfferingMapper MAPPER = Mappers.getMapper(OfferingMapper.class);

    ServiceOffering mapDtoToEntity(ServiceDto serviceDto);

    ServiceDto mapEntityToDto(ServiceOffering serviceOffering);

}
