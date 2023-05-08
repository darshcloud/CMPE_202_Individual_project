package com.sjsu.creditcardvalidation;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class ContextTest {

	Validator strategy = new XMLValidator();

	Context context = new Context(strategy);
	ArrayList<CreditCard> ccList = new ArrayList<CreditCard>();

	@Test
	public void testRunValidate() throws IOException {

		ArrayList<CreditCard> result = context.runValidate("Sample_Test_Files/input_file.xml");

		CreditCard firstCard = result.get(0);
		assertEquals("5567894523129089", firstCard.getCardNumber());
		assertEquals("MasterCard", firstCard.getCardType());
		assertEquals("Valid", firstCard.getValidCreditCard());

		CreditCard secondCard = result.get(1);
		assertEquals("59012345678901234567890", secondCard.getCardNumber());
		assertEquals("Invalid", secondCard.getCardType());
		assertEquals("Invalid: more than 19 digits", secondCard.getValidCreditCard());

		CreditCard thirdCard = result.get(2);
		assertEquals("4123456789123", thirdCard.getCardNumber());
		assertEquals("Visa", thirdCard.getCardType());
		assertEquals("Valid", thirdCard.getValidCreditCard());

		CreditCard fourthCard = result.get(3);
		assertEquals("347856341908126", fourthCard.getCardNumber());
		assertEquals("AmericanExpress", fourthCard.getCardType());
		assertEquals("Valid", fourthCard.getValidCreditCard());

	}

	@Test
	public void testRunConvertFormat() {

		ccList.add(new CreditCard("5410000000000000", "3/20", "Alice", "MasterCard", "Valid"));
		ccList.add(new CreditCard("4120000000000", "4/20", "Bob", "Visa", "Valid"));
		ccList.add(new CreditCard("341000000000000", "5/20", "Eve", "AmericanExpress", "Valid"));
		ccList.add(new CreditCard("6011*890HJrt6789", "6/20", "Richard", "Invalid", "Invalid: non numeric characters"));

		context.runConvertFormat(ccList, "Sample_Test_Files/sample_output.xml");

	}

}
