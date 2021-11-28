package exe;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

public class Main {

	public static void main(String[] args) {
		
		int clientNumbers = 3;

		System.out.println("The clients ------------------------------------");
		Client[] clients = createClientArray(clientNumbers);
		displayClients(clients);
		
		System.out.println("The accounts ------------------------------------");
		Account[] accounts = createAccountArray(clients, clientNumbers*2);
		displayAccounts(accounts);
		
		System.out.println("The hashtable ------------------------------------");
		Hashtable<Integer, Account> hAccounts = createHashtable(accounts);
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

	static Account[] createAccountArray(Client[] clients, int number) {
		Account[] accounts = new Account[number];
		accounts[0] = new CurrentAccount("Current", clients[0]);
		accounts[0].setBalance(0.0);
		accounts[1] = new SavingsAccount("Savings", clients[0]);
		accounts[1].setBalance(0.0);
		accounts[2] = new CurrentAccount("Current", clients[1]);
		accounts[2].setBalance(0.0);
		accounts[3] = new SavingsAccount("Savings", clients[1]);
		accounts[3].setBalance(0.0);
		accounts[4] = new CurrentAccount("Current", clients[2]);
		accounts[4].setBalance(0.0);
		accounts[5] = new SavingsAccount("Savings", clients[2]);
		accounts[5].setBalance(0.0);
			
		return accounts;
	}
	
	static void displayAccounts(Account[] accounts) {
		for (Account a : accounts) {
			System.out.println(a.toString());
		}
	}
	
	static Hashtable<Integer,Account> createHashtable(Account[] accounts) {
		Hashtable<Integer, Account> hAccounts = new Hashtable<Integer, Account>();
		
		for (Account a : accounts) {
			hAccounts.put(a.getAccountNumber(), a);
		}
		
		return hAccounts;
	}
	
	static void displayHashtable (Hashtable<Integer,Account> hAccounts) {
		
		for (Map.Entry<Integer, Account> entry : hAccounts.entrySet()) {

		    System.out.println (entry.toString());
		}
               
		
		
		
	}
}
