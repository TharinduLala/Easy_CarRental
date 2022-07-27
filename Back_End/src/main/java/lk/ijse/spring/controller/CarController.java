package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarDto;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCars() {
        return new ResponseUtil(200, "Ok", carService.getAllCars());
    }

    @GetMapping(path = "/{carRegNo}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchCar(@PathVariable String carRegNo) {
        return new ResponseUtil(200, "Ok", carService.searchCar(carRegNo));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveCar(@ModelAttribute CarDto carDto) {
        carService.saveCar(carDto);
        return new ResponseUtil(200, "Saved Successfully...", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCar(@RequestBody CarDto carDto) {
        carService.updateCar(carDto);
        return new ResponseUtil(200, "Updated Successfully...", null);
    }

    @DeleteMapping(params = {"carRegNo"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCar(@RequestParam String carRegNo) {
        carService.deleteCar(carRegNo);
        return new ResponseUtil(200, "Deleted Successfully", null);
    }
}
