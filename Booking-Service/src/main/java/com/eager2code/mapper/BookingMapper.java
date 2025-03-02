package com.eager2code.mapper;

import com.eager2code.model.Booking;
import com.eager2code.pojo.BookingDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    BookingMapper MAPPER = Mappers.getMapper(BookingMapper.class);

    Booking mapDtoToEntity(BookingDto bookingDto);

    BookingDto mapEntityToDTO(Booking booking);

    List<BookingDto> mapListOfEntityToDtos(List<Booking> bookings);


}
