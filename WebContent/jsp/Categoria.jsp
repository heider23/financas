<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  

<jsp:useBean id="Categoria" scope="request" class="br.com.financas.controle.Categoria"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" language="JavaScript" src="../js/util.js"></script>
	<script type="text/javascript" language="JavaScript" src="../js/Categoria.js"></script>
	<link href="../css/financas.css" rel="stylesheet" type="text/css"> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema Financas Pessoal :: Categoria</title>
</head>
<body onload="atualizar('<%=Categoria.getAcao()%>','<%=Categoria.getMsg()%>')">
	<table width="100%" style="background-color: orange;">
		<tr>
			<td>
				<h1><font color="brown">Financas Pessoal</font></h1>
			</td>
		</tr>
	</table>
	<form name="frmCategoria" id="frmCategoria" method="post">
		<input name="acao" id="acao" type="hidden" value="<%=Categoria.getAcao()%>">
		<input name="msg" id="msg" type="hidden" value="<%=Categoria.getMsg()%>">
		<input name="idCategoria" id="idCategoria" type="hidden" value="<%=Categoria.getIdCategoria()%>">
		<table align="center">
			<tr>
				<td><h1>Categoria</h1></td>
			</tr>
			<tr>
				<td>Nome:</td>
				<td>
					<input name="nomeCategoria" id="nomeCategoria" type="text" size="20" onkeypress="return formatar(this,'A',event);" value="<%=Categoria.getNomeCategoria() %>">
				</td>
				<td>
					<input name="browserNomeConta" id="browserNomeConta" type="button" value="..." onclick="browseCategoria();" style="width: 1cm">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input name="CON" id="CON" value="Consultar" type="button" style="width: 2cm" onclick="consultar()" <%=Categoria.isPodeCON()?"":"disabled"%>>
					<input name="ALT" id="ALT" value="Alterar"   type="button" style="width: 2cm" onclick="alterar()" <%=Categoria.isPodeALT()?"":"disabled"%>>
					<input name="INC" id="INC" value="Incluir"   type="button" style="width: 2cm" onclick="incluir()" <%=Categoria.isPodeINC()?"":"disabled"%>>
					<input name="EXC" id="EXC" value="Excluir"   type="button" style="width: 2cm" onclick="excluir()" <%=Categoria.isPodeEXC()?"":"disabled"%>>
				</td>
			</tr>	
			<tr>
				<td colspan="2" align="center">
					<input name="VOL" id="VOL" value="Voltar" type="button" style="width: 2cm" onclick="voltar()">
					<input name="CAN" id="CAN" value="Cancelar" type="button" style="width: 2cm" onclick="cancelar()">
				</td>
			</tr>				
		</table>
	</form>
</body>
</html>