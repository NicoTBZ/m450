package ch.tbz.m450.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address address;
    private Date now;

    @BeforeEach
    void setUp() {
        now = new Date();
        address = new Address(1, "Max", "Muster", "+41 79 000 00 00", now);
    }

    @Test
    void constructorAndGettersWork() {
        assertEquals(1, address.getId());
        assertEquals("Max", address.getFirstname());
        assertEquals("Muster", address.getLastname());
        assertEquals("+41 79 000 00 00", address.getPhonenumber());
        assertEquals(now, address.getRegistrationDate());
    }

    @Test
    void settersWork() {
        Date later = new Date(now.getTime() + 1000);
        address.setId(2);
        address.setFirstname("Erika");
        address.setLastname("Mustermann");
        address.setPhonenumber("000");
        address.setRegistrationDate(later);

        assertEquals(2, address.getId());
        assertEquals("Erika", address.getFirstname());
        assertEquals("Mustermann", address.getLastname());
        assertEquals("000", address.getPhonenumber());
        assertEquals(later, address.getRegistrationDate());
    }
}
