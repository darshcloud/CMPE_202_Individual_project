package com.sjsu.creditcardvalidation;

import static org.junit.Assert.*;

import org.junit.Test;

public class VerifyVisaCCTest {

	@Test
	public void testVerify() {

		VerifyVisaCC verifyVisaCC = new VerifyVisaCC();
		String result = verifyVisaCC.verifyCard("4123456789123");
		String expected = "Visa";
		assertEquals(expected, result);

	}

}
