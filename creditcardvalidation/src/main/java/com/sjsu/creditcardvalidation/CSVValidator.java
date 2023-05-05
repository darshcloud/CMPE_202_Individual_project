package com.sjsu.creditcardvalidation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class CSVValidator implements Validator {

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

		String line = "";
		String splitBy = ",";
		try {
			BufferedReader br = new BufferedReader(new FileReader(input));
			br.readLine();
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				CreateNewCreditCard createCreditCardFactory = new CreateNewCreditCard();
				String[] Credit = line.split(splitBy); // use comma as separator
				String number = Credit[0];
				String expDate = Credit[1];
				String name = Credit[2];

				if (number == null || number.isEmpty()) {
					CreditCard e = new CreditCard(number, expDate, name, "Invalid", "Invalid: empty/null card number");
					list.add(e);
					continue;
				}

				if (number.length() > 19) {
					CreditCard e = new CreditCard(number, expDate, name, "Invalid",
							"Invalid: more than 19 digits");
					list.add(e);
					continue;
				}

				if (!isNumeric(number)) {
					CreditCard e = new CreditCard(number, expDate, name, "Invalid", "Invalid: non numeric characters");
					list.add(e);
					continue;
				}

				double temp = Double.valueOf(number);
				String ccNumber = String.format("%.0f", temp);

				String checkHere = check1.verifyCard(ccNumber);

				CreditCard e = createCreditCardFactory.createInstance(ccNumber, expDate, name, checkHere);
				list.add(e);

			}
			br.close();

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public void convertFormat(ArrayList<CreditCard> cards, String outputFile) {

		System.out.println("CSV writing Begins Here");

		File file = new File(outputFile);
		try {
			FileWriter outputfile = new FileWriter(file);

			CSVWriter writer = new CSVWriter(outputfile, CSVWriter.DEFAULT_SEPARATOR, '\0', CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "cardNumber", "cardType", "Message" });
			for (int i = 0; i < cards.size(); i++) {
				String cardNumber = String.valueOf(cards.get(i).getCardNumber());
				String cardType = cards.get(i).getCardType();
				String message = cards.get(i).getValidCreditCard();
				data.add(new String[] { cardNumber, cardType, message });
			}

			writer.writeAll(data);

			writer.close();
			System.out.println("Output CSV file has been written successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
