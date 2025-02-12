package com.eager2code.controller;

import com.eager2code.model.ServiceOffering;
import com.eager2code.pojo.ServiceDto;
import com.eager2code.service.impl.OfferingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/service-offering")
@AllArgsConstructor
public class ServiceOfferingController {

    private final OfferingServiceImpl offeringService;

    @GetMapping("/getServices/{salonId}")
    public ResponseEntity<Set<ServiceDto>> getServicesBySalonId(
            @PathVariable Long salonId,
            @RequestParam(required = false) Long categoryId
    ){
        return ResponseEntity.ok(
                offeringService.getAllServicesBySalonId(salonId,categoryId)
        );
    }


    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceDto>> getServicesByIds(
            @PathVariable Set<Long> ids) {

        return ResponseEntity.ok(offeringService
                .getServicesByIds(ids));
    }
}
