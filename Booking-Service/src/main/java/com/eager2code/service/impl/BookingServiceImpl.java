package com.eager2code.service.impl;

import com.eager2code.mapper.BookingMapper;
import com.eager2code.model.Booking;
import com.eager2code.pojo.*;
import com.eager2code.repository.BookingRepository;
import com.eager2code.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;
    /**
     * @param bookingRequest
     * @param userDTO
     * @param salonDTO
     * @param serviceDtos
     * @return
     */
    @Override
    public BookingDto createBooking(BookingRequest bookingRequest,
                                    UserDTO userDTO,
                                    SalonDTO salonDTO,
                                    Set<ServiceDto> serviceDtos) throws Exception {
        int totalDuration = serviceDtos.stream()
                .mapToInt(ServiceDto::getDurationInMinutes)
                .sum();
        LocalDateTime bookingStartTime = bookingRequest.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);
        Boolean isSlotAvailable = isTimeSlotAvailable(salonDTO,bookingStartTime,bookingEndTime);
        if(!isSlotAvailable){
            throw new Exception("Slot is not available");
        }
        int totalPrice = serviceDtos.stream()
                .mapToInt(ServiceDto::getPrice)
                .sum();
        Set<Long> idList = serviceDtos.stream()
                .map(ServiceDto::getId)
                .collect(Collectors.toSet());
        Booking newBooking = new Booking();
        newBooking.setCustomerId(userDTO.getId());
        newBooking.setSalonId(salonDTO.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);
        Booking savedBooking = bookingRepository.save(newBooking);
        return bookingMapper.mapEntityToDTO(savedBooking);
    }

    public Boolean isTimeSlotAvailable(SalonDTO salonDTO,
                                       LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception {
        List<BookingDto> existingBookings = getBookingsBySalonId(salonDTO.getId());
        LocalDateTime salonOpenTime = salonDTO.getOpenTime()
                .atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDTO.getCloseTime()
                .atDate(bookingStartTime.toLocalDate());
        if(bookingStartTime.isBefore(salonOpenTime) ||
            bookingEndTime.isAfter(salonCloseTime)){
            throw new Exception("Booking time should be within salon working hours");
        }
        for(BookingDto existingBooking : existingBookings){
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();
            if(
                    (bookingStartTime.isBefore(existingBookingEndTime) &&
                            bookingEndTime.isAfter(existingBookingStartTime)
                    ) ||
                            (bookingStartTime.isEqual(existingBookingStartTime) ||
                        bookingEndTime.isEqual(existingBookingEndTime))
            ) {

                throw new Exception("Slot not available, choose different slot");
            }
        }
        return true;
    }

    /**
     * @param customerId
     * @return
     */
    @Override
    public List<BookingDto> getBookingsByCustomerId(Long customerId) {
        return bookingMapper.mapListOfEntityToDtos(
                bookingRepository.findByCustomerId(customerId)
        );
    }

    /**
     * @param salonId
     * @return
     */
    @Override
    public List<BookingDto> getBookingsBySalonId(Long salonId) {
        return bookingMapper.mapListOfEntityToDtos(
                bookingRepository.findByCustomerId(salonId)
        );
    }

    /**
     * @param id
     * @return
     */
    @Override
    public BookingDto getBookingById(Long id) {
        return bookingMapper.mapEntityToDTO(
                bookingRepository.findById(id).orElse(null));
    }

    /**
     * @param bookingId
     * @param status
     * @return
     */
    @Override
    public BookingDto updateBookingStatus(Long bookingId, BookingStatus status) {
        BookingDto  bookingDto = getBookingById(bookingId);
        bookingDto.setBookingStatus(status.name());

        return bookingMapper.mapEntityToDTO(
                bookingRepository.save(
                        bookingMapper.mapDtoToEntity(bookingDto)
                )
        );
    }

    /**
     * @param date
     * @param salonId
     * @return
     */
    @Override
    public List<BookingDto> getBookingsByDate(LocalDate date, Long salonId) {
        //retrive all bookings based on salonid
        List<BookingDto> allBookings = getBookingsBySalonId(salonId);
        if (date == null) {
            return allBookings;
        }
        return allBookings.stream()
                .filter( booking -> isSameDate(booking.getStartTime(),date)
                || isSameDate(booking.getEndTime(),date))
                .collect(Collectors.toList());
    }

    private boolean isSameDate(LocalDateTime time, LocalDate date) {
        return time.toLocalDate().isEqual(date);
    }

    /**
     * @param salonId
     * @return
     */
    @Override
    public SalonReport getSalonReport(Long salonId) {
        List<BookingDto> bookings = getBookingsBySalonId(salonId);

        Double totalEarnings = bookings.stream()
                .mapToDouble(BookingDto::getTotalPrice)
                .sum();

        List<BookingDto> cancelledBookings = bookings.stream()
                .filter(booking ->
                        BookingStatus.CANCELLED.toString().equals(booking.getBookingStatus()))
                .toList();

        Double totalRefund = cancelledBookings.stream()
                .mapToDouble(BookingDto::getTotalPrice)
                .sum();
        SalonReport salonReport = new SalonReport();
        salonReport.setSalonId(salonId);
        salonReport.setTotalBookings(bookings.size());
        salonReport.setCancelledBookings(cancelledBookings.size());
        salonReport.setTotalEarnings(totalEarnings);
        salonReport.setTotalRefund(totalRefund);
        return salonReport;
    }
}
