<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Borrar Idioma</title>
</head>
<body>
<center><h1>AL BORRAR UN IDIOMA SERAN ELIMINADOS TODOS LOS PAISES QUE TENGAN EL MISMO IDIOMA</h1>
<form method="post" action="Delete">
		<input type="hidden" value="${language}" name="language">
		<br>
		<input type="submit"  value="SI, estoy seguro">
		<input type="button" onclick="window.location.href='index.jsp'"  value="NO, quiero eliminarlo">
	</form>	
</center>
</body>
</html>