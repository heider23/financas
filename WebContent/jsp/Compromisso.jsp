<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<jsp:useBean id="Compromisso" scope="request" class="br.com.financas.controle.Compromisso"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" language="JavaScript" src="../js/util.js"></script>
	<script type="text/javascript" language="JavaScript" src="../js/Compromisso.js"></script>
	<link href="../css/financas.css" rel="stylesheet" type="text/css"> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema Financas Pessoal :: Compromisso</title>
</head>
<body onload="atualizar('<%=Compromisso.getAcao()%>','<%=Compromisso.getMsg()%>')">
	<table width="100%" style="background-color: orange;">
		<tr>
			<td>
				<h1><font color="brown">Financas Pessoal</font></h1>
			</td>
		</tr>
	</table>
	<form name="frmCompromisso" id="frmCompromisso" method="post">
		<input name="acao" id="acao" type="hidden" value="<%=Compromisso.getAcao()%>">
		<input name="msg" id="msg" type="hidden" value="<%=Compromisso.getMsg()%>">
		<input name="idCompromisso" id="idCompromisso" type="hidden" value="<%=Compromisso.getIdCompromisso()%>">
		<input name="idContato" id="idContato" type="hidden" value="<%=Compromisso.getIdContato()%>">
		<table align="center">
			<tr>
				<td><h1>Compromisso</h1></td>
			</tr>
			<tr>
				<td>Assunto:</td>
				<td>
					<input name="assunto" id="assunto" type="text" size="20" onkeypress="return formatar(this,'A',event);" value="<%=Compromisso.getAssunto() %>">
				</td>
			</tr>
			<tr>
				<td>Observacoes:</td>
				<td colspan="2">
					<input name="observacoes" id="observacoes" type="text" size="30" onkeypress="return formatar(this,'X',event);" value="<%=Compromisso.getObservacoes() %>">
				</td>
			</tr>
			<tr>
				<td>Periodo:</td>
				<td colspan="2">
					<input name="dataHoraInicio" id="dataHoraInicio" type="text" onkeypress="return formatar(this,'99/99/9999 99:99',event);" value="<%=Compromisso.getDataHoraInicio() %>">
					até
					<input name="dataHoraFinal" id="dataHoraFinal" type="text" onkeypress="return formatar(this,'99/99/9999 99:99',event);" value="<%=Compromisso.getDataHoraFinal() %>">
				</td>
			</tr>
			<tr>
				<td>Contato:</td>
				<td colspan="2">
					<input name="nome" id="nome" type="text" size="30" onkeypress="return formatar(this,'A',event);" value="<%=Compromisso.getNome() %>">
				</td>
				<td>
					<input name="browserNome" id="browserNome" type="button" value="..." onclick="browseContato();" style="width: 1cm">
				</td>
			</tr>	
			<tr>
				<td colspan="2" align="center">
					<input name="CON" id="CON" value="Consultar" type="button" style="width: 2cm" onclick="consultar()" <%=Compromisso.isPodeCON()?"":"disabled"%>>
					<input name="ALT" id="ALT" value="Alterar"   type="button" style="width: 2cm" onclick="alterar()" <%=Compromisso.isPodeALT()?"":"disabled"%>>
					<input name="INC" id="INC" value="Incluir"   type="button" style="width: 2cm" onclick="incluir()" <%=Compromisso.isPodeINC()?"":"disabled"%>>
					<input name="EXC" id="EXC" value="Excluir"   type="button" style="width: 2cm" onclick="excluir()" <%=Compromisso.isPodeEXC()?"":"disabled"%>>
				</td>
			</tr>	
			<tr>
				<td colspan="2" align="center">
					<input name="VOL" id="VOL" value="Voltar" type="button" style="width: 2cm" onclick="voltar()">
					<input name="CAN" id="CAN" value="Cancelar" type="button" style="width: 2cm" onclick="cancelar()">
				</td>
			</tr>				
		</table>
		<c:if test="${Compromisso.lstECompromisso ne null }">
			<br>
			<table align="center" width="100%" border="1">
					<tr style="background-color: orange;">
						<td width="25%" colspan="2">Assunto</td>
						<td width="30%">Observações</td>
						<td width="20%" colspan="2" align="center">Período</td>
						<td width="25%" colspan="2">Contato</td>
					</tr>
					<c:forEach items="${Compromisso.lstECompromisso }" var="item">	
						<tr onclick="selecionaCompromisso(this);">
							<td>${item.idCompromisso }</td>
							<td>${item.assunto }</td>	
							<td>${item.observacoes }</td>
							<td align="center">${item.dataHoraInicioFormatada }</td>
							<td align="center">${item.dataHoraFinalFormatada }</td>
							<td>${item.EContato.idContato }</td>
							<td>${item.EContato.nome }</td>
						</tr>
					</c:forEach>	
			</table>	
		</c:if>
	</form>
</body>
</html>