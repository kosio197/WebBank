<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>


<ct:page title="Bank Operation">
	<jsp:body>
		<form:form action="/webBankOperation" method="post" modelAttribute="BankModel">
			<fieldset>		
				<b>Account Number</b> <input name="accountNumber" type="text"><br /><br />			
				<b>Operation</b> 	<select name="operation">
						  				<option value="deposit">deposit </option>
						  			    <option value="withdraw">withdraw</option>
							  		 </select> <br /><br />
			
			     <b>Amount</b>	  <input name="amount" type="number" value=0><br /><br />
			
			     <b>Currency</b>	  <select name="currency">
							  			<option value="BGN ">BGN </option>
							  			<option value="EURO">EURO</option>
							 			<option value="USD">USD</option>
									  </select> 
				 <br /><br />			  
				 <input type="submit" value="submit">	
			</fieldset>
		</form:form>
		<br /><br /><br /><br />
	</jsp:body>
</ct:page>
