package bg.jdv.webBank.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import bg.jdv.webBank.entities.BankAccount;
import bg.jdv.webBank.entities.BankUser;

@Component
public class WebBankDataStorageImpl implements WebBankDataStorage {
    // username
    private static HashMap<String, BankUser> usersHash = new HashMap<String, BankUser>();
    // username
    private static HashMap<String, List<BankAccount>> userAccountsHash = new HashMap<String, List<BankAccount>>();
    // accountNumber
    private static HashMap<String, BankAccount> accountsHash = new HashMap<String, BankAccount>();

    static {
        initialUser();
    }

    public WebBankDataStorageImpl() {
        System.out.println();
    }

    @Override
    public BankUser getUser(String username) {

        return usersHash.get(username);
    }

    @Override
    public BankUser addUser(String username, BankUser user) {

        usersHash.put(username, user);
        userAccountsHash.put(username, new ArrayList<BankAccount>());
        return user;
    }

    @Override
    public boolean removeUser(String username) {

        List<BankAccount> rem = userAccountsHash.get(username);

        for (BankAccount bankAccount : rem) {
            accountsHash.remove(bankAccount.getAccountNumber());
        }
        usersHash.remove(username);
        return userAccountsHash.remove(username) == null ? false : true;

    }

    @Override
    public BankAccount getAccount(String accountNumber) {

        return accountsHash.get(accountNumber);
    }

    @Override
    public BankAccount addAccount(String accountNumber, String createdBy, String currency, double amount,
            BankUser user) {
        BankAccount next = new BankAccount();

        next.setAccountNumber(accountNumber);
        next.setUser(user);
        next.setAmount(amount);
        next.setCreatedBy(createdBy);
        next.setCurrency(currency);

        userAccountsHash.get(user.getUsername()).add(next);
        accountsHash.put(accountNumber, next);

        return next;
    }

    @Override
    public boolean removeAccount(String accountNumber) {

        BankAccount rem = accountsHash.remove(accountNumber);
        if (rem == null)
            return false;
        userAccountsHash.get(rem.getUser().getUsername()).remove(rem);
        return true;
    }

    private static void initialUser() {
        WebBankDataStorageImpl dts = new WebBankDataStorageImpl();

        ArrayList<GrantedAuthority> arrUser = new ArrayList<GrantedAuthority>();
        arrUser.add(new SimpleGrantedAuthority("ROLE_USER"));

        ArrayList<GrantedAuthority> arrBankEmployee = new ArrayList<GrantedAuthority>();
        arrBankEmployee.add(new SimpleGrantedAuthority("ROLE_BANK_EMPLOYEE"));
        // BANK_EMPLOYEE
        BankUser user1 = new BankUser();
        user1.setUsername("jimi");
        user1.setPassword("202cb962ac59075b964b07152d234b70");
        user1.setAuthorities(arrUser);
        dts.addUser("jimi", user1);

        BankUser user2 = new BankUser();
        user2.setUsername("bob");
        user2.setPassword("81dc9bdb52d04dc20036dbd8313ed055");
        user2.setAuthorities(arrUser);
        dts.addUser("bob", user2);

        BankUser user3 = new BankUser();
        user3.setUsername("kosio");
        user3.setPassword("827ccb0eea8a706c4c34a16891f84e7b");
        user3.setAuthorities(arrUser);
        dts.addUser("kosio", user3);

        BankUser user4 = new BankUser();
        user4.setUsername("joro");
        user4.setPassword("4a7d1ed414474e4033ac29ccb8653d9b");
        user4.setAuthorities(arrBankEmployee);
        dts.addUser("joro", user4);

    }

    @Override
    public List<BankAccount> getAllAccounts() {

        List<BankAccount> allAccount = new ArrayList<BankAccount>();
        for (Map.Entry<String, BankAccount> account : accountsHash.entrySet()) {
            allAccount.add(account.getValue());
        }

        return allAccount;
    }

    @Override
    public List<BankAccount> getUserAccounts(String username) {

        return Collections.unmodifiableList(userAccountsHash.get(username));
    }
}
