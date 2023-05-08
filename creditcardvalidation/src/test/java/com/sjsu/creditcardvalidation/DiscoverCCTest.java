package com.sjsu.creditcardvalidation;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiscoverCCTest {

	@Test
	public void testConstructor() {

		String cardNumber = "6011111100007756";
		String expiryDate = "02/24";
		String cardHolderName = "John Doe";
		String cardType = "Discover";
		String validCreditCard = "Valid";
		DiscoverCC discoverCC = new DiscoverCC(cardNumber, expiryDate, cardHolderName, cardType, validCreditCard);
		assertEquals(cardNumber, discoverCC.getCardNumber());
		assertEquals(expiryDate, discoverCC.getExpiryDate());
		assertEquals(cardHolderName, discoverCC.getCardHolderName());
		assertEquals(cardType, discoverCC.getCardType());
		assertEquals(validCreditCard, discoverCC.getValidCreditCard());

	}

}
