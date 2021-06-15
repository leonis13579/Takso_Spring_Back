package ru.realityfamily.takso.Converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.realityfamily.takso.Models.Cars;
import ru.realityfamily.takso.Models.Clients;
import ru.realityfamily.takso.Models.Drivers;
import ru.realityfamily.takso.Models.Orders;

public class JSON_Converter {
    public static String toJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static Cars JSONtoCars(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return  objectMapper.readValue(json, Cars.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static Drivers JSONtoDrivers(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return  objectMapper.readValue(json, Drivers.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static Clients JSONtoClients(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return  objectMapper.readValue(json, Clients.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static Orders JSONtoOrders(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return  objectMapper.readValue(json, Orders.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
