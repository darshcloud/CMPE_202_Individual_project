package com.sjsu.creditcardvalidation;

public class CreditCard {

	protected String cardNumber;
	protected String expiryDate;
	protected String cardHolderName;
	protected String cardType;
	protected String validCreditCard;

	public CreditCard(String cardNumber, String expiryDate, String cardHolderName, String cardType,
			String validCreditCard) {
		super();
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cardHolderName = cardHolderName;
		this.cardType = cardType;
		this.validCreditCard = validCreditCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getValidCreditCard() {
		return validCreditCard;
	}

	public void setValidCreditCard(String validCreditCard) {
		this.validCreditCard = validCreditCard;
	}

}
