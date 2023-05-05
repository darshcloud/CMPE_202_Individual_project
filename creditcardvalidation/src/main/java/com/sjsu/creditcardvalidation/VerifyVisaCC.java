package com.sjsu.creditcardvalidation;

public class VerifyVisaCC implements CreditCardHandler {

	private CreditCardHandler nextCard;

	@Override
	public void setNextCard(CreditCardHandler nextHandler) {
		this.nextCard = nextHandler;

	}

	@Override
	public String verifyCard(String creditCardNumber) {
		/*
		 * char first= creditCardNumber.charAt(0); if(first=='4' &&
		 * (creditCardNumber.length()==13||creditCardNumber.length()==16)) return
		 * "Visa"; else return nextCard.verifyCard(creditCardNumber);
		 */
		
		if (creditCardNumber.matches("^4[0-9]{12}(?:[0-9]{3})?$")) {
			return "Visa";
		} else {
			return nextCard.verifyCard(creditCardNumber);
		}
	}

}
