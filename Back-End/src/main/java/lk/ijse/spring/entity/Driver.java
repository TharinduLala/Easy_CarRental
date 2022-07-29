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
public class Driver {
    @Id
    private String driverNic;
    private String driverName;
    private String driverAddress;
    private String driverTel;
    private String driverEmail;
    private String driverLicenseNo;
    private String driverPassword;
    private String driverAvailability;
}
