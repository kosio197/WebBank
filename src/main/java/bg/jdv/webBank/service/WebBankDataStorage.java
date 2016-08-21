package bg.jdv.webBank.service;

import java.util.List;

import bg.jdv.webBank.entities.BankAccount;
import bg.jdv.webBank.entities.BankUser;

public interface WebBankDataStorage {
	
	BankUser addUser(String username, BankUser user);	
	BankUser getUser(String username);
	boolean removeUser(String username);	

	BankAccount addAccount(String accountNumber, String createdBy, String currency,double amount,BankUser user);
	BankAccount getAccount(String accountNumber);
	boolean removeAccount(String accountNumber);
	
	List<BankAccount> getAllAccounts();
	List<BankAccount> getUserAccounts(String username);
}
