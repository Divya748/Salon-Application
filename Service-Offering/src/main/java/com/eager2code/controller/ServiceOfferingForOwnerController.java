package com.eager2code.controller;

import com.eager2code.pojo.CategoryDto;
import com.eager2code.pojo.SalonDTO;
import com.eager2code.pojo.ServiceDto;
import com.eager2code.service.impl.OfferingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering-owner")
@AllArgsConstructor
public class ServiceOfferingForOwnerController {

    private final OfferingServiceImpl offeringService;

    @PostMapping("/createService")
    public ResponseEntity<ServiceDto> saveService(
            @RequestBody ServiceDto serviceDto
    ){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        ServiceDto dto = offeringService
                .createNewService(categoryDto,salonDTO,serviceDto);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/updateService/{serviceId}")
    public ResponseEntity<ServiceDto> updateService(
            @PathVariable Long serviceId,
            @RequestBody ServiceDto service
    ) throws Exception {
        ServiceDto updatedService = offeringService
                .updateService(serviceId, service);
        if (updatedService != null) {
            return new ResponseEntity<>(updatedService, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
