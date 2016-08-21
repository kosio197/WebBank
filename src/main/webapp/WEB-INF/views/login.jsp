<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<ct:page title="web Bank Login">	
	<jsp:body>
		<form:form action="/login" method="post" modelAttribute="BankModel">
		 	<fieldset>
				<p><b>username</b> <input name="username" type="text"></p>
				<p><b>password</b> <input name="password" type="password"></p>
				<input type="submit" value="Login">
			</fieldset>
		</form:form>	<br /><br /><br />
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
