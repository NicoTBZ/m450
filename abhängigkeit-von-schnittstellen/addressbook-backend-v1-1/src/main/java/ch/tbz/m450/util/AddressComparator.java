package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        if (a1 == a2) return 0;
        if (a1 == null) return 1; // nulls last
        if (a2 == null) return -1;

        // compare by lastname (case-insensitive)
        int c = compareNullableIgnoreCase(a1.getLastname(), a2.getLastname());
        if (c != 0) return c;

        // then by firstname
        c = compareNullableIgnoreCase(a1.getFirstname(), a2.getFirstname());
        if (c != 0) return c;

        // then by registration date (older first)
        if (a1.getRegistrationDate() == null && a2.getRegistrationDate() != null) return 1;
        if (a1.getRegistrationDate() != null && a2.getRegistrationDate() == null) return -1;
        if (a1.getRegistrationDate() != null && a2.getRegistrationDate() != null) {
            c = a1.getRegistrationDate().compareTo(a2.getRegistrationDate());
            if (c != 0) return c;
        }

        // finally by id
        return Integer.compare(a1.getId(), a2.getId());
    }

    private int compareNullableIgnoreCase(String s1, String s2) {
        if (s1 == s2) return 0;
        if (s1 == null) return 1; // nulls last
        if (s2 == null) return -1;
        return s1.compareToIgnoreCase(s2);
    }

}
