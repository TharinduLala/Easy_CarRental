package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.BookingDto;
import lk.ijse.spring.entity.Booking;
import lk.ijse.spring.repo.BookingRepo;
import lk.ijse.spring.service.BookingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveBooking(BookingDto bookingDto) {
        if (!bookingRepo.existsById(bookingDto.getBookingId())) {
            bookingRepo.save(modelMapper.map(bookingDto, Booking.class));
        } else {
            throw new RuntimeException("Booking Already Exist For Id " + bookingDto.getBookingId());
        }
    }

    @Override
    public void updateBooking(BookingDto bookingDto) {
        if (bookingRepo.existsById(bookingDto.getBookingId())) {
            bookingRepo.save(modelMapper.map(bookingDto, Booking.class));
        } else {
            throw new RuntimeException("NO Booking For Id: " + bookingDto.getBookingId());
        }
    }

    @Override
    public void deleteBooking(String bookingId) {
        if (bookingRepo.existsById(bookingId)) {
            bookingRepo.deleteById(bookingId);
        } else {
            throw new RuntimeException("Please Check Id");
        }
    }

    @Override
    public BookingDto searchBooking(String bookingId) {
        if (bookingRepo.existsById(bookingId)) {
            Booking booking = bookingRepo.findById(bookingId).get();
            return modelMapper.map(booking, BookingDto.class);
        } else {
            throw new RuntimeException("No Booking For :" + bookingId);
        }
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return modelMapper.map(bookingRepo.findAll(), new TypeToken<List<BookingDto>>() {
        }.getType());
    }
}
