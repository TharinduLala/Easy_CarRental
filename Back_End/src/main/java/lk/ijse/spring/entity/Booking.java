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
    private String carType;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "customerNic", referencedColumnName = "customerNic", nullable = false)
    private Customer customer;
    private LocalDate pickupDate;
    private LocalTime pickupTime;
    private LocalDate returnDate;
    private String bookingStatus;
    private String paymentSlip;
}
