package com.sjsu.creditcardvalidation;

import java.io.IOException;
import java.util.ArrayList;

public interface Validator {

	public ArrayList<CreditCard> validate(String input) throws IOException;

	public void convertFormat(ArrayList<CreditCard> cards, String outputFile);

	public boolean isNumeric(String numStr);

}
