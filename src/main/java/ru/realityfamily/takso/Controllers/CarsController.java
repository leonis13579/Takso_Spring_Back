package ru.realityfamily.takso.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso.Models.AuthData;
import ru.realityfamily.takso.Models.Cars;
import ru.realityfamily.takso.Models.Drivers;
import ru.realityfamily.takso.Models.EmptyJsonResponse;
import ru.realityfamily.takso.Models.Rightech.RightechObject;
import ru.realityfamily.takso.Repositories.CarsRepository;
import ru.realityfamily.takso.Repositories.DriversRepository;
import ru.realityfamily.takso.Request.RightechConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class CarsController {

    @Autowired
    CarsRepository carsRepository;
    @Autowired
    DriversRepository driversRepository;

    @GetMapping("/cars")
    public List<Cars> getCars() {
        List<Cars> temp_list = new ArrayList<>();
        boolean flag = false;

        rightechLoop:
        for (RightechObject ro : RightechConnection.getInstance().getObjects()) {
            flag = false;
            for (Cars car : carsRepository.findAll()) {
                if (ro.get_id().equals(car.getCodeName())) {
                    flag = true;
                    if (car.getDriver() == null) {
                        temp_list.add(car);
                        continue rightechLoop;
                    }
                }
            }

            if (!flag) {
                Cars car = new Cars();
                car.setName(ro.getName());
                car.setCodeName(ro.get_id());
                Cars temp = carsRepository.save(car);
                temp_list.add(temp);
            }
        }

        return temp_list;
    }

    @PostMapping("/cars/get_driver_car")
    public ResponseEntity<Cars> getCar(@RequestHeader("Authentication") String token) {
        for (Cars car : carsRepository.findAll()) {
            if (car.getDriver() != null) {
                if (car.getDriver().getAuthData().getToken().equals(token)) {
                    return new ResponseEntity<Cars>(car, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

    @PostMapping("/car/{carId}/addDriver")
    public ResponseEntity<Boolean> addDriver(@PathVariable Long carId, @RequestHeader("Authentication") String token) {
        Optional<Cars> temp = carsRepository.findById(carId);
        if (temp.isPresent()) {
            for (Drivers driver : driversRepository.findAll()) {
                if (driver.getAuthData().getToken().equals(token)) {
                    temp.get().setDriver(driver);
                    carsRepository.save(temp.get());
                    return new ResponseEntity<>(true, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/cars/unpinCar")
    public ResponseEntity<Boolean> unpinCar(@RequestHeader("Authentication") String token) {
        for (Cars car : carsRepository.findAll()) {
            if (car.getDriver() != null) {
                if (car.getDriver().getAuthData().getToken().equals(token)) {
                    car.setDriver(null);
                    carsRepository.save(car);
                    return new ResponseEntity<>(true, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
}
