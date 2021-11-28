package components;

import java.util.Date;

public class Transfert extends Flow{


	public Transfert(String comment, double amount, int targetAccountNumber, boolean effect, Date dateOfFlow) {
		super(comment, amount, targetAccountNumber, effect, dateOfFlow);
		// TODO Auto-generated constructor stub
	}

	private int issuingAccountNumber;
	
}
