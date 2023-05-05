package com.sjsu.creditcardvalidation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLValidator implements Validator {

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

		VerifyMasterCC check1 = new VerifyMasterCC();
		VerifyVisaCC check2 = new VerifyVisaCC();
		VerifyAmExCC check3 = new VerifyAmExCC();
		VerifyDiscoverCC check4 = new VerifyDiscoverCC();

		check1.setNextCard(check2);
		check2.setNextCard(check3);
		check3.setNextCard(check4);

		ArrayList<CreditCard> list = new ArrayList<CreditCard>();
		try {
			File file = new File(input);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("CARD");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String ccNumber = eElement.getElementsByTagName("CARD_NUMBER").item(0).getTextContent();
					String expDate = eElement.getElementsByTagName("EXPIRATION_DATE").item(0).getTextContent();
					String name = eElement.getElementsByTagName("CARD_HOLDER_NAME").item(0).getTextContent();
					
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

					if (!isNumeric(ccNumber)) {
						CreditCard e = new CreditCard(ccNumber, expDate, name, "Invalid", "Invalid: non numeric characters");
						list.add(e);
						continue;
					}

					double temp = Double.valueOf(ccNumber);
					ccNumber = String.format("%.0f", temp);

					String checkHere = check1.verifyCard(ccNumber);

					CreateNewCreditCard createCreditCardFactory = new CreateNewCreditCard();
					CreditCard e = createCreditCardFactory.createInstance(ccNumber, expDate, name, checkHere);
					list.add(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public void convertFormat(ArrayList<CreditCard> cards, String outputFile) {
		System.out.println("XML writing Begins Here");

		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("CARDS");
			document.appendChild(root);

			for (CreditCard card : cards) {
				Element record = document.createElement("CARD");

				root.appendChild(record);

				Element cc = document.createElement("CARD_NUMBER");
				cc.appendChild(document.createTextNode(card.getCardNumber()));
				record.appendChild(cc);

				Element cctype = document.createElement("CARD_TYPE");
				cctype.appendChild(document.createTextNode(card.getCardType()));
				record.appendChild(cctype);

				Element valid = document.createElement("MESSAGE");
				valid.appendChild(document.createTextNode(card.getValidCreditCard()));
				record.appendChild(valid);

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			document.setXmlStandalone(true);
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(outputFile));

			transformer.transform(domSource, streamResult);

			System.out.println("Output XML file has been written successfully");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

}
