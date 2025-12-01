package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.Comparator;
import java.util.List;

public class AddressComparator implements Comparator<Address> {

    public enum SortKey {
        LASTNAME,
        FIRSTNAME,
        PHONENUMBER,
        REGISTRATION_DATE,
        ID
    }

    private final List<SortKey> sortOrder;

    public AddressComparator() {
        this.sortOrder = List.of(SortKey.LASTNAME, SortKey.FIRSTNAME, SortKey.ID);
    }

    public AddressComparator(List<SortKey> sortOrder) {
        this.sortOrder = (sortOrder == null || sortOrder.isEmpty())
                ? List.of(SortKey.LASTNAME, SortKey.FIRSTNAME, SortKey.ID)
                : List.copyOf(sortOrder);
    }

    @Override
    public int compare(Address a1, Address a2) {
        if (a1 == a2)
            return 0;
        if (a1 == null)
            return 1; // nulls last
        if (a2 == null)
            return -1;

        Comparator<Address> comparator = null;
        for (SortKey key : sortOrder) {
            Comparator<Address> next = switch (key) {
                case LASTNAME -> comparingString(Address::getLastname);
                case FIRSTNAME -> comparingString(Address::getFirstname);
                case PHONENUMBER -> comparingString(Address::getPhonenumber);
                case REGISTRATION_DATE -> Comparator.comparing(Address::getRegistrationDate,
                        Comparator.nullsLast(Comparator.naturalOrder()));
                case ID -> Comparator.comparingInt(Address::getId);
            };
            comparator = comparator == null ? next : comparator.thenComparing(next);
        }

        // Fallback in case sortOrder was somehow empty
        if (comparator == null) {
            comparator = comparingString(Address::getLastname)
                    .thenComparing(comparingString(Address::getFirstname))
                    .thenComparingInt(Address::getId);
        }

        return comparator.compare(a1, a2);
    }

    private static Comparator<Address> comparingString(java.util.function.Function<Address, String> getter) {
        return Comparator.comparing(
                a -> normalize(getter.apply(a)),
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
    }

    private static String normalize(String s) {
        if (s == null)
            return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }

}
