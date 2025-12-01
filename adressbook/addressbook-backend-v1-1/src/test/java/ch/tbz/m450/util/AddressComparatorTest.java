package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressComparatorTest {

    private List<Address> addresses;

    @BeforeEach
    void setUp() {
        addresses = new ArrayList<>();
        Date now = new Date();
        addresses.add(new Address(3, "Max", "Muster", "333", new Date(now.getTime() - 3000)));
        addresses.add(new Address(2, "Erika", "Muster", "222", new Date(now.getTime() - 2000)));
        addresses.add(new Address(4, "Adam", "Aarau", "444", new Date(now.getTime() - 4000)));
        addresses.add(new Address(1, "Zoe", "Zed", "111", new Date(now.getTime() - 1000)));
        addresses.add(new Address(5, null, null, null, null));
    }

    @Test
    void defaultSortByLastnameFirstnameId() {
        List<Address> sorted = addresses.stream().sorted(new AddressComparator()).toList();
        // Order: Aarau(4), Muster(2:Erika then 3:Max), Zed(1), nulls(5)
        assertEquals(4, sorted.get(0).getId());
        assertEquals(2, sorted.get(1).getId());
        assertEquals(3, sorted.get(2).getId());
        assertEquals(1, sorted.get(3).getId());
        assertEquals(5, sorted.get(4).getId());
    }

    @Test
    void sortByRegistrationDateThenLastname() {
        AddressComparator cmp = new AddressComparator(List.of(
                AddressComparator.SortKey.REGISTRATION_DATE,
                AddressComparator.SortKey.LASTNAME));
        List<Address> sorted = addresses.stream().sorted(cmp).toList();
        // Earliest registration date first: id=4,3,2,1 then nulls last(id=5)
        assertEquals(4, sorted.get(0).getId());
        assertEquals(3, sorted.get(1).getId());
        assertEquals(2, sorted.get(2).getId());
        assertEquals(1, sorted.get(3).getId());
        assertEquals(5, sorted.get(4).getId());
    }

    @Test
    void sortNullsLastAndCaseInsensitive() {
        List<Address> list = List.of(
                new Address(10, "anna", "alpha", "", null),
                new Address(11, "Anna", "Alpha", "", null),
                new Address(12, null, "Alpha", "", null));
        List<Address> sorted = list.stream().sorted(new AddressComparator()).toList();
        assertEquals(10, sorted.get(0).getId());
        assertEquals(11, sorted.get(1).getId());
        assertEquals(12, sorted.get(2).getId());
    }
}
