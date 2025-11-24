package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddressComparatorTests {

    @Test
    public void testCompareByLastFirstDateAndId() {
        Address a1 = new Address(1, "John", "Doe", "123", new Date(1000));
        Address a2 = new Address(2, "Alice", "Alpha", "111", new Date(500));
        Address a3 = new Address(3, "Bob", "Doe", "222", new Date(800));
        Address a4 = new Address(4, "Bob", "Doe", "333", new Date(800));

        List<Address> list = new ArrayList<>(Arrays.asList(a1, a2, a3, a4));
        Collections.sort(list, new AddressComparator());

        // sortiert nach name und dann id erwartet
        assertEquals(a2, list.get(0));
        assertEquals(a3, list.get(1));
        assertEquals(a4, list.get(2));
        assertEquals(a1, list.get(3));
    }

    @Test
    public void testNullSafe() {
        Address a1 = new Address(1, null, null, null, null);
        Address a2 = new Address(2, "Anna", null, null, null);

        AddressComparator cmp = new AddressComparator();
        assertTrue(cmp.compare(a1, a2) > 0); // null firstname/lastname treated as after
        assertTrue(cmp.compare(a2, a1) < 0);
    }

}
