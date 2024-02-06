<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="ListaConta" scope="request" class="br.com.financas.controle.ListaConta"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" language="JavaScript" src="../js/ListaConta.js"></script>
	<link href="../css/financas.css" rel="stylesheet" type="text/css"> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema Financas Pessoal :: Lista Contas</title>
</head>
<body>
	<center>
		<h3>Conta</h3>
	</center>
	<hr>
	<form name="frmListaConta" id="frmListaConta" method="post">	
		<c:forEach items="${ListaConta.listaConta }" var="item">		
			<a href="#" name="nomeConta" id="nomeConta" onclick="seleciona('${item.idConta }','${item.nome }');">${item.nome }</a><br>
		</c:forEach>
	</form>
	<hr>
</body>
</html>