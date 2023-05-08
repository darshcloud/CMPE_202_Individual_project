package com.sjsu.creditcardvalidation;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreditCardTest {

	@Test
	public void testCreditCardConstructor() {
		String cardNumber = "5567894523129089";
		String expiryDate = "08/26";
		String cardHolderName = "John DoE";
		String cardType = "MasterCard";
		String validCreditCard = "Valid";
		CreditCard creditCardObj = new CreditCard(cardNumber, expiryDate, cardHolderName, cardType, validCreditCard);
		assertEquals(cardNumber, creditCardObj.getCardNumber());
		assertEquals(expiryDate, creditCardObj.getExpiryDate());
		assertEquals(cardHolderName, creditCardObj.getCardHolderName());
		assertEquals(cardType, creditCardObj.getCardType());
		assertEquals(validCreditCard, creditCardObj.getValidCreditCard());
	}

}
