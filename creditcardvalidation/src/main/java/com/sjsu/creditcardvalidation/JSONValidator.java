package com.sjsu.creditcardvalidation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONValidator implements Validator {

	@Override
	public boolean isNumeric(String numStr) {
		if (numStr == null) {
			return false;
		}
		for (int i = 0; i < numStr.length(); i++) {
			if (!Character.isDigit(numStr.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ArrayList<CreditCard> validate(String input) throws IOException {

		ArrayList<CreditCard> list = new ArrayList<CreditCard>();
		VerifyMasterCC check1 = new VerifyMasterCC();
		VerifyVisaCC check2 = new VerifyVisaCC();
		VerifyAmExCC check3 = new VerifyAmExCC();
		VerifyDiscoverCC check4 = new VerifyDiscoverCC();

		check1.setNextCard(check2);
		check2.setNextCard(check3);
		check3.setNextCard(check4);

		JSONParser jsonParser = new JSONParser();

		try {
			FileReader reader = new FileReader(input);
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONObject jsonObject = (JSONObject) obj;
			JSONArray cards = (JSONArray) jsonObject.get("cards");

			for (int i = 0; i < cards.size(); i++) {
				
				JSONObject cardObject = (JSONObject) cards.get(i);
	            String ccNumber = (String) cardObject.get("cardNumber");
	            String expDate = (String) cardObject.get("expirationDate");
	            String name = (String) cardObject.get("cardHolderName");
	            
				if (ccNumber == null || ccNumber.isEmpty()) {
					CreditCard e = new CreditCard(ccNumber, expDate, name, "Invalid", "Invalid: empty/null card number");
					list.add(e);
					continue;
				}
	            
	            if(ccNumber.length() > 19) {
					CreditCard e = new CreditCard(ccNumber, expDate, name, "Invalid", "Invalid: more than 19 digits");
					list.add(e);
					continue;
				}

				// If Credit card is not a Numeric value, return invalid
				if (!isNumeric(ccNumber)) {
					CreditCard e = new CreditCard(ccNumber, expDate, name, "Invalid", "Invalid: non numeric characters");
					list.add(e);
					continue;
				}
				
				String checkHere = check1.verifyCard(ccNumber);

				CreateNewCreditCard createCreditCardFactory = new CreateNewCreditCard();

				CreditCard e = createCreditCardFactory.createInstance(ccNumber, expDate, name, checkHere);
				list.add(e);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public void convertFormat(ArrayList<CreditCard> cards, String outputFile) {
		System.out.println("JSON writing Begins Here");

		try {
			FileWriter fw = new FileWriter(outputFile);
			fw.write("{");
			fw.write('\n');
			fw.write("    " + "\"cards\": [");
			for (int i = 0; i < cards.size(); i++) {
				CreditCard cc = cards.get(i);
				fw.write("" + " {");
				fw.write('\n');
				fw.write("    " + "\"CardNumber\": " + "\"" + cc.getCardNumber() + "\"" + ",");
				fw.write('\n');
				fw.write("    " + "\"Type\": " + "\"" + cc.getCardType() + "\"" + " ,");
				fw.write('\n');
				fw.write("    " + "\"Message\": " + "\"" + cc.getValidCreditCard() + "\"");
				fw.write('\n');

				if (i == cards.size() - 1) {
					fw.write(" " + "}");
				} else {
					fw.write(" " + "},");
				}
				fw.write('\n');

			}
			fw.write("" + "]");
			fw.write('\n');
			fw.write("}");
			fw.close();
			System.out.println("Output JSON file has been written successfully");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

}
