package com.sjsu.creditcardvalidation;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
	public static void main(String[] args) throws IOException {
		String inputFile, outputFile, inputExtension, outputExtension;
		inputFile = "";
		outputFile = "";
		if (args.length != 2) {
	        System.out.println("Please provide both input file name and output file name");
	        System.exit(0);
	    }
		else {
			inputFile = args[0];
			outputFile = args[1];
			inputExtension = inputFile.substring(inputFile.indexOf("."));
			outputExtension = outputFile.substring(outputFile.indexOf("."));

			if (!inputExtension.equals(outputExtension)) {
				System.out.println("Please Enter Same Input-Output File Formats");
				System.exit(0);
			} else {
				System.out.println("Entered Input File: " + inputFile);
				System.out.println("Entered Output File: " + outputFile);
			}
		}

		ArrayList<CreditCard> outputList = new ArrayList<CreditCard>();

		if (inputFile.contains(".csv")) {
			Context csv = new Context(new CSVValidator());
			try {
				outputList = csv.runValidate(inputFile);
				csv.runConvertFormat(outputList, outputFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (inputFile.contains(".json")) {
			Context json = new Context(new JSONValidator());
			try {
				outputList = json.runValidate(inputFile);
				json.runConvertFormat(outputList, outputFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (inputFile.contains(".xml")) {
			Context xml = new Context(new XMLValidator());
			try {
				outputList = xml.runValidate(inputFile);
				xml.runConvertFormat(outputList, outputFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Unsupported File Types");
			System.exit(0);
		}

	}
}
