<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="ListaCategoria" scope="request" class="br.com.financas.controle.ListaCategoria"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" language="JavaScript" src="../js/ListaCategoria.js"></script>
	<link href="../css/financas.css" rel="stylesheet" type="text/css"> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema Financas Pessoal :: Lista Categorias</title>
</head>
<body>
	<center>
		<h3>Categoria</h3>
	</center>
	<hr>
	<form name="frmListaCategoria" id="frmListaCategoria" method="post">	
		<c:forEach items="${ListaCategoria.listaCategoria }" var="item">		
			<a href="#" name="nomeCategoria" id="nomeCategoria" onclick="seleciona('${item.idCategoria }','${item.nomeCategoria }');">${item.nomeCategoria }</a><br>
		</c:forEach>
	</form>
	<hr>
</body>
</html>