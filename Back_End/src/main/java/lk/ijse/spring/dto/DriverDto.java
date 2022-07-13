package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DriverDto {
    private String driverNic;
    private String driverName;
    private String driverAddress;
    private String driverTel;
    private String driverEmail;
    private String driverLicenseNo;
    private String driverPassword;
    private String driverAvailability;
}
