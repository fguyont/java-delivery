 // 1.2.1 Creation of the account class

package components;

import java.util.Objects;

public abstract class Account {

	protected String label;
	protected double balance;
	protected int accountNumber;
	protected static int lastAccountNumber = 1;
	protected Client client;
	public Account(String label, Client client) {
		super();
		this.label = label;
		this.accountNumber = lastAccountNumber;
		lastAccountNumber++;
		this.client = client;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public static int getLastAccountNumber() {
		return lastAccountNumber;
	}
	public static void setLastAccountNumber(int lastAccountNumber) {
		Account.lastAccountNumber = lastAccountNumber;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	@Override
	public String toString() {
		return "Account [label=" + label + ", balance=" + balance + ", accountNumber=" + accountNumber + ", client="
				+ client + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, balance, client, label);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountNumber == other.accountNumber
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(client, other.client) && Objects.equals(label, other.label);
	}
	
	
}
