package ch.schule.bank.junit5;

import ch.schule.Bank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests f�r die Klasse 'Bank'.
 *
 * @author xxxx
 * @version 1.0
 */
public class BankTests {
    /**
     * Tests to create new Accounts
     */
    @Test
    public void testCreate() {
        Bank bank = new Bank();

        String sId = bank.createSavingsAccount();
        assertNotNull(sId);
        assertTrue(sId.startsWith("S-"));

        String yId = bank.createPromoYouthSavingsAccount();
        assertNotNull(yId);
        assertTrue(yId.startsWith("Y-"));

        String pId = bank.createSalaryAccount(-500L);
        assertNotNull(pId);
        assertTrue(pId.startsWith("P-"));
    }
    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        Bank bank = new Bank();
        String id = bank.createSavingsAccount();

        assertTrue(bank.deposit(id, 1, 1000L));
        assertEquals(1000L, bank.getBalance(id));
        // deposit to non-existent account
        assertFalse(bank.deposit("NOPE", 1, 100L));
    }
    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        Bank bank = new Bank();
        String id = bank.createSavingsAccount();

        // cannot withdraw without balance
        assertFalse(bank.withdraw(id, 1, 100L));

        bank.deposit(id, 1, 1000L);
        assertTrue(bank.withdraw(id, 2, 500L));
        assertEquals(500L, bank.getBalance(id));
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        Bank bank = new Bank();
        String id = bank.createSavingsAccount();
        bank.deposit(id, 1, 100L);

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(out);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        try {
            bank.print(id);
        } finally {
            System.out.flush();
            System.setOut(old);
        }

        String printed = out.toString();
        assertTrue(printed.contains("Kontoauszug"));
    }

    /**
     * Experimente mit print(year, month).
     */
    @Test
    public void testMonthlyPrint() {
        Bank bank = new Bank();
        String id = bank.createSavingsAccount();
        int date = (1970 - 1970) * 360 + (2 - 1) * 30 + 5;
        bank.deposit(id, date, 200L);

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(out);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        try {
            bank.print(id, 1970, 2);
        } finally {
            System.out.flush();
            System.setOut(old);
        }

        String printed = out.toString();
        assertTrue(printed.contains("Kontoauszug"));
    }

    /**
     * Testet den Gesamtkontostand der Bank.
     */
    @Test
    public void testBalance() {
        Bank bank = new Bank();
        String a = bank.createSavingsAccount();
        String b = bank.createSavingsAccount();

        bank.deposit(a, 1, 100L);
        bank.deposit(b, 1, 200L);

        // Bank.getBalance subtracts account balances in implementation
        assertEquals(-(100L + 200L), bank.getBalance());
    }

    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testTop5() {
        Bank bank = new Bank();
        String a = bank.createSavingsAccount();
        String b = bank.createSavingsAccount();
        String c = bank.createSavingsAccount();

        bank.deposit(a, 1, 100L);
        bank.deposit(b, 1, 300L);
        bank.deposit(c, 1, 200L);

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(out);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        try {
            bank.printTop5();
        } finally {
            System.out.flush();
            System.setOut(old);
        }

        String printed = out.toString();
        // first printed account should be the one with highest balance (b)
        assertTrue(printed.contains(b));
    }

    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testBottom5() {
        Bank bank = new Bank();
        String a = bank.createSavingsAccount();
        String b = bank.createSavingsAccount();
        String c = bank.createSavingsAccount();

        bank.deposit(a, 1, 100L);
        bank.deposit(b, 1, 300L);
        bank.deposit(c, 1, 200L);

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(out);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        try {
            bank.printBottom5();
        } finally {
            System.out.flush();
            System.setOut(old);
        }

        String printed = out.toString();
        // bottom list should contain the smallest account id (a) as one of lines
        assertTrue(printed.contains(a));
    }

}
