package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverDto;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.service.DriverService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveDriver(DriverDto driverDto) {
        if (!driverRepo.existsById(driverDto.getDriverNic())) {
            driverRepo.save(modelMapper.map(driverDto, Driver.class));
        } else {
            throw new RuntimeException("Driver Already Exist For Id " + driverDto.getDriverNic());
        }
    }

    @Override
    public void updateDriver(DriverDto driverDto) {
        if (driverRepo.existsById(driverDto.getDriverNic())) {
            driverRepo.save(modelMapper.map(driverDto, Driver.class));
        } else {
            throw new RuntimeException("No Customer For Id " + driverDto.getDriverNic());
        }
    }

    @Override
    public void deleteDriver(String driverNic) {
        if (driverRepo.existsById(driverNic)) {
            driverRepo.deleteById(driverNic);
        } else {
            throw new RuntimeException("Please Check Id");
        }
    }

    @Override
    public DriverDto searchDriver(String driverNic) {
        if (driverRepo.existsById(driverNic)) {
            Driver driver = driverRepo.findById(driverNic).get();
            return modelMapper.map(driver, DriverDto.class);
        } else {
            throw new RuntimeException("No Driver For :" + driverNic);
        }
    }

    @Override
    public List<DriverDto> getAllDrivers() {
        return modelMapper.map(driverRepo.findAll(), new TypeToken<List<DriverDto>>() {
        }.getType());
    }
}
