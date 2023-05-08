package com.sjsu.creditcardvalidation;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MasterCCTest {

	@Test
	public void testConstructor() {
		
		String cardNumber = "5567894523129089";
		String expiryDate = "08/26";
		String cardHolderName = "John DoE";
		String cardType = "MasterCard";
		String validCreditCard = "Valid";
		MasterCC masterCC = new MasterCC(cardNumber, expiryDate, cardHolderName, cardType, validCreditCard);
		assertEquals(cardNumber, masterCC.getCardNumber());
		assertEquals(expiryDate, masterCC.getExpiryDate());
		assertEquals(cardHolderName, masterCC.getCardHolderName());
		assertEquals(cardType, masterCC.getCardType());
		assertEquals(validCreditCard, masterCC.getValidCreditCard());
		
	}

}
