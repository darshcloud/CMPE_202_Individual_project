package com.sjsu.creditcardvalidation;

import java.io.IOException;
import java.util.ArrayList;

//Using Strategy pattern 
public class Context {

	private Validator strategy;

	public Context(Validator strategy) {
		this.strategy = strategy;
	}

	public ArrayList<CreditCard> runValidate(String input) throws IOException {
		return strategy.validate(input);
	}

	public void runConvertFormat(ArrayList<CreditCard> cards, String outputFile) {
		strategy.convertFormat(cards, outputFile);
	}

}
