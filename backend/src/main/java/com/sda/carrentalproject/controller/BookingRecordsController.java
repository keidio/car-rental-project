package com.sda.carrentalproject.controller;

import com.sda.carrentalproject.domain.BookingRecord;
import com.sda.carrentalproject.dto.BookingRecordDto;
import com.sda.carrentalproject.dto.CarBookingRequestDto;
import com.sda.carrentalproject.mapper.BookingRecordMapper;
import com.sda.carrentalproject.service.BookingRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class BookingRecordsController {

    private final BookingRecordService bookingRecordService;
    private final BookingRecordMapper bookingRecordMapper;

    public BookingRecordsController(BookingRecordService bookingRecordService, BookingRecordMapper bookingRecordMapper) {
        this.bookingRecordService = bookingRecordService;
        this.bookingRecordMapper = bookingRecordMapper;
    }

    @GetMapping("/booking-records")
    public List<BookingRecordDto> allBookingRecords(){
        log.info("Getting all booking records");

        return bookingRecordService.findAllBookingRecords()
                .stream()
                .map(bookingRecord -> bookingRecordMapper.fromEntityToDto(bookingRecord))
                .toList();
    }

    @PostMapping("/booking-records")
    public ResponseEntity<BookingRecordDto> bookCar(@RequestBody CarBookingRequestDto carBookingRequest, UriComponentsBuilder ucb) {
        log.info("Trying to book car with arguments: [{}]", carBookingRequest);

        BookingRecord savedBookingRecord = bookingRecordService.createNewBooking(carBookingRequest);
        URI path = ucb.path("/api/booking-records/{id}")
                .buildAndExpand(Map.of("id", savedBookingRecord.getId()))
                .toUri();

        return ResponseEntity.created(path)
                .body(bookingRecordMapper.fromEntityToDto(savedBookingRecord));
    }
}
