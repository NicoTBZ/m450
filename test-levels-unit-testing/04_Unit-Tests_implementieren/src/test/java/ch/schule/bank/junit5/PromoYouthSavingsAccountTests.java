package ch.schule.bank.junit5;

import ch.schule.PromoYouthSavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für das Promo-Jugend-Sparkonto.
 *
 * @author XXXX
 * @version 1.0
 */
public class PromoYouthSavingsAccountTests
{
	/**
	 * Der Test.
	 */
	@Test
	public void test()
	{
		PromoYouthSavingsAccount p = new PromoYouthSavingsAccount("Y-1");

		// deposit of 100 should get 1% bonus -> 101 total
		assertTrue(p.deposit(1, 100L));
		assertEquals(101L, p.getBalance());
	}
}
