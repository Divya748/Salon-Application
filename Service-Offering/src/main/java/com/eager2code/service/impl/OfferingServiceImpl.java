package com.eager2code.service.impl;

import com.eager2code.mapper.OfferingMapper;
import com.eager2code.model.ServiceOffering;
import com.eager2code.pojo.CategoryDto;
import com.eager2code.pojo.SalonDTO;
import com.eager2code.pojo.ServiceDto;
import com.eager2code.repository.OfferingRepository;
import com.eager2code.service.OfferingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfferingServiceImpl implements OfferingService {

    private OfferingRepository offeringRepository;

    private OfferingMapper offeringMapper;

    /**
     * @param categoryDto
     * @param salonDTO
     * @param serviceDto
     * @return
     */
    @Override
    public ServiceDto createNewService(CategoryDto categoryDto,
                                       SalonDTO salonDTO, ServiceDto serviceDto) {

        ServiceOffering service =
                offeringMapper.mapFieldsToServiceFields(
                        salonDTO,serviceDto,categoryDto);
      ServiceOffering  offering =  offeringRepository.save(service);
      return offeringMapper.mapEntityToDto(offering);
    }

    /**
     * @param serviceId
     * @param serviceDto
     * @return
     */
    @Override
    public ServiceDto updateService(Long serviceId, ServiceDto serviceDto) throws Exception {
        ServiceOffering offering =
                offeringRepository.findById(serviceId).orElse(null);
        if(offering == null){
            throw new Exception("service does  not exist with serviceId");
        }
        ServiceOffering updatedData =
                offeringMapper.updateExistingDataToNewData(serviceDto,offering);
        return offeringMapper
                 .mapEntityToDto(offeringRepository.save(updatedData));
    }

    /**
     * @param salonId
     * @param categoryId
     * @return
     */
    @Override
    public Set<ServiceDto> getAllServicesBySalonId(Long salonId, Long categoryId) {
       Set<ServiceOffering> serviceOfferings =
               offeringRepository.findBySalonId(salonId);
       if(categoryId!=null){
           serviceOfferings = serviceOfferings.stream().filter(
                   (service) -> service.getCategoryId()!=null &&
                           service.getCategoryId() == categoryId
           ).collect(Collectors.toSet());
       }
        return offeringMapper
                .mapEntitySetToDtoSet(serviceOfferings);
    }

    /**
     * @param ids
     * @return
     */
    @Override
    public Set<ServiceDto> getServicesByIds(Set<Long> ids) {
        return  offeringMapper
                .mapEntitySetToDtoSet(
                        new HashSet<>(offeringRepository.findAllById(ids))
                );
    }


}
