package bg.jdv.webBank.service;

public interface WebBankConvert {

		double currencyConverter(String accountCurrency, String operationCurrency, double operationAmount);
}
