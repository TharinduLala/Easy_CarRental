package lk.ijse.spring.controller;

import lk.ijse.spring.dto.BookingDto;
import lk.ijse.spring.service.BookingService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllBookings() {
        return new ResponseUtil(200, "Ok", bookingService.getAllBookings());
    }

    @GetMapping(path = "/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchBooking(@PathVariable String bookingId) {
        return new ResponseUtil(200, "Ok", bookingService.searchBooking(bookingId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveBooking(@RequestBody BookingDto bookingDto) {
        bookingService.saveBooking(bookingDto);
        return new ResponseUtil(200, "Saved Successfully...", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateBooking(@RequestBody BookingDto bookingDto) {
        bookingService.updateBooking(bookingDto);
        return new ResponseUtil(200, "Updated Successfully...", null);
    }

    @DeleteMapping(params = {"bookingId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteBooking(@RequestParam String bookingId) {
        bookingService.deleteBooking(bookingId);
        return new ResponseUtil(200, "Deleted Successfully", null);
    }
}
