package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDto customerDto);

    void updateCustomer(CustomerDto customerDto);

    void deleteCustomer(String customerNic);

    CustomerDto searchCustomer(String customerNic);

    List<CustomerDto> getAllCustomers();

    String getCustomerPassword(String customerNic);
}
