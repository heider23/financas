<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="ListaContato" scope="request" class="br.com.financas.controle.ListaContato"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" language="JavaScript" src="../js/ListaContato.js"></script>
	<link href="../css/financas.css" rel="stylesheet" type="text/css"> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema Financas Pessoal :: Lista Contatos</title>
</head>
<body>
	<center>
		<h3>Contato</h3>
	</center>
	<hr>
	<form name="frmListaContato" id="frmListaContato" method="post">	
		<c:forEach items="${ListaContato.listaContato }" var="item">		
			<a href="#" name="nome" id="nome" onclick="seleciona('${item.idContato }','${item.nome }');">${item.nome }</a><br>
		</c:forEach>
	</form>
	<hr>
</body>
</html>