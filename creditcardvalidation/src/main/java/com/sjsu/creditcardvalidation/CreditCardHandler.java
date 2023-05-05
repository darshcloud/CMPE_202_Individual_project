package com.sjsu.creditcardvalidation;

public interface CreditCardHandler {

	public void setNextCard(CreditCardHandler nextHandler);

	public String verifyCard(String creditCardNumber);

}
