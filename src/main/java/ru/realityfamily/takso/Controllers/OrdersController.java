package ru.realityfamily.takso.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.realityfamily.takso.Models.Orders;
import ru.realityfamily.takso.Repositories.ClientsRepository;
import ru.realityfamily.takso.Repositories.DriversRepository;
import ru.realityfamily.takso.Repositories.OrdersRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class OrdersController {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ClientsRepository clientsRepository;
    @Autowired
    DriversRepository driversRepository;

    @PostMapping("/orders/{clientId}/create")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order, @PathVariable Long clientId) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        order.setCreateTime(dtf.format(now));

        order.setClient(clientsRepository.findById(clientId).get());

        order.setPrice(new Random().nextDouble() * 2000 + 500);

        order.setOrderStatus(Orders.Statuses.Created);

        Orders temp = ordersRepository.save(order);
        return new ResponseEntity<Orders>(temp, HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}/info")
    public ResponseEntity<Orders> getOrderInfo(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<Orders>(ordersRepository.findById(orderId).get(), HttpStatus.OK);
    }

    @PostMapping("/orders/close")
    public ResponseEntity<Orders> closeOrder(@RequestBody Orders orders) {
        Orders temp = ordersRepository.findById(orders.getId()).get();
        temp.setOrderStatus(orders.getOrderStatus());
        ordersRepository.save(temp);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @GetMapping("/orders/history/client/{clientId}")
    public ResponseEntity<List<Orders>> clientHistory (@PathVariable("clientId") Long clientId) {
        List<Orders> temp = new ArrayList<>();

        for (Orders order : ordersRepository.findAll()) {
            if (order.getClient() != null) {
                if (order.getClient().getId().equals(clientId)) {
                    temp.add(order);
                }
            }
        }

        return new ResponseEntity<List<Orders>>(temp, HttpStatus.OK);
    }

    @GetMapping("/orders/history/driver/{driverId}")
    public ResponseEntity<List<Orders>> driverHistory (@PathVariable("driverId") Long clientId) {
        List<Orders> temp = new ArrayList<>();

        for (Orders order : ordersRepository.findAll()) {
            if (order.getDriver() != null) {
                if (order.getDriver().getId().equals(clientId)) {
                    temp.add(order);
                }
            }
        }

        return new ResponseEntity<List<Orders>>(temp, HttpStatus.OK);
    }

    @GetMapping("/orders/active")
    public ResponseEntity<List<Orders>> getActiveOrders () {
        List<Orders> temp = new ArrayList<>();

        for (Orders order : ordersRepository.findAll()) {
            if (order.getOrderStatus() == Orders.Statuses.Created) {
                temp.add(order);
            }
        }

        return new ResponseEntity<List<Orders>>(temp, HttpStatus.OK);
    }

    @PostMapping("/orders/{orderId}/take/{driverId}")
    public ResponseEntity<Orders> takeOrder(@PathVariable("driverId") long driverId, @PathVariable("orderId") long orderId) {
         Optional<Orders> order_check =  ordersRepository.findById(orderId);

        if (order_check.isPresent()) {
            Orders order = order_check.get();
            order.setOrderStatus(Orders.Statuses.Waiting);
            order.setDriver(driversRepository.getOne(driverId));
            ordersRepository.save(order);

            return new ResponseEntity<>(order, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

//    @PostMapping("/orders/check")
//    public ResponseEntity<Orders> checkOrder(@RequestBody AuthData authData) {
//        switch (authData.getType()) {
//            case Client:
//                break;
//            case Driver:
//                break;
//        }
//
//        Orders temp = ordersRepository.findById(orders.getId()).get();
//        temp.setOrderStatus(orders.getOrderStatus());
//        ordersRepository.save(temp);
//        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
//    }
}
