package lk.ijse.spring.service;

import lk.ijse.spring.dto.BookingDto;

import java.util.List;

public interface BookingService {
    void saveBooking(BookingDto bookingDto);

    void updateBooking(BookingDto bookingDto);

    void deleteBooking(String bookingId);

    BookingDto searchBooking(String bookingId);

    List<BookingDto> getAllBookings();

    List<BookingDto> getBookingsByCustomerNic(String customerNic);

}
