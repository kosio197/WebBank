package bg.jdv.webBank.service;

import org.springframework.stereotype.Service;

import bg.jdv.webBank.entities.BankAccount;

@Service
public class WebBankProcessImpl implements WebBankProcess {

    @Override
    public String accountProcess(BankAccount account, double operationAmount, String operation) {

        if (operationAmount <= 0) {
            return "The amount should be a positive number!";
        }

        if (operation.equals("deposit")) {
            double newAmount = operationAmount + account.getAmount();
            account.setAmount(newAmount);
        } else {
            if (operationAmount > account.getAmount()) {
                return "Withdrawal amount can't be more than the account amount: " + account.getAmount() + " "
                        + account.getCurrency() + " " + "!";
            }
            account.setAmount(account.getAmount() - operationAmount);
        }

        return "";
    }
}
