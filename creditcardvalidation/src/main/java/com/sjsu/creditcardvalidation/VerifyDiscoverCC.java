package com.sjsu.creditcardvalidation;

public class VerifyDiscoverCC implements CreditCardHandler {

	private CreditCardHandler nextCard;

	@Override
	public void setNextCard(CreditCardHandler nextHandler) {
		this.nextCard = nextHandler;
	}

	@Override
	public String verifyCard(String creditCardNumber) {
		if (creditCardNumber.matches("^6011\\d{12}$")) {
			return "Discover";
		} else {
			return "invalid";
		}
	}

}
