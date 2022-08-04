package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class BookingPayment {

    @Id
    private String bookingId;
    private double damageDeduction;
    private double totalDownPaymentRefund;
    private double totalDistance;
    private double extraDistance;
    private double rentalCost;
    private double costForExtraDistance;
    private double driverCost;
    private double totalPayment;
}
