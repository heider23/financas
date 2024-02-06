<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="Conta" scope="request" class="br.com.financas.controle.Conta"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" language="JavaScript" src="../js/util.js"></script>
	<script type="text/javascript" language="JavaScript" src="../js/Conta.js"></script>
	<link href="../css/financas.css" rel="stylesheet" type="text/css"> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema Financas Pessoal :: Conta</title>
</head>
<body onload="atualizar('<%=Conta.getAcao()%>','<%=Conta.getMsg()%>')">
	<table width="100%" style="background-color: orange;">
		<tr>
			<td>
				<h1><font color="brown">Financas Pessoal</font></h1>
			</td>
		</tr>
	</table>
	<form name="frmConta" id="frmConta" method="post">
		<input name="acao" id="acao" type="hidden" value="<%=Conta.getAcao()%>">
		<input name="msg" id="msg" type="hidden" value="<%=Conta.getMsg()%>">
		<input name="idConta" id="idConta" type="hidden" value="<%=Conta.getIdConta()%>">
		<input name="idUsuario" id="idUsuario" type="hidden" value="<%=Conta.getIdUsuario()%>">
		<table align="center">
			<tr>
				<td><h1>Conta</h1></td>
			</tr>
			<tr>
				<td>Nome:</td>
				<td>
					<input name="nomeConta" id="nomeConta" type="text" size="20" onkeypress="return formatar(this,'A',event);" value="<%=Conta.getNomeConta() %>">
				</td>
				<td>
					<input name="browserNomeConta" id="browserNomeConta" type="button" value="..." onclick="browseConta();" style="width: 1cm">
				</td>
			</tr>
			<tr>
				<td>Descrição:</td>
				<td>
					<input name="descricao" id="descricao" type="text" size="30" onkeypress="return formatar(this,'A',event);" value="<%=Conta.getDescricao() %>">
				</td>
			</tr>
			<tr>
				<td>Data Criação:</td>
				<td>
					<input name="dataCriacao" id="dataCriacao" type="text" size="10" onkeypress="return formatar(this,'99/99/9999',event);" value="<%=Conta.getDataCriacao() %>">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input name="CON" id="CON" value="Consultar" type="button" style="width: 2cm" onclick="consultar()" <%=Conta.isPodeCON()?"":"disabled"%>>
					<input name="ALT" id="ALT" value="Alterar"   type="button" style="width: 2cm" onclick="alterar()" <%=Conta.isPodeALT()?"":"disabled"%>>
					<input name="INC" id="INC" value="Incluir"   type="button" style="width: 2cm" onclick="incluir()" <%=Conta.isPodeINC()?"":"disabled"%>>
					<input name="EXC" id="EXC" value="Excluir"   type="button" style="width: 2cm" onclick="excluir()" <%=Conta.isPodeEXC()?"":"disabled"%>>
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