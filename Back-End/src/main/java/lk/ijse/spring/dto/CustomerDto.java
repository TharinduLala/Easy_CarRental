package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerDto {
    private String customerNic;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerTel;
    private String customerPassword;
    private String nicPhoto;
}
