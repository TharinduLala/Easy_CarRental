package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AdminDto {
    private String adminNic;
    private String adminName;
    private String adminEmail;
    private String adminTel;
    private String adminPassword;
}
