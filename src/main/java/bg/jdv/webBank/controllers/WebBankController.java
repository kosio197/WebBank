package bg.jdv.webBank.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bg.jdv.webBank.entities.BankAccount;
import bg.jdv.webBank.entities.BankUser;
import bg.jdv.webBank.modelImpl.BankModel;
import bg.jdv.webBank.service.WebBankConvert;
import bg.jdv.webBank.service.WebBankDataStorage;
import bg.jdv.webBank.service.WebBankProcess;

@Controller
public class WebBankController {

    @Autowired
    private WebBankDataStorage dataStorage;
    @Autowired
    private WebBankProcess process;
    @Autowired
    private WebBankConvert convert;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String r(Model model, HttpServletRequest request) {

        if (request.getParameter("error") != null) {
            model.addAttribute("info", "Invalid credentials!");
        }
        return "login";
    }

    // @Secured({ "ROLE_USER" })
    @RequestMapping(value = "/register")
    public String register(Model model, HttpServletRequest request) {

        BankUser user = (BankUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isEmployee = hasAutority(user.getAuthorities(), "ROLE_BANK_EMPLOYEE");

        model.addAttribute("info", request.getParameter("info"));
        if (isEmployee) {
            model.addAttribute("accountsList", dataStorage.getAllAccounts());
        } else {
            model.addAttribute("accountsList", dataStorage.getUserAccounts(user.getUsername()));
        }

        return "register";
    }

    @RequestMapping(value = "/webBankOperation", method = RequestMethod.GET)
    public String bankProcess(Model model) {
        return "bankOperation";
    }

    @RequestMapping(value = "/webBankOperation", method = RequestMethod.POST)
    public String bankProcess(Model model, @ModelAttribute("BankModel") BankModel bankModel,
            @ModelAttribute("BankUser") BankUser bankUser) throws IOException {
        String info = "";
        BankAccount account = dataStorage.getAccount(bankModel.getAccountNumber());
        BankUser currentLoggedUser = (BankUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (account == null) {
            info = "Account doesn't exist!";
        } else {
            BankUser user = account.getUser();
            if (!hasAutority(currentLoggedUser.getAuthorities(), "ROLE_BANK_EMPLOYEE")
                    && !currentLoggedUser.getUsername().equals(user.getUsername())) {
                info = "Account doesn't exist!";
            } else {

                String accountCurrency = account.getCurrency();
                double currentAmount = convert.currencyConverter(accountCurrency, bankModel.getCurrency(),
                        bankModel.getAmount());
                info = process.accountProcess(account, currentAmount, bankModel.getOperation());
            }
        }

        model.addAttribute("info", info);
        return "redirect:register";
    }

    @RequestMapping(value = "/createNewAccount", method = RequestMethod.GET)
    public String createAccount(Model model) {
        return "createAccount";
    }

    @RequestMapping(value = "/createNewAccount", method = RequestMethod.POST)
    public String createAccount(Model model, @ModelAttribute("BankModel") BankModel bankModel,
            @ModelAttribute("BankUser") BankUser bankUser) throws IOException {
        String info = "";
        String accountNumber = bankModel.getAccountNumber();

        if (dataStorage.getAccount(accountNumber) == null) {
            String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
            BankUser user = dataStorage.getUser(bankModel.getUsername());
            if (user == null) {
                info = "Username doesn't exist!";
            } else {
                Double amount = bankModel.getAmount();
                if (amount != null && amount < 0) {
                    info = "The amount should be a non-negative number!";
                } else {
                    dataStorage.addAccount(accountNumber, createdBy, bankModel.getCurrency(), amount, user);
                }
            }
        } else {
            info = "Account number already exist";
        }

        if (info.equals("")) {
            return "redirect:register";
        }

        model.addAttribute("info", info);
        return "createAccount";
    }

    private boolean hasAutority(Collection<? extends GrantedAuthority> authorities, String str) {
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(str))
                return true;
        }
        return false;
    }
}
