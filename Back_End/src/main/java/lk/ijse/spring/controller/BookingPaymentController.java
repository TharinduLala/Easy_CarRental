package lk.ijse.spring.controller;

import lk.ijse.spring.dto.BookingDto;
import lk.ijse.spring.dto.BookingPaymentDto;
import lk.ijse.spring.service.BookingPaymentService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookingPayment")
@CrossOrigin
public class BookingPaymentController {

    @Autowired
    BookingPaymentService bookingPaymentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllBookingPayments() {
        return new ResponseUtil(200, "Ok", bookingPaymentService.getAllBookingPayments());
    }

    @GetMapping(path = "/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchBookingPayment(@PathVariable String bookingId) {
        return new ResponseUtil(200, "Ok", bookingPaymentService.searchBookingPayment(bookingId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveBookingPayment(@RequestBody BookingPaymentDto bookingPaymentDto) {
        bookingPaymentService.saveBookingPayment(bookingPaymentDto);
        return new ResponseUtil(200, "Saved Successfully...", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateBookingPayment(@RequestBody BookingPaymentDto bookingPaymentDto) {
        bookingPaymentService.updateBookingPayment(bookingPaymentDto);
        return new ResponseUtil(200, "Updated Successfully...", null);
    }

    @DeleteMapping(params = {"bookingId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteBookingPayment(@RequestParam String bookingId) {
        bookingPaymentService.deleteBookingPayment(bookingId);
        return new ResponseUtil(200, "Deleted Successfully", null);
    }
}
