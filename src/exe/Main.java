package exe;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.function.Predicate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfer;

public class Main {

	public static void main(String[] args) {

		int clientNumbers = 3;

		System.out.println("The clients ------------------------------------");
		Client[] clients = createClientArray(clientNumbers);
		displayClients(clients);

		System.out.println("The accounts ------------------------------------");
		ArrayList<Account> accountList = createAccountArray(clients);
		displayAccounts(accountList);

		System.out.println("The hashtable ------------------------------------");
		HashMap<Integer, Account> hAccounts = createHashtable(accountList);
		displayHashtable(hAccounts);

		System.out.println("The flows ------------------------------------");
		ArrayList <Flow> flowList = createFlowArray(accountList);
		displayFlows(flowList);

		System.out.println("The updates ------------------------------------");
		updateAccounts(flowList, hAccounts);
		displayHashtable(hAccounts);

		System.out.println("With JSon file flows ------------------------------------");
		addJSonFlows(flowList);
		updateAccounts(flowList, hAccounts);
		displayFlows(flowList);
		displayHashtable(hAccounts);
		
		System.out.println("With XML file account ------------------------------------");
		addXmlAccounts(accountList, clients);
		displayAccounts(accountList);
		hAccounts = createHashtable(accountList);
		displayHashtable(hAccounts);

	}

	static Client[] createClientArray(int number) {
		Client[] clients = new Client[number];
		clients[0] = new Client("Dupont", "Albert");
		clients[1] = new Client("Le Roux", "Jacques");
		clients[2] = new Client("Rivière", "Anne");
		return clients;
	}

	static void displayClients(Client[] clients) {
		for (Client c : clients) {
			System.out.println(c.toString());
		}
	}

	static ArrayList<Account> createAccountArray(Client[] clients) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts.add(new CurrentAccount("Current", clients[0]));
		accounts.add(new SavingsAccount("Savings", clients[0]));
		accounts.add(new CurrentAccount("Current", clients[1]));
		accounts.add(new SavingsAccount("Savings", clients[1]));
		accounts.add(new CurrentAccount("Current", clients[2]));
		accounts.add(new SavingsAccount("Savings", clients[2]));

		return accounts;
	}

	static void displayAccounts(ArrayList<Account> accounts) {
		for (Account a : accounts) {
			System.out.println(a.toString());
		}
	}

	static HashMap<Integer,Account> createHashtable(ArrayList<Account> accounts) {
		HashMap<Integer, Account> hAccounts = new HashMap<Integer, Account>();

		for (Account a : accounts) {
			hAccounts.put(a.getAccountNumber(), a);
		}

		return hAccounts;
	}

	static void displayHashtable (HashMap<Integer,Account> hAccounts) {

		for (Map.Entry<Integer, Account> entry : hAccounts.entrySet()) {

			System.out.println (entry.toString());
		}




	}

	static ArrayList<Flow> createFlowArray(ArrayList<Account> accounts) {
		ArrayList<Flow> flowList = new ArrayList<Flow>();

		flowList.add(new Debit("Debit", 50.0, 1));
		flowList.add(new Credit("Credit", 100.50, accounts.get(0).getAccountNumber()));
		flowList.add(new Credit("Credit", 100.50, accounts.get(2).getAccountNumber()));
		flowList.add(new Credit("Credit", 100.50, accounts.get(4).getAccountNumber()));
		flowList.add(new Credit("Credit", 1500.00, accounts.get(1).getAccountNumber()));
		flowList.add(new Credit("Credit", 1500.00, accounts.get(3).getAccountNumber()));
		flowList.add(new Credit("Credit", 1500.00, accounts.get(5).getAccountNumber()));
		flowList.add(new Transfer("Transfer", 50.0, 1, 2));

		return flowList;
	}

	static void displayFlows (ArrayList<Flow> flows) {
		for (Flow flow : flows) {
			System.out.println(flow.toString());
		}
	}


	static void updateAccounts (ArrayList<Flow> flows, HashMap<Integer, Account> hAccounts) {

		for (Flow flow : flows) {

			hAccounts.get(flow.getTargetAccountNumber()).setBalance(flow);
		}

		for (Map.Entry<Integer, Account> entry : hAccounts.entrySet()) {
			Predicate<Double> tester = value -> value < 0.0;
			double value = entry.getValue().getBalance();
			if (tester.test(value) == true) {
				System.out.println("The account number " +entry.getKey()+ " has a negative balance " +entry.getValue().getBalance());
			}
		}

	}

	static void addJSonFlows (ArrayList<Flow> flows) {
		
		JSONParser parser = new JSONParser();
		try {
			JSONArray jSONArray = (JSONArray) parser.parse(new FileReader("./resources/FGBank-flows.json"));

			for (Object o : jSONArray)
			{

				JSONObject jsonObject =  (JSONObject) o;

				String comment = jsonObject.get("comment").toString();
				double amount = Double.valueOf(jsonObject.get("amount").toString()) ;
				int targetAccountNumber = Integer.parseInt(jsonObject.get("target_account_number").toString());
				
				if (comment.equals("Credit")) {		
					flows.add(new Credit (comment, amount, targetAccountNumber)) ;
				}
				
				if (comment.equals("Debit")) {		
					flows.add(new Debit (comment, amount, targetAccountNumber));;
				}
				
				if (comment.equals("Transfer")) {
					int issuingAccountNumber = Integer.parseInt(jsonObject.get("issuing_account_number").toString());
					flows.add(new Transfer (comment, amount, targetAccountNumber, issuingAccountNumber));
				}

				
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static Client getById (Client[] clients, int clientId) {
		for (Client client : clients) {
			if (clientId == client.getClientNumber()) {
				return client;
			}
		}
		return null;
	}
	
	static void addXmlAccounts (ArrayList<Account> accounts, Client[] clients) {
		try {
		    File fXmlFile = new File("./resources/FGBank-accounts.xml");
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(fXmlFile);
		    doc.getDocumentElement().normalize();

		//  System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		    NodeList nList = doc.getElementsByTagName("account");
		//  System.out.println("----------------------------");

		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
	   //       System.out.println("\nCurrent Element :" + nNode.getNodeName());
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		            Element eElement = (Element) nNode;
		            
		            String label = eElement.getElementsByTagName("label").item(0).getTextContent();
		            int clientNumber = Integer.parseInt(eElement.getElementsByTagName("client-number").item(0).getTextContent());
		            Client client = getById(clients, clientNumber);
		            
		            if (label.equals("Current")) {
		            	accounts.add(new CurrentAccount(label, client));
		            }
		            
		            if (label.equals("Savings")) {
		            	accounts.add(new SavingsAccount(label, client));
		            }
		        }
		    }
		    } catch (Exception e) {
		    e.printStackTrace();
		    }
		  
	}
}
