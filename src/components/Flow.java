package components;

import java.util.Date;
import java.util.Objects;

public abstract class Flow {

	private String comment;
	private int identifier;
	private double amount;
	private int targetAccountNumber;
	private boolean effect;
	private Date dateOfFlow;
	private static int lastFlowIdentifier = 1;
	
	public Flow(String comment, double amount, int targetAccountNumber, boolean effect,
			Date dateOfFlow) {
		super();
		this.comment = comment;
		this.identifier = lastFlowIdentifier;
		lastFlowIdentifier++;
		this.amount = amount;
		this.targetAccountNumber = targetAccountNumber;
		this.effect = effect;
		this.dateOfFlow = dateOfFlow;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIdentifier() {
		return identifier;
	}
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getTargetAccountNumber() {
		return targetAccountNumber;
	}
	public void setTargetAccountNumber(int targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}
	public boolean isEffect() {
		return effect;
	}
	public void setEffect(boolean effect) {
		this.effect = effect;
	}
	public Date getDateOfFlow() {
		return dateOfFlow;
	}
	public void setDateOfFlow(Date dateOfFlow) {
		this.dateOfFlow = dateOfFlow;
	}
	public static int getLastFlowIdentifier() {
		return lastFlowIdentifier;
	}
	public static void setLastFlowIdentifier(int lastFlowIdentifier) {
		Flow.lastFlowIdentifier = lastFlowIdentifier;
	}
	@Override
	public String toString() {
		return "Flow [comment=" + comment + ", identifier=" + identifier + ", amount=" + amount
				+ ", targetAccountNumber=" + targetAccountNumber + ", effect=" + effect + ", dateOfFlow=" + dateOfFlow
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, comment, dateOfFlow, effect, identifier, targetAccountNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flow other = (Flow) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(comment, other.comment) && Objects.equals(dateOfFlow, other.dateOfFlow)
				&& effect == other.effect && identifier == other.identifier
				&& targetAccountNumber == other.targetAccountNumber;
	}
	
	
}
