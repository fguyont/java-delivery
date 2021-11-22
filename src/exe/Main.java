package exe;

import components.Client;

public class Main {

	public static void main(String[] args) {

		Client[] clients = createArray(3);
		displayClients(clients);
		
	}
	
	static Client[] createArray(int number) {
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

}
