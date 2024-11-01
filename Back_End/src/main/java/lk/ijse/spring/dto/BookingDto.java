package lk.ijse.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Driver;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickupDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime pickupTime;
    private String packageType;
    private String bookingStatus;
    private String paymentSlip;
    private Customer customer;
    private Car car;
    private String driveInfo;
    private Driver driver;
}
