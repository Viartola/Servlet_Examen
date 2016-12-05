<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.*,java.util.*,servlet.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mostrar Paises</title>
<style>
	a{
		text-decoration:none;
		color:red;
	}
</style>
</head>
<body>

<form method="post" action="List">	
		<input type="submit"  value="Mostrar">
		<input type="button" onclick="window.location.href='index.jsp'"  value="Volver">
</form>	
<br>
<br>
<table>
		<thead>
			<tr>
				<td>Pais</td>
				<td></td>
				<td>Idioma</td>
				<td></td>
				<td align = "center">Borrar</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="country" items="${listAllCountries}">
				<tr>
					<td><c:out value="${country.country}"/> </td>
					<td></td>
					<td><c:out value="${country.language}"/> </td>
					<td></td>
					<td><a name="user" href="Confirmation?language=${country.language}">Borrar Idioma</a></td>
		    	</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>