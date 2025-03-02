package com.eager2code.service;


import com.eager2code.pojo.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

    BookingDto createBooking(BookingRequest bookingRequest,
                             UserDTO userDTO,
                             SalonDTO salonDTO,
                             Set<ServiceDto> serviceDtos) throws Exception;

    List<BookingDto> getBookingsByCustomerId(Long customerId);
    List<BookingDto> getBookingsBySalonId(Long salonId);
    BookingDto getBookingById(Long id);
    BookingDto updateBookingStatus(Long bookingId,BookingStatus status);
    List<BookingDto> getBookingsByDate(LocalDate localDate,Long salonId);
    SalonReport getSalonReport(Long salonId);
}
