package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.Booking;
import ch.schule.SalaryAccount;
import ch.schule.SavingsAccount;
import org.junit.jupiter.api.Test;


import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests für die Klasse Account.
 *
 * @author xxxx
 * @version 1.0
 */
public class AccountTests {
    /**
     * Tested die Initialisierung eines Kontos.
     */
    @Test
    public void testInit() {
        SavingsAccount s = new SavingsAccount("S-1");

        assertEquals("S-1", s.getId());
        assertEquals(0L, s.getBalance());
    }

    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        SavingsAccount s = new SavingsAccount("S-2");

        assertTrue(s.deposit(1, 1000L));
        assertEquals(1000L, s.getBalance());

        // negative amount should fail
        assertFalse(s.deposit(2, -10L));
    }

    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        SavingsAccount s = new SavingsAccount("S-3");

        // cannot withdraw when balance insufficient
        assertFalse(s.withdraw(1, 100L));

        // deposit and then withdraw
        assertTrue(s.deposit(1, 1000L));
        assertTrue(s.withdraw(2, 500L));
        assertEquals(500L, s.getBalance());
    }

    /**
     * Tests the reference from SavingsAccount
     */
    @Test
    public void testReferences() {
        SavingsAccount s = new SavingsAccount("S-4");
        Booking b = new Booking(1, 200L);

        s.setBooking(b);
        assertSame(b, s.getBooking());
    }

    /**
     * teste the canTransact Flag
     */
    @Test
    public void testCanTransact() {
        SavingsAccount s = new SavingsAccount("S-5");

        assertTrue(s.canTransact(10));
        // deposit at date 20
        assertTrue(s.deposit(20, 100L));
        // cannot transact at earlier date
        assertFalse(s.canTransact(15));
        // can transact at same or later date
        assertTrue(s.canTransact(20));
        assertTrue(s.canTransact(21));
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        SavingsAccount s = new SavingsAccount("S-6");
        s.deposit(5, 100L);

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(out);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        try {
            s.print();
        } finally {
            System.out.flush();
            System.setOut(old);
        }

        String printed = out.toString();
        assertTrue(printed.contains("Kontoauszug"));
    }

    /**
     * Experimente mit print(year,month).
     */
    @Test
    public void testMonthlyPrint() {
        SavingsAccount s = new SavingsAccount("S-7");
        // date -> year/month calculation uses banking days; choose date within month
        s.deposit((1970 - 1970) * 360 + (1 - 1) * 30 + 5, 100L);

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(out);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        try {
            s.print(1970, 1);
        } finally {
            System.out.flush();
            System.setOut(old);
        }

        String printed = out.toString();
        assertTrue(printed.contains("Kontoauszug"));
    }

}
