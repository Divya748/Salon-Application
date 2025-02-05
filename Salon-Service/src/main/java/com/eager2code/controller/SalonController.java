package com.eager2code.controller;


import com.eager2code.model.Salon;
import com.eager2code.pojo.SalonDTO;
import com.eager2code.pojo.UserDTO;
import com.eager2code.service.SalonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

    private  final SalonService salonService;

    @PostMapping("/createSalon")
    public ResponseEntity<String> createSalon(@RequestBody @Valid SalonDTO salonDto){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.createSalon(salonDto,userDTO);
        return new ResponseEntity<String>(
                "Salon details saved successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/updateSalon/{salonId}")
    public ResponseEntity<String> updateSalon(
            @RequestBody SalonDTO salonDto,
            @PathVariable Long salonId) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.updateSalon(salonDto,userDTO,salonId);
        return new ResponseEntity<String>(
                "Salon details updated successfully", HttpStatus.CREATED);
    }

    @GetMapping("/allSalons")
    public ResponseEntity<List<SalonDTO>> getAllSalons() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        List<SalonDTO> salon = salonService.getAllSalons();
        return ResponseEntity.ok(salon);

    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getSalonById(
            @PathVariable Long salonId
    ) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        SalonDTO salonDTO = salonService.getSalonById(salonId);
        return ResponseEntity.ok(salonDTO);
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<SalonDTO> getSalonOwnerId(
            @PathVariable Long ownerId
    ) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        SalonDTO salonDTO = salonService.getSalonByOwnerId(ownerId);
        return ResponseEntity.ok(salonDTO);
    }

    @GetMapping("/searchSalon/{city}")
    public ResponseEntity<List<SalonDTO>> searchSalons(
            @PathVariable String city
    ) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        List<SalonDTO> salon = salonService.searchSalonByCity(city);
        return ResponseEntity.ok(salon);

    }
}
