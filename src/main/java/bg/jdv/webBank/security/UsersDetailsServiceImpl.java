package bg.jdv.webBank.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import bg.jdv.webBank.entities.BankUser;
import bg.jdv.webBank.service.WebBankDataStorage;


public class UsersDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private WebBankDataStorage dataStorage; 

//
//	@Override
//	protected void additionalAuthenticationChecks(UserDetails userDetails,
//			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {		
//	}
//
//	@Override
//	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
//			throws AuthenticationException {
//		BankUser user=dataStorage.getUser(username);
//		
//		if( user== null){
//			throw new AuthenticationServiceException("Invalid Credentials");
//		}
//
//		return user;
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		BankUser user=dataStorage.getUser(username);
		
		if( user== null){
			throw new AuthenticationServiceException("Invalid Credentials");
		}

		return user;
	}
}
