package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Booking {
    @Id
    private String bookingId;
    private LocalDate pickupDate;
    private LocalTime pickupTime;
    private LocalDate returnDate;
    private String bookingStatus;
    private double downPayment;
    private String paymentSlip;
    @ManyToOne
    @JoinColumn(name = "customerNic", referencedColumnName = "customerNic", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "carRegNo", referencedColumnName = "carRegNo", nullable = false)
    private Car car;
    @ManyToOne
    @JoinColumn(name = "driverNic", referencedColumnName = "driverNic")
    private Driver driver;
}
