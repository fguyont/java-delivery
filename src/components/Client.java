 // 1.1.1 Creation of the client class

package components;

import java.util.Objects;

public class Client {

	private String name;
	private String firstName;
	private int clientNumber;
	private static int lastClientNumber = 1;
	
	public Client(String name, String firstName) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.clientNumber = lastClientNumber;
		lastClientNumber++;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}
	public static int getLastClientNumber() {
		return lastClientNumber;
	}
	public static void setLastClientNumber(int lastClientNumber) {
		Client.lastClientNumber = lastClientNumber;
	}
	
	@Override
	public String toString() {
		return "Client [name=" + name + ", firstName=" + firstName + ", clientNumber=" + clientNumber + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(clientNumber, firstName, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return clientNumber == other.clientNumber && Objects.equals(firstName, other.firstName)
				&& Objects.equals(name, other.name);
	}	
	
}

