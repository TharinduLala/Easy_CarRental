package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Car {
    @Id
    private String carRegNo;
    private String carBrand;
    private String carType;
    private String noOfPassengers;
    private String fuelType;
    private String transmissionType;
    private String carColour;
    private double lossDamagePayment ;
    private double monthlyRate;
    private double dailyRate;
    private double freeKmForMonth;
    private double freeKmForDay;
    private double priceForExtraKm;
    private String carFrontImg;
    private String carBackImg;
    private String carSideImg;
    private String carInteriorImg;
    private String carAvailability;
}
