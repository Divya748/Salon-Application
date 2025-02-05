package com.eager2code.mapper;

import com.eager2code.model.Salon;
import com.eager2code.pojo.SalonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SalonMapper {

    SalonMapper MAPPER = Mappers.getMapper(SalonMapper.class);


    SalonDTO mapToSalonDto(Salon salon);

    Salon mapToSalon(SalonDTO userDto);

    List<SalonDTO> mapToListOfSalonDto(List<Salon> salons);

}
