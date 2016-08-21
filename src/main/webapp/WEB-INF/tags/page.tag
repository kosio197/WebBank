<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>

<%@ attribute name="title" %>

<c:set var="project_name" value="Topic 7 - web banking"/>
<c:set var="version"  value="1.0.1"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title}</title>
	</head>
	<body>
	    <h2><font color="red">${info}</font></h2>
		<ct:header title="${title}"/>
		
		<p> <jsp:doBody /></p>
		
		<ct:footer project_name="${project_name}" project_version="${version}"/>
	</body>
</html>