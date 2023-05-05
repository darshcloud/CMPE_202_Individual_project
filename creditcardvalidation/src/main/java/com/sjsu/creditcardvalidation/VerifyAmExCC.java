package com.sjsu.creditcardvalidation;

public class VerifyAmExCC implements CreditCardHandler {

	private CreditCardHandler nextCard;

	@Override
	public void setNextCard(CreditCardHandler nextHandler) {
		this.nextCard = nextHandler;
	}

	@Override
	public String verifyCard(String creditCardNumber) {
		if (creditCardNumber.matches("^3[47][0-9]{13}$")) {
			return "AmericanExpress";
		} else {
			return nextCard.verifyCard(creditCardNumber);
		}

	}

}
