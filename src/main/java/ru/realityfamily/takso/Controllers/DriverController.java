package ru.realityfamily.takso.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.realityfamily.takso.Models.Clients;
import ru.realityfamily.takso.Models.Drivers;
import ru.realityfamily.takso.Repositories.DriversRepository;

@RestController
public class DriverController {

    @Autowired
    DriversRepository driversRepository;

    @GetMapping("/driver/{driverId}/getinfo")
    public ResponseEntity<Drivers> getDriverInfo(@PathVariable Long driverId) {
        Drivers driver = driversRepository.findById(driverId).get();
        return new ResponseEntity<Drivers>(driver, HttpStatus.OK);
    }

    @PostMapping("/driver/{driverId}/postinfo")
    public ResponseEntity<Drivers> postDriverInfo(@PathVariable Long driverId, @RequestBody Drivers drivers) {
        drivers.setId(driverId);
        driversRepository.save(drivers);
        return new ResponseEntity<Drivers>(drivers, HttpStatus.OK);
    }
}
