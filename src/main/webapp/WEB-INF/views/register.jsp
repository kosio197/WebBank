<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<ct:page title="Bank Register">	
	<jsp:body>
		<form   action="/logout">
			<fieldset>
				<input type="submit" value="Logout"><br /><br />	
				<table border="1">
					<thead>
						<tr>
							<th>Username</th>
							<th>Account Number</th>
							<th>Current Amount</th>
							<th>Account Currency</th>
							<th>Created By</th>
						</tr>
					</thead>
					<c:if test="${not empty accountsList}">
				    	<tbody>
					        <c:forEach var="s" items="${accountsList}">
					            <tr>
					                <td>${s.user.username}</td>
					                <td>${s.accountNumber}</td>
					                <td>${s.amount}</td>
					                <td>${s.currency}</td>
					                <td>${s.createdBy}</td>
					            </tr>
					        </c:forEach>
				        </tbody>
					</c:if>	
				</table>
			</fieldset>
		</form>	
		<br /><br />
		<sec:authorize  access="hasRole ('BANK_EMPLOYEE')">
			<a href="/createNewAccount"><button>New Account</button></a>
		</sec:authorize>
	    <br /> <br />
		<sec:authorize  access="authenticated">	
		   <c:choose>
			    <c:when test="${empty accountsList}">
		            <a href="/webBankOperation"><button disabled="disabled">Operation</button></a>
		        </c:when>
		        <c:otherwise>
		           <a href="/webBankOperation"><button>Operation</button></a>
		        </c:otherwise>
	        </c:choose>
		</sec:authorize>	
	</jsp:body>
</ct:page>
