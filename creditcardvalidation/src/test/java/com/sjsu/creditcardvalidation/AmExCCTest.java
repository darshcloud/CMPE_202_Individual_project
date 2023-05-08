package com.sjsu.creditcardvalidation;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AmExCCTest {
	@Test
	public void testConstructor() {
		
		String cardNumber = "347856341908126";
		String expiryDate = "03/23";
		String cardHolderName = "Jane S. Dayton";
		String cardType = "AmericanExpress";
		String validCreditCard = "Valid";
		AmExCC amExCC = new AmExCC(cardNumber, expiryDate, cardHolderName, cardType, validCreditCard);
		assertEquals(cardNumber, amExCC.getCardNumber());
		assertEquals(expiryDate, amExCC.getExpiryDate());
		assertEquals(cardHolderName, amExCC.getCardHolderName());
		assertEquals(cardType, amExCC.getCardType());
		assertEquals(validCreditCard, amExCC.getValidCreditCard());
		
	}
}
