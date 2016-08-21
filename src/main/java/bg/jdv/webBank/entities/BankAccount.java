package bg.jdv.webBank.entities;

public class BankAccount {

	private String accountNumber;
	private String createdBy;
	private String currency;	
	private double amount;
	private BankUser user;
	
	public BankAccount(){
		
	}
		
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BankUser getUser() {
		return user;
	}
	public void setUser(BankUser user) {
		this.user = user;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
