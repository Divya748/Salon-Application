package com.eager2code.mapper;

import com.eager2code.model.ServiceOffering;
import com.eager2code.pojo.CategoryDto;
import com.eager2code.pojo.SalonDTO;
import com.eager2code.pojo.ServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfferingMapper {

    OfferingMapper MAPPER = Mappers.getMapper(OfferingMapper.class);

    ServiceOffering mapDtoToEntity(ServiceDto serviceDto);

    ServiceDto mapEntityToDto(ServiceOffering serviceOffering);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "image", ignore = true)
//    @Mapping(target = "categoryId",source = "categoryDto.id")
//    @Mapping(target = "salonId",source = "salonDTO.id")
//    @Mapping(target = "name", source = "serviceDto.name")
//    ServiceOffering updateService(SalonDTO salonDTO,
//                             ServiceDto serviceDto, CategoryDto categoryDto);

    ServiceOffering updateExistingDataToNewData(ServiceDto source,
                                     @MappingTarget ServiceOffering target);

    Set<ServiceDto> mapEntitySetToDtoSet(Set<ServiceOffering> serviceOfferings);


}
