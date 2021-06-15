package ru.realityfamily.takso.Converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.realityfamily.takso.Models.Cars;
import ru.realityfamily.takso.Models.Clients;
import ru.realityfamily.takso.Models.Drivers;
import ru.realityfamily.takso.Models.Orders;

public class XML_Converter {
    public static String toXML(Object obj) {
        XmlMapper xmlMapper = new XmlMapper();

        int a[] = {1, 2, 3};
        a.clone();

        try {
            return xmlMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static Cars XMLtoCars(String xml) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(xml, Cars.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static Drivers XMLtoDrivers(String xml) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(xml, Drivers.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static Clients XMLtoClients(String xml) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(xml, Clients.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static Orders XMLtoOrders(String xml) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(xml, Orders.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
