package com.eager2code.service;

import com.eager2code.pojo.CategoryDto;
import com.eager2code.pojo.SalonDTO;
import com.eager2code.pojo.ServiceDto;

import java.util.Set;

public interface OfferingService {
     ServiceDto createNewService(CategoryDto categoryDto, SalonDTO salonDTO,
                                 ServiceDto serviceDto);
    ServiceDto updateService(Long serviceId,
                                ServiceDto serviceDto) throws Exception;

    Set<ServiceDto> getAllServicesBySalonId(Long salonId,Long categoryId);

    Set<ServiceDto> getServicesByIds(Set<Long> ids);

}
