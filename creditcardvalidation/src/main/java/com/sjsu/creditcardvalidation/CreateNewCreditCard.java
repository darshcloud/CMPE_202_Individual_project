package com.sjsu.creditcardvalidation;

public class CreateNewCreditCard implements CreditCardFactory {

	public CreditCard createInstance(String str1, String str2, String str3, String checkStr) {
		if (checkStr.equals("MasterCard")) {
			return new MasterCC(str1, str2, str3, checkStr, "valid");
		} else if (checkStr.equals("Visa")) {
			return new VisaCC(str1, str2, str3, checkStr, "valid");
		} else if (checkStr.equals("AmericanExpress")) {
			return new AmExCC(str1, str2, str3, checkStr, "valid");
		} else if (checkStr.equals("Discover")) {
			return new DiscoverCC(str1, str2, str3, checkStr, "valid");
		} else {
			return new CreditCard(str1, str2, str3, checkStr, "invalid");
		}
	}

}
