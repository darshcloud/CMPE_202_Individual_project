package com.sjsu.creditcardvalidation;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreateNewCreditCardTest {

	@Test
	public void testCreateInstance() {
		CreateNewCreditCard createNewCC = new CreateNewCreditCard();

		// Test case for MasterCard
		CreditCard masterCard = createNewCC.createInstance("5567894523129089", "08/26", "John DoE", "MasterCard");
		assertTrue(masterCard instanceof MasterCC);
		assertEquals("Valid", masterCard.getValidCreditCard());

		// Test case for Visa
		CreditCard visaCard = createNewCC.createInstance("4123456789123", "04/26", "Martha Clark", "Visa");
		assertTrue(visaCard instanceof VisaCC);
		assertEquals("Valid", visaCard.getValidCreditCard());

		// Test case for AmericanExpress
		CreditCard amexCard = createNewCC.createInstance("347856341908126", "03/23", "Jane S. Dayton",
				"AmericanExpress");
		assertTrue(amexCard instanceof AmExCC);
		assertEquals("Valid", amexCard.getValidCreditCard());

		// Test case for Discover
		CreditCard discoverCard = createNewCC.createInstance("6011111100007756", "02/24", "John Doe", "Discover");
		assertTrue(discoverCard instanceof DiscoverCC);
		assertEquals("Valid", discoverCard.getValidCreditCard());

		// Test case for invalid card type
		CreditCard invalidCard = createNewCC.createInstance("3601112345678789", "06/24", "Lara Wayne",
				"InvalidCardType");
		assertFalse(invalidCard instanceof MasterCC);
		assertFalse(invalidCard instanceof VisaCC);
		assertFalse(invalidCard instanceof AmExCC);
		assertFalse(invalidCard instanceof DiscoverCC);
		assertEquals("Invalid: Not a possible card number", invalidCard.getValidCreditCard());

	}

}
