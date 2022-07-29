package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarDto;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.service.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo carRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveCar(CarDto carDto) {
        if (!carRepo.existsById(carDto.getCarRegNo())) {
            carRepo.save(modelMapper.map(carDto, Car.class));
        } else {
            throw new RuntimeException("Car Already Exist For RegNo " + carDto.getCarRegNo());
        }
    }

    @Override
    public void updateCar(CarDto carDto) {
        if (carRepo.existsById(carDto.getCarRegNo())) {
            carRepo.save(modelMapper.map(carDto, Car.class));
        } else {
            throw new RuntimeException("No Car For RegNo:" + carDto.getCarRegNo());
        }
    }

    @Override
    public void deleteCar(String carRegNo) {
        if (carRepo.existsById(carRegNo)) {
            carRepo.deleteById(carRegNo);
        } else {
            throw new RuntimeException("Please Check Id");
        }
    }

    @Override
    public CarDto searchCar(String carRegNo) {
        if (carRepo.existsById(carRegNo)) {
            Car car = carRepo.findById(carRegNo).get();
            return modelMapper.map(car, CarDto.class);
        } else {
            throw new RuntimeException("No Car For :" + carRegNo);
        }
    }

    @Override
    public List<CarDto> getAllCars() {
        return modelMapper.map(carRepo.findAll(), new TypeToken<List<CarDto>>() {
        }.getType());
    }
}
