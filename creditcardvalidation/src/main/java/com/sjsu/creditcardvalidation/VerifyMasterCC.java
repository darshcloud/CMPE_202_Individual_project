package com.sjsu.creditcardvalidation;

public class VerifyMasterCC implements CreditCardHandler {

	private CreditCardHandler nextCard;

	@Override
	public void setNextCard(CreditCardHandler nextHandler) {
		this.nextCard = nextHandler;

	}

	@Override
	public String verifyCard(String creditCardNumber) {
		if (creditCardNumber.matches("^5[1-5]\\d{14}$")) {
			return "MasterCard";
		} else {
			return nextCard.verifyCard(creditCardNumber);
		}
	}

}
