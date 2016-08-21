package bg.jdv.webBank.service;

import bg.jdv.webBank.entities.BankAccount;

public interface WebBankProcess {
	
	String accountProcess(BankAccount account, double operationAmount, String operation);

}
