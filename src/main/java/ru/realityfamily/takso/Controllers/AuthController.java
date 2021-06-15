package ru.realityfamily.takso.Controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.realityfamily.takso.Models.AuthData;
import ru.realityfamily.takso.Models.Clients;
import ru.realityfamily.takso.Models.Drivers;
import ru.realityfamily.takso.Repositories.AuthRepository;
import ru.realityfamily.takso.Repositories.ClientsRepository;
import ru.realityfamily.takso.Repositories.DriversRepository;

import java.util.List;
import java.util.UUID;

@RestController
public class AuthController {

    @Autowired
    AuthRepository authRepository;
    @Autowired
    ClientsRepository clientsRepository;
    @Autowired
    DriversRepository driversRepository;

    @PostMapping("/auth/login")
    public ResponseEntity<AuthData> login(@RequestBody AuthData authData) {
        String token = UUID.randomUUID().toString();

        for (AuthData data : authRepository.findAll()) {
            if (data.getLogin().equals(authData.getLogin()) &&
                    data.getPassword().equals(authData.getPassword()) &&
                    data.getType() == authData.getType()) {
                data.setToken(token);
                authRepository.save(data);
                return new ResponseEntity<>(data, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Boolean> register(@RequestBody AuthData authData) {
        for (AuthData localAuthData : authRepository.findAll()) {
            if (localAuthData.getLogin().equals(authData.getLogin())) {
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        }

        authData = authRepository.save(authData);
        if (authData.getType() != null) {
            switch (authData.getType()) {
                case Client:
                    Clients clients = new Clients();
                    clients.setAuthData(authData);
                    clientsRepository.save(clients);
                    break;
                case Driver:
                    Drivers drivers = new Drivers();
                    drivers.setAuthData(authData);
                    driversRepository.save(drivers);
                    break;
                default:
                    return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Boolean>(!authData.getId().toString().isEmpty(),
                !authData.getId().toString().isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
