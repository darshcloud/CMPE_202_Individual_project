package com.sjsu.creditcardvalidation;

import static org.junit.Assert.*;

import org.junit.Test;

public class VerifyMasterCCTest {

	@Test
	public void testVerify() {

		VerifyMasterCC verifyMasterCC = new VerifyMasterCC();
		String result = verifyMasterCC.verifyCard("5567894523129089");
		String expected = "MasterCard";
		assertEquals(expected, result);

	}

}
