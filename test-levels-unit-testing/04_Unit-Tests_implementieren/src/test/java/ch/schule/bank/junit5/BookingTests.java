package ch.schule.bank.junit5;

import ch.schule.Booking;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests für die Klasse Booking.
 *
 * @author Luigi Cavuoti
 * @version 1.1
 */
public class BookingTests
{
	/**
	 * Tests f�r die Erzeugung von Buchungen.
	 */
	@Test
	public void testInitialization()
	{
		Booking b = new Booking(10, 500L);

		assertEquals(10, b.getDate());
		assertEquals(500L, b.getAmount());
	}

	/**
	 * Experimente mit print().
	 */
	@Test
	public void testPrint()
	{
		Booking b = new Booking(1, 12345L);

		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
		java.io.PrintStream ps = new java.io.PrintStream(out);
		java.io.PrintStream old = System.out;
		System.setOut(ps);
		try {
			b.print(0);
		} finally {
			System.out.flush();
			System.setOut(old);
		}

		String printed = out.toString();
		// should contain formatted date and amount
		assertTrue(printed.contains(ch.schule.BankUtils.formatBankDate(1)));
		assertTrue(printed.contains(ch.schule.BankUtils.formatAmount(12345L)));
	}
}
