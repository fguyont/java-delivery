// 1.3.3 Creation of the transfer class


package components;

public class Transfer extends Flow{


	private int issuingAccountNumber;

	public Transfer(String comment, double amount, int targetAccountNumber, int issuingAccountNumber) {
		super(comment, amount, targetAccountNumber);
		this.issuingAccountNumber = issuingAccountNumber;
	}

	public int getIssuingAccountNumber() {
		return issuingAccountNumber;
	}

	public void setIssuingAccountNumber(int issuingAccountNumber) {
		this.issuingAccountNumber = issuingAccountNumber;
	}
	

}
