package ch.tbz.m450.repository;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AddressEntityTests {

    @Test
    public void testConstructorAndGetters() {
        Date d = new Date(12345);
        Address a = new Address(42, "Max", "Mustermann", "+41781234567", d);

        assertEquals(42, a.getId());
        assertEquals("Max", a.getFirstname());
        assertEquals("Mustermann", a.getLastname());
        assertEquals("+41781234567", a.getPhonenumber());
        assertEquals(d, a.getRegistrationDate());
    }

    @Test
    public void testSetters() {
        Address a = new Address();
        a.setId(5);
        a.setFirstname("Anna");
        a.setLastname("Alpha");
        a.setPhonenumber("000");
        a.setRegistrationDate(new Date(1));

        assertEquals(5, a.getId());
        assertEquals("Anna", a.getFirstname());
        assertEquals("Alpha", a.getLastname());
    }

}
