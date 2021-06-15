package ru.realityfamily.takso.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.realityfamily.takso.Models.AuthData;
import ru.realityfamily.takso.Models.Clients;
import ru.realityfamily.takso.Models.Drivers;
import ru.realityfamily.takso.Models.EmptyJsonResponse;
import ru.realityfamily.takso.Repositories.AuthRepository;
import ru.realityfamily.takso.Repositories.ClientsRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    ClientsRepository clientsRepository;
    @Autowired
    AuthRepository authRepository;


    @GetMapping("/client/get_info")
    public ResponseEntity<Clients> getClientInfo(@RequestHeader("Authentication") String token) {
        for (Clients client : clientsRepository.findAll()) {
            if (client.getAuthData().getToken().equals(token)) {
                return new ResponseEntity<>(client, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/client/post_info")
    public ResponseEntity<Clients> postClientInfo(@RequestHeader("Authentication") String token, @RequestBody Clients clients) {
        for (Clients localClient : clientsRepository.findAll()) {
            if (localClient.getAuthData().getToken().equals(token)) {
                localClient.setName(clients.getName());
                localClient.setLastName(clients.getLastName());
                localClient.setPhone(clients.getPhone());
                localClient.setEmail(clients.getEmail());

                clientsRepository.save(localClient);
                return new ResponseEntity<>(localClient, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/client/{clientId}/homeAddress")
    public ResponseEntity<String> getHomeAddress(@PathVariable Long clientId){
        return new ResponseEntity<>(clientsRepository.findById(clientId).get().getHomeAddress(), HttpStatus.OK);
    }

    @PostMapping("/client/{clientId}/homeAddress")
    public ResponseEntity<EmptyJsonResponse> postHomeAddress(@PathVariable Long clientId, @RequestBody String homeAddress){
        Clients client = clientsRepository.findById(clientId).get();
        client.setHomeAddress(homeAddress);
        clientsRepository.save(client);
        return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}/workAddress")
    public ResponseEntity<String> getWorkAddress(@PathVariable Long clientId){
        return new ResponseEntity<>(clientsRepository.findById(clientId).get().getWorkAddress(), HttpStatus.OK);
    }

    @PostMapping("/client/{clientId}/workAddress")
    public ResponseEntity<EmptyJsonResponse> postWorkAddress(@PathVariable Long clientId, @RequestBody String workAddress){
        Clients client = clientsRepository.findById(clientId).get();
        client.setWorkAddress(workAddress);
        clientsRepository.save(client);
        return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}/favoutiteAddresses")
    public ResponseEntity<List<String>> getFavouriteAddresses(@PathVariable Long clientId){
        return new ResponseEntity<>(clientsRepository.findById(clientId).get().getFavoriteAddresses(), HttpStatus.OK);
    }

    @PostMapping("/client/{clientId}/favouriteAddresses")
    public ResponseEntity<EmptyJsonResponse> postFavouriteAddresses(@PathVariable Long clientId, @RequestBody List<String> favouriteAddresses){
        Clients client = clientsRepository.findById(clientId).get();
        client.setFavoriteAddresses(favouriteAddresses);
        clientsRepository.save(client);
        return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
