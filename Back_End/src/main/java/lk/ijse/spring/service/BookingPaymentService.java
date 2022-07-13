package lk.ijse.spring.service;

import lk.ijse.spring.dto.BookingPaymentDto;

import java.util.List;

public interface BookingPaymentService {
    void saveBookingPayment(BookingPaymentDto bookingPaymentDto);

    void updateBookingPayment(BookingPaymentDto bookingPaymentDto);

    void deleteBookingPayment(String bookingId);

    BookingPaymentDto searchBookingPayment(String bookingId);

    List<BookingPaymentDto> getAllBookingPayments();
}
