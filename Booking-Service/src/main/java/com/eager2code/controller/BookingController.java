package com.eager2code.controller;

import com.eager2code.pojo.BookingDto;
import com.eager2code.pojo.BookingRequest;
import com.eager2code.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    public ResponseEntity<BookingDto>  createBooking(
            @RequestParam Long salonId,
            @RequestBody BookingRequest bookingRequest
            ){
        return null;
    }

    @GetMapping("/customer")
    public ResponseEntity<List<BookingDto>> getBookingsByCustomer(
            @RequestParam Long customerId) {
        List<BookingDto> bookings = bookingService.getBookingsByCustomerId(customerId);

        return ResponseEntity.ok(bookings);

    }
}
