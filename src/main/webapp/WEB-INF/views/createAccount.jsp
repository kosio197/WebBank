<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>


<ct:page title="Create Bank Account">	
	<jsp:body>			
		<form:form action="/createNewAccount" method="post" modelAttribute="BankModel">
			<fieldset>
					<p><b>Username</b> <input name="username" type="text"></p>						
					<p><b>Account Number</b> <input name="accountNumber" type="text"></p>
					<p><b>Initial Amount</b> <input name="amount" type="number" value=0></p>
			 		<b>Currency</b>		<select name="currency">
							  				<option value="BGN ">BGN </option>
							  				<option value="EURO">EURO</option>
							 				<option value="USD">USD</option>
									 	 </select> 
					<br /><br />
					<input type="submit" value="submit"><br />
			</fieldset>
		</form:form><br /><br />
		
		<form>
		  <fieldset>
			    <legend>Information for test program:</legend><br />
					Username="jimi" password="123" authorities="ROLE_USER"<br />			
					Username="bob" password="1234" authorities="ROLE_USER" <br />
					Username="kosio" password="12345" authorities="ROLE_USER "	<br />		
					Username="joro" password="0000" authorities="ROLE_BANK_EMPLOYEE"<br />
		   </fieldset>
		</form>
	</jsp:body>
</ct:page>
