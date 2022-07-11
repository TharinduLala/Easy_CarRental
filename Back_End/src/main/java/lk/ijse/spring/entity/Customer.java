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
public class Customer {
    @Id
    private String customerNic;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerTel;
    private String customerPassword;
    private String nicPhoto;
}
