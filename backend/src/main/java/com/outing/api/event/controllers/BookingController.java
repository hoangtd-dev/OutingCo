package com.outing.api.event.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.outing.api.event.dto.requests.BookingRequest;
import com.outing.api.event.dto.responses.BookingResponse;
import com.outing.api.event.entities.Booking;
import com.outing.api.event.mapper.BookingMapper;
import com.outing.api.event.repositories.BookingRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

	private final BookingRepository bookingRepository;
	private final BookingMapper mapper;

	public BookingController(BookingRepository bookingRepository, BookingMapper mapper) {
		this.bookingRepository = bookingRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<BookingResponse>> getBookings(Pageable pageable) {
		return ResponseEntity.ok(bookingRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookingResponse> getBooking(@PathVariable int id) {
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(booking));
	}

	@PostMapping
	public ResponseEntity<Void> createBooking(@Valid @RequestBody BookingRequest request) {
		bookingRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBooking(@PathVariable int id) {
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found: " + id));
		booking.setIsDeleted(true);
		bookingRepository.save(booking);
		return ResponseEntity.noContent().build();
	}
}
