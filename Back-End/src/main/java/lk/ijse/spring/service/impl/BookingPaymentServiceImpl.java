package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.BookingPaymentDto;
import lk.ijse.spring.entity.BookingPayment;
import lk.ijse.spring.repo.BookingPaymentRepo;
import lk.ijse.spring.service.BookingPaymentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookingPaymentServiceImpl implements BookingPaymentService {

    @Autowired
    private BookingPaymentRepo bookingPaymentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveBookingPayment(BookingPaymentDto bookingPaymentDto) {
        if (!bookingPaymentRepo.existsById(bookingPaymentDto.getBookingId())) {
            bookingPaymentRepo.save(modelMapper.map(bookingPaymentDto, BookingPayment.class));
        } else {
            throw new RuntimeException("Booking Payment Already Exist For Id " + bookingPaymentDto.getBookingId());
        }
    }

    @Override
    public void updateBookingPayment(BookingPaymentDto bookingPaymentDto) {
        if (bookingPaymentRepo.existsById(bookingPaymentDto.getBookingId())) {
            bookingPaymentRepo.save(modelMapper.map(bookingPaymentDto, BookingPayment.class));
        } else {
            throw new RuntimeException("No Booking For Id: " + bookingPaymentDto.getBookingId());
        }
    }

    @Override
    public void deleteBookingPayment(String bookingId) {
        if (bookingPaymentRepo.existsById(bookingId)) {
            bookingPaymentRepo.deleteById(bookingId);
        } else {
            throw new RuntimeException("Please Check Id");
        }
    }

    @Override
    public BookingPaymentDto searchBookingPayment(String bookingId) {
        if (bookingPaymentRepo.existsById(bookingId)) {
            BookingPayment bookingPayment = bookingPaymentRepo.findById(bookingId).get();
            return modelMapper.map(bookingPayment, BookingPaymentDto.class);
        } else {
            throw new RuntimeException("No Booking For :" + bookingId);
        }
    }

    @Override
    public List<BookingPaymentDto> getAllBookingPayments() {
        return modelMapper.map(bookingPaymentRepo.findAll(), new TypeToken<List<BookingPaymentDto>>() {
        }.getType());
    }
}
