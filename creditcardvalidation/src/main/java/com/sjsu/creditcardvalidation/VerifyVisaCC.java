package com.sjsu.creditcardvalidation;

public class VerifyVisaCC implements CreditCardHandler {

	private CreditCardHandler nextCard;

	@Override
	public void setNextCard(CreditCardHandler nextHandler) {
		this.nextCard = nextHandler;

	}

	@Override
	public String verifyCard(String creditCardNumber) {
		if (creditCardNumber.matches("^4(\\\\d{12}|\\\\d{15})$")) {
			return "Visa";
		} else {
			return nextCard.verifyCard(creditCardNumber);
		}

	}

}
