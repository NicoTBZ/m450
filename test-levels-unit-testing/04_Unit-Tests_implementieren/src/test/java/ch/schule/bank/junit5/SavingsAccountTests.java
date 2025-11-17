package ch.schule.bank.junit5;

import ch.schule.SavingsAccount;



/**
 * Tests f�r die Klasse SavingsAccount.
 *
 * @author Roger H. J&ouml;rg
 * @version 1.0
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests für die Klasse SavingsAccount.
 *
 * @author XXX
 * @version 1.0
 */
public class SavingsAccountTests
{
	@Test
	public void test()
	{
		SavingsAccount s = new SavingsAccount("S-10");

		// deposit and withdraw within balance
		assertTrue(s.deposit(1, 1000L));
		assertEquals(1000L, s.getBalance());

		assertTrue(s.withdraw(2, 500L));
		assertEquals(500L, s.getBalance());

		// withdraw more than balance should fail
		assertFalse(s.withdraw(3, 600L));
	}
}

