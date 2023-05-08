package com.sjsu.creditcardvalidation;

import static org.junit.Assert.*;

import org.junit.Test;

public class VerifyAmExCCTest {

	@Test
	public void testVerify() {

		VerifyAmExCC verifyamex = new VerifyAmExCC();
		String result = verifyamex.verifyCard("377856341908126");
		String expected = "AmericanExpress";
		assertEquals(expected, result);

	}

}
