package lk.ijse.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.spring.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookingDto {
    private String bookingId;
    private String carType;
    private Customer customer;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickupDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime pickupTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    private String bookingStatus;
    private String paymentSlip;
}
