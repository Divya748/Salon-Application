package com.eager2code.service.impl;

import com.eager2code.mapper.SalonMapper;
import com.eager2code.model.Salon;
import com.eager2code.pojo.SalonDTO;
import com.eager2code.pojo.UserDTO;
import com.eager2code.repository.SalonRepository;
import com.eager2code.service.SalonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDTO salonDTO, UserDTO userDTO) {

        Salon salon = SalonMapper.MAPPER.mapToSalon(salonDTO);
        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salonDTO, UserDTO userDTO, Long salonId) throws Exception {
        Salon existingSalon = salonRepository.findById(salonId).orElse(
                null
        );
        if(existingSalon != null &&
            salonDTO.getOwnerId().equals(userDTO.getId())
        ){
            existingSalon.setAddress(salonDTO.getAddress());
            existingSalon.setCity(salonDTO.getCity());
            existingSalon.setImages(salonDTO.getImages());
            existingSalon.setEmail(salonDTO.getEmail());
            existingSalon.setName(salonDTO.getName());
            existingSalon.setOwnerId(userDTO.getId());
            existingSalon.setOpenTime(salonDTO.getOpenTime());
            existingSalon.setCloseTime(salonDTO.getCloseTime());
            existingSalon.setPhoneNumber(salonDTO.getPhoneNumber());
        }
        throw new Exception("Salon not exist");
    }

    @Override
    public List<SalonDTO> getAllSalons() {
        List<Salon> salons = salonRepository.findAll();
        return SalonMapper.MAPPER.mapToListOfSalonDto(salons);
    }

    @Override
    public SalonDTO getSalonById(Long salonId) throws Exception {
        Salon salon =salonRepository.findById(salonId).orElse(null);
        if(salon == null){
            throw new Exception("Salon not exist");
        }
        return SalonMapper.MAPPER.mapToSalonDto(salon);
    }

    @Override
    public SalonDTO getSalonByOwnerId(Long ownerId) {

        return SalonMapper.MAPPER.mapToSalonDto(
                salonRepository.findByOwnerId(ownerId)
            );
    }

    @Override
    public List<SalonDTO> searchSalonByCity(String city) {

        return SalonMapper.MAPPER.mapToListOfSalonDto(
                salonRepository.searchSalons(city)
        );
    }
}
