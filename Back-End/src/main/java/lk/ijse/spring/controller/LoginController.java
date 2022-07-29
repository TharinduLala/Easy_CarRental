package lk.ijse.spring.controller;

import lk.ijse.spring.dto.LoginDetailsDto;
import lk.ijse.spring.service.AdminService;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    CustomerService customerService;
    @Autowired
    AdminService adminService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/customer",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil customerLogin(@ModelAttribute LoginDetailsDto loginDetailsDto) {
        String customerPassword = customerService.getCustomerPassword(loginDetailsDto.getUserId());
        if (customerPassword.equals(loginDetailsDto.getPassword())) {
            return new ResponseUtil(200, "Login Successfully...", null);
        } else {
            throw new RuntimeException("Wrong Password");
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/admin",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil adminLogin(@ModelAttribute LoginDetailsDto loginDetailsDto) {
        String adminPassword = adminService.getAdminPassword(loginDetailsDto.getUserId());
        if (adminPassword.equals(loginDetailsDto.getPassword())) {
            return new ResponseUtil(200, "Login Successfully...", null);
        } else {
            throw new RuntimeException("Wrong Password");
        }

    }
}
