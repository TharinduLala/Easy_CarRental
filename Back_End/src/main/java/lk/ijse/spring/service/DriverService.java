package lk.ijse.spring.service;

import lk.ijse.spring.dto.DriverDto;

import java.util.List;

public interface DriverService {
    void saveDriver(DriverDto driverDto);

    void updateDriver(DriverDto driverDto);

    void deleteDriver(String driverNic);

    DriverDto searchDriver(String driverNic);

    List<DriverDto> getAllDrivers();
}
