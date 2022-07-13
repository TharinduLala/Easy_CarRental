package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDto;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCustomers() {
        return new ResponseUtil(200, "Ok", customerService.getAllCustomers());
    }

    @GetMapping(path = "/{customerNic}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchCustomer(@PathVariable String customerNic) {
        return new ResponseUtil(200, "Ok", customerService.searchCustomer(customerNic));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveCustomer(@ModelAttribute CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
        return new ResponseUtil(200, "Saved Successfully...", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCustomer(@RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerDto);
        return new ResponseUtil(200, "Updated Successfully...", null);
    }

    @DeleteMapping(params = {"customerNic"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCustomer(@RequestParam String customerNic) {
        customerService.deleteCustomer(customerNic);
        return new ResponseUtil(200, "Deleted Successfully", null);
    }
}
