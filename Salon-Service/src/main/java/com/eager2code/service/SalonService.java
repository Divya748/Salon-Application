package com.eager2code.service;


import com.eager2code.model.Salon;
import com.eager2code.pojo.SalonDTO;
import com.eager2code.pojo.UserDTO;

import java.util.List;

public interface SalonService {

    Salon createSalon(SalonDTO salonDTO, UserDTO userDTO);
    Salon updateSalon(SalonDTO salonDTO, UserDTO userDTO,Long salonId) throws Exception;
    List<SalonDTO> getAllSalons();
    SalonDTO getSalonById(Long salonId) throws Exception;
    SalonDTO getSalonByOwnerId(Long ownerId);
    List<SalonDTO> searchSalonByCity(String city);
}
