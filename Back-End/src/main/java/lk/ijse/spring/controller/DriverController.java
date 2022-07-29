package lk.ijse.spring.controller;

import lk.ijse.spring.dto.DriverDto;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@CrossOrigin
public class DriverController {

    @Autowired
    DriverService driverService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllDrivers() {
        return new ResponseUtil(200, "Ok", driverService.getAllDrivers());
    }

    @GetMapping(path = "/{driverNic}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchDriver(@PathVariable String driverNic) {
        return new ResponseUtil(200, "Ok", driverService.searchDriver(driverNic));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveDriver(@ModelAttribute DriverDto driverDto) {
        driverService.saveDriver(driverDto);
        return new ResponseUtil(200, "Saved Successfully...", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateDriver(@RequestBody DriverDto driverDto) {
        driverService.updateDriver(driverDto);
        return new ResponseUtil(200, "Updated Successfully...", null);
    }

    @DeleteMapping(params = {"driverNic"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteDriver(@RequestParam String driverNic) {
        driverService.deleteDriver(driverNic);
        return new ResponseUtil(200, "Deleted Successfully", null);
    }
}
