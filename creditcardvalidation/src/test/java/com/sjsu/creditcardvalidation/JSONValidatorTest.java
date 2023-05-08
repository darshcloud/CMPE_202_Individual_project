package com.sjsu.creditcardvalidation;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class JSONValidatorTest {

	private JSONValidator jsonValidator;

	@Test
	public void testNumeric() {
		JSONValidator jsonValidator = new JSONValidator();

		// Test with a valid numeric string
		String validNumStr = "12345";
		assertTrue(jsonValidator.isNumeric(validNumStr));

		// Test with null input
		String nullStr = null;
		assertFalse(jsonValidator.isNumeric(nullStr));

		// Test with a non-numeric string
		String nonNumericStr = "abc";
		assertFalse(jsonValidator.isNumeric(nonNumericStr));

		// Test with a string containing a mix of numeric and non-numeric characters
		String mixStr = "123abc";
		assertFalse(jsonValidator.isNumeric(mixStr));

	}

	@Before
	public void setUp() {
		jsonValidator = new JSONValidator();
	}

	@Test
	public void testValidatewithValidInput() throws IOException {
		ArrayList<CreditCard> result = jsonValidator.validate("Sample_Test_Files/input_file-1.json");

		CreditCard firstCard = result.get(0);
		assertEquals("5567894523129089", firstCard.getCardNumber());
		assertEquals("MasterCard", firstCard.getCardType());
		assertEquals("Valid", firstCard.getValidCreditCard());

		CreditCard secondCard = result.get(2);
		assertEquals("4123456789123", secondCard.getCardNumber());
		assertEquals("Visa", secondCard.getCardType());
		assertEquals("Valid", secondCard.getValidCreditCard());

		CreditCard thirdCard = result.get(3);
		assertEquals("347856341908126", thirdCard.getCardNumber());
		assertEquals("AmericanExpress", thirdCard.getCardType());
		assertEquals("Valid", thirdCard.getValidCreditCard());

	}

	@Test
	public void testValidatewithInvalidInput() throws IOException {

		ArrayList<CreditCard> result = jsonValidator.validate("Sample_Test_Files/input_file-1.json");

		CreditCard firstCard = result.get(1);
		assertEquals("59012345678901234567890", firstCard.getCardNumber());
		assertEquals("Invalid", firstCard.getCardType());
		assertEquals("Invalid: more than 19 digits", firstCard.getValidCreditCard());

		CreditCard secondcard = result.get(5);
		assertEquals("3601112345678789", secondcard.getCardNumber());
		assertEquals("Invalid", secondcard.getCardType());
		assertEquals("Invalid: Not a possible card number", secondcard.getValidCreditCard());

	}

}
