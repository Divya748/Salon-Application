package com.eager2code.mapper;

import com.eager2code.model.Booking;
import com.eager2code.pojo.BookingDto;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-06T11:08:17+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public Booking mapDtoToEntity(BookingDto bookingDto) {
        if ( bookingDto == null ) {
            return null;
        }

        Booking booking = new Booking();

        booking.setId( bookingDto.getId() );
        booking.setSalonId( bookingDto.getSalonId() );
        booking.setCustomerId( bookingDto.getCustomerId() );
        booking.setStartTime( bookingDto.getStartTime() );
        booking.setEndTime( bookingDto.getEndTime() );
        Set<Long> set = bookingDto.getServiceIds();
        if ( set != null ) {
            booking.setServiceIds( new LinkedHashSet<Long>( set ) );
        }
        booking.setTotalPrice( bookingDto.getTotalPrice() );

        return booking;
    }

    @Override
    public BookingDto mapEntityToDTO(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingDto bookingDto = new BookingDto();

        bookingDto.setId( booking.getId() );
        bookingDto.setSalonId( booking.getSalonId() );
        bookingDto.setCustomerId( booking.getCustomerId() );
        bookingDto.setStartTime( booking.getStartTime() );
        bookingDto.setEndTime( booking.getEndTime() );
        Set<Long> set = booking.getServiceIds();
        if ( set != null ) {
            bookingDto.setServiceIds( new LinkedHashSet<Long>( set ) );
        }
        bookingDto.setTotalPrice( booking.getTotalPrice() );

        return bookingDto;
    }

    @Override
    public List<BookingDto> mapListOfEntityToDtos(List<Booking> bookings) {
        if ( bookings == null ) {
            return null;
        }

        List<BookingDto> list = new ArrayList<BookingDto>( bookings.size() );
        for ( Booking booking : bookings ) {
            list.add( mapEntityToDTO( booking ) );
        }

        return list;
    }
}
