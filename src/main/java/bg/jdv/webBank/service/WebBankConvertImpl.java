package bg.jdv.webBank.service;

import org.springframework.stereotype.Service;

@Service
public class WebBankConvertImpl implements WebBankConvert{

	@Override
	public double currencyConverter(String accountCurrency, String operationCurrency, double operationAmount) {
		
		char uc= accountCurrency.charAt(0);
		char oc =operationCurrency.charAt(0);
		
		switch (uc){
	
		case 'B':
			switch (oc) {
						case 'B':
							return operationAmount;
													
						case 'U':
							return operationAmount * 1.7;
							
						case 'E':
							return operationAmount * 1.95;
							
						default:
							break;
						}
		
		case 'U' :
			switch (oc) {
						case 'B':
							return operationAmount / 1.7;
													
						case 'U':
							return operationAmount;
							
						case 'E':
							return operationAmount * 1.25;
							
						default:
							break;
						}
			
		case 'E' :
			switch (oc) {
						case 'B':
							return operationAmount / 1.95;
													
						case 'U':
							return operationAmount / 1.25;
							
						case 'E':
							return operationAmount;
							
						default:
							break;
						}
			break;

		default:
			break;
		}
		
		return 0;
	}

}
