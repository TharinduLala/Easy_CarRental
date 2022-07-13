package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDto;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        if (!customerRepo.existsById(customerDto.getCustomerNic())) {
            customerRepo.save(modelMapper.map(customerDto, Customer.class));
        } else {
            throw new RuntimeException("Customer Already Exist For Id " + customerDto.getCustomerNic());
        }
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        if (customerRepo.existsById(customerDto.getCustomerNic())) {
            customerRepo.save(modelMapper.map(customerDto, Customer.class));
        } else {
            throw new RuntimeException("No Customer For Id:" + customerDto.getCustomerNic());
        }
    }

    @Override
    public void deleteCustomer(String customerNic) {
        if (customerRepo.existsById(customerNic)) {
            customerRepo.deleteById(customerNic);
        } else {
            throw new RuntimeException("Please Check Id");
        }
    }

    @Override
    public CustomerDto searchCustomer(String customerNic) {
        if (customerRepo.existsById(customerNic)) {
            Customer customer = customerRepo.findById(customerNic).get();
            return modelMapper.map(customer, CustomerDto.class);
        } else {
            throw new RuntimeException("No Customer For :" + customerNic);
        }
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return modelMapper.map(customerRepo.findAll(), new TypeToken<List<CustomerDto>>() {
        }.getType());
    }
}
