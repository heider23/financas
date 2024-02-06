<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="Movimento" scope="request" class="br.com.financas.controle.Movimento"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" language="JavaScript" src="../js/util.js"></script>
	<script type="text/javascript" language="JavaScript" src="../js/Movimento.js"></script>
	<link href="../css/financas.css" rel="stylesheet" type="text/css"> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema Financas Pessoal :: Movimento</title>
</head>
<body onload="atualizar('<%=Movimento.getAcao()%>','<%=Movimento.getMsg()%>')">
	<table width="100%" style="background-color: orange;">
		<tr>
			<td>
				<h1><font color="brown">Financas Pessoal</font></h1>
			</td>
		</tr>
	</table>
	<form name="frmMovimento" id="frmMovimento" method="post">
		<input name="acao" id="acao" type="hidden" value="<%=Movimento.getAcao()%>">
		<input name="msg" id="msg" type="hidden" value="<%=Movimento.getMsg()%>">
		<input name="idUsuario" id="idUsuario" type="hidden" value="<%=Movimento.getIdUsuario()%>">
		<input name="idMovimento" id="idMovimento" type="hidden" value="<%=Movimento.getIdMovimento()%>">
		<table align="center">
			<tr>
				<td><h1>Movimento</h1></td>
			</tr>
			<tr>
				<td>Conta:</td>
				<td colspan="2">				
					<select name="idConta" id="idConta">
						<option value="0">Selecione...</option>
						<c:forEach items="${Movimento.lstEConta }" var="item">	
							<option value="${item.idConta }">${item.nome }</option>
						</c:forEach>	
					</select>	
					<script type="text/javascript">
						setarSelect('idConta','${Movimento.idConta }');
					</script>				
				</td>
			</tr>
			<tr>
				<td>Categoria:</td>
				<td colspan="2">
					<select name="idCategoria" id="idCategoria">
						<option value="0">Selecione...</option>
						<c:forEach items="${Movimento.lstECategoria }" var="item">
							<option value="${item.idCategoria }">${item.nomeCategoria }</option>
						</c:forEach>	
					</select>
					<script type="text/javascript">
						setarSelect('idCategoria','${Movimento.idCategoria }');
					</script>	
				</td>
			</tr>
			<tr>
				<td>Data:</td>
				<td colspan="2">
					<input name="dataMovimento" id="dataMovimento" type="text" onkeypress="return formatar(this,'99/99/9999',event);" value="<%=Movimento.getDataMovimento() %>">
				</td>
			</tr>
			<tr>
				<td>Histórico:</td>
				<td colspan="2">
					<input name="historicoMovimento" id="historicoMovimento" type="text" size="30" onkeypress="return formatar(this,'X',event);" value="<%=Movimento.getHistoricoMovimento() %>">
				</td>
			</tr>
			<tr>
				<td>Valor:</td>
				<td colspan="2">
					<input name="valorMovimento" id="valorMovimento" type="text" size="15" onkeypress="return formatarNumero(this,'999.999.999,99',event);" value="<%=Movimento.getValorMovimento() %>">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input name="CON" id="CON" value="Consultar" type="button" style="width: 2cm" onclick="consultar()" <%=Movimento.isPodeCON()?"":"disabled"%>>
					<input name="ALT" id="ALT" value="Alterar"   type="button" style="width: 2cm" onclick="alterar()" <%=Movimento.isPodeALT()?"":"disabled"%>>
					<input name="INC" id="INC" value="Incluir"   type="button" style="width: 2cm" onclick="incluir()" <%=Movimento.isPodeINC()?"":"disabled"%>>
					<input name="EXC" id="EXC" value="Excluir"   type="button" style="width: 2cm" onclick="excluir()" <%=Movimento.isPodeEXC()?"":"disabled"%>>
				</td>
			</tr>	
			<tr>
				<td colspan="2" align="center">
					<input name="VOL" id="VOL" value="Voltar" type="button" style="width: 2cm" onclick="voltar()">
					<input name="CAN" id="CAN" value="Cancelar" type="button" style="width: 2cm" onclick="cancelar()">
				</td>
			</tr>				
		</table>
		<c:if test="${Movimento.lstEMovimento ne null }">
			<br>
			<table align="center" width="70%" border="1">
					<tr style="background-color: orange;">
						<td align="center" width="15%">Data</td>
						<td width="15%">Categoria</td>
						<td width="45%">Historico</td>
						<td width="25%">Valor</td>
					</tr>
					<c:forEach items="${Movimento.lstEMovimento }" var="item">	
						<tr onclick="selecionaMovimento(this, ${item.id }, ${item.EConta.idConta }, ${item.ECategoria.idCategoria });">
							<td align="center">${item.dataFormatada }</td>
							<td align="left">${item.ECategoria.nomeCategoria }</td>	
							<td align="left">${item.historico }</td>
							<td align="right">${item.valorFormatado }</td>
						</tr>
					</c:forEach>	
			</table>	
		</c:if>
	</form>
</body>
</html>