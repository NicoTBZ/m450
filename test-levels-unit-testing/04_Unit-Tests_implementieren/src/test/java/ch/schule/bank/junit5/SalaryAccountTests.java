package ch.schule.bank.junit5;

import ch.schule.SalaryAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests der Klasse SalaryAccount.
 *
 * @author XXX
 * @version 1.1
 */
public class SalaryAccountTests
{
	/**
	 * Der Test.
	 */
	@Test
	public void test()
	{
		SalaryAccount s = new SalaryAccount("P-1", -500L);

		// can withdraw up to credit limit
		assertTrue(s.deposit(1, 200L));
		assertTrue(s.withdraw(2, 600L)); // final balance = -400 which is >= -500
		assertEquals(-400L, s.getBalance());

		// cannot exceed credit limit
		assertFalse(s.withdraw(3, 200L));
	}
}
