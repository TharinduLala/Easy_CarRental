package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDto;

import java.util.List;

public interface CarService {
    void saveCar(CarDto carDto);

    void updateCar(CarDto carDto);

    void deleteCar(String carRegNo);

    CarDto searchCar(String carRegNo);

    List<CarDto> getAllCars();
}
