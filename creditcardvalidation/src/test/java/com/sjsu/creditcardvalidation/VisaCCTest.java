package com.sjsu.creditcardvalidation;

import static org.junit.Assert.*;

import org.junit.Test;

public class VisaCCTest {

	@Test
	public void testConstructor() {
		
		String cardNumber = "4123456789123";
		String expiryDate = "04/26";
		String cardHolderName = "Martha Clark";
		String cardType = "Visa";
		String validCreditCard = "Valid";
		VisaCC visaCC = new VisaCC(cardNumber, expiryDate, cardHolderName, cardType, validCreditCard);
		assertEquals(cardNumber, visaCC.getCardNumber());
		assertEquals(expiryDate, visaCC.getExpiryDate());
		assertEquals(cardHolderName, visaCC.getCardHolderName());
		assertEquals(cardType, visaCC.getCardType());
		assertEquals(validCreditCard, visaCC.getValidCreditCard());
		
	}

}
