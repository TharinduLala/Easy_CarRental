package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookingPaymentDto {
    private String bookingId;
    private double damageDeduction;
    private double totalDownPaymentRefund;
    private double totalDistance;
    private double extraDistance;
    private double rentalCost;
    private double costForExtraDistance;
    private double totalPayment;
}
