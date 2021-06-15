package ru.realityfamily.takso.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.realityfamily.takso.Models.Drivers;
import ru.realityfamily.takso.Models.Lines;
import ru.realityfamily.takso.Repositories.CarsRepository;
import ru.realityfamily.takso.Repositories.DriversRepository;
import ru.realityfamily.takso.Repositories.LinesRepository;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class LinesController {

    @Autowired
    LinesRepository linesRepository;
    @Autowired
    DriversRepository driversRepository;
    @Autowired
    CarsRepository carsRepository;

    @PostMapping("/line/{driverId}")
    public ResponseEntity<Boolean> addLine(@PathVariable Long driverId) {
        Drivers me = driversRepository.findById(driverId).get();

        for (Lines line : linesRepository.findAll()) {
            if (line.getDriver().getId().equals(driverId) && line.isOnLine()) {
                line.setOnLine(false);
                line.setEndOnLine(OffsetDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                linesRepository.save(line);
            }
        }
        Lines line = new Lines();
        line.setStartOnLine(OffsetDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        line.setOnLine(true);
        line.setDriver(me);
        linesRepository.save(line);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/line/{driverId}/check")
    public ResponseEntity<Boolean> checkLine(@PathVariable Long driverId) {
        Drivers me = driversRepository.findById(driverId).get();

        for (Lines line : linesRepository.findAll()) {
            if (line.getDriver().getId().equals(driverId) && line.isOnLine()) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @PostMapping("/line/{driverId}/close")
    public void closeLine(@PathVariable Long driverId) {
        Drivers me = driversRepository.findById(driverId).get();

        for (Lines line : linesRepository.findAll()) {
            if (line.getDriver().equals(me) && line.isOnLine()) {
                line.setOnLine(false);
                line.setEndOnLine(OffsetDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                linesRepository.save(line);
            }
        }
    }
}
