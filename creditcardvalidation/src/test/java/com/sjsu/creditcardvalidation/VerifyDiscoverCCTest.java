package com.sjsu.creditcardvalidation;

import static org.junit.Assert.*;

import org.junit.Test;

public class VerifyDiscoverCCTest {

	@Test
	public void testVerify() {

		VerifyDiscoverCC verifyDiscoverCC = new VerifyDiscoverCC();
		String result = verifyDiscoverCC.verifyCard("6011111100007756");
		String expected = "Discover";
		assertEquals(expected, result);

	}

}
