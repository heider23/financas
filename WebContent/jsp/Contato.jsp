<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="Contato" scope="request" class="br.com.financas.controle.Contato"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" language="JavaScript" src="../js/util.js"></script>
	<script type="text/javascript" language="JavaScript" src="../js/Contato.js"></script>
	<link href="../css/financas.css" rel="stylesheet" type="text/css"> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema Financas Pessoal :: Contato</title>
</head>
<body onload="atualizar('<%=Contato.getAcao()%>','<%=Contato.getMsg()%>')">
	<table width="100%" style="background-color: orange;">
		<tr>
			<td>
				<h1><font color="brown">Financas Pessoal</font></h1>
			</td>
		</tr>
	</table>
	<form name="frmContato" id="frmContato" method="post">
		<input name="acao" id="acao" type="hidden" value="<%=Contato.getAcao()%>">
		<input name="msg" id="msg" type="hidden" value="<%=Contato.getMsg()%>">
		<input name="idContato" id="idContato" type="hidden" value="<%=Contato.getIdContato()%>">
		<table align="center">
			<tr>
				<td><h1>Contato</h1></td>
			</tr>
			<tr>
				<td>Nome:</td>
				<td>
					<input name="nome" id="nome" type="text" size="40" onkeypress="return formatar(this,'A',event);" value="<%=Contato.getNome() %>">
				</td>
				<td>
					<input name="browserNome" id="browserNome" type="button" value="..." onclick="browseContato();" style="width: 1cm">
				</td>
			</tr>
			<tr>
				<td>Data Nascimento:</td>
				<td colspan="2">
					<input name="dataNascimento" id="dataNascimento" type="text" onkeypress="return formatar(this,'99/99/9999',event);" value="<%=Contato.getDataNascimento() %>">
				</td>
			</tr>
			<tr>
				<td>Sexo:</td>
				<td colspan="2">
					<select name="sexo" id="sexo">
						<option value="" <%=Contato.getSexo().equals("")? "selected":"" %> >Selecione...</option>
						<option value="M" <%=Contato.getSexo().equals("M")? "selected":"" %> >Masculino</option>
						<option value="F" <%=Contato.getSexo().equals("F")? "selected":"" %> >Feminino</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Endereço:</td>
				<td colspan="2">
					<input name="endereco" id="endereco" type="text" size="30" onkeypress="return formatar(this,'X',event);" value="<%=Contato.getEndereco() %>">
				</td>
			</tr>
			<tr>
				<td>Complemento:</td>
				<td colspan="2">
					<input name="complemento" id="complemento" type="text" size="20" onkeypress="return formatar(this,'X',event);" value="<%=Contato.getComplemento() %>">
				</td>
			</tr>
			<tr>
				<td>Bairro:</td>
				<td colspan="2">
					<input name="bairro" id="bairro" type="text" size="30" onkeypress="return formatar(this,'X',event);" value="<%=Contato.getBairro() %>">
				</td>
			</tr>
			<tr>
				<td>Cep:</td>
				<td colspan="2">
					<input name="cep" id="cep" type="text" onkeypress="return formatar(this,'99999-999',event);" value="<%=Contato.getCep() %>">
				</td>
			</tr>
			<tr>
				<td>Telefone fixo:</td>
				<td colspan="2">
					<input name="telefoneFixo" id="telefoneFixo" type="text" onkeypress="return formatar(this,'(99)9999-9999',event);" value="<%=Contato.getTelefoneFixo() %>">
				</td>
			</tr>
			<tr>
				<td>Telefone celular:</td>
				<td colspan="2">
					<input name="telefoneCelular" id="telefoneCelular" type="text" onkeypress="return formatar(this,'(99)9999-9999',event);" value="<%=Contato.getTelefoneCelular() %>">
				</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td colspan="2">
					<input name="email" id="email" type="text" size="20" onkeypress="return formatar(this,'X',event);" value="<%=Contato.getEmail() %>">
				</td>
			</tr>		
			<tr>
				<td colspan="2" align="center">
					<input name="CON" id="CON" value="Consultar" type="button" style="width: 2cm" onclick="consultar()" <%=Contato.isPodeCON()?"":"disabled"%>>
					<input name="ALT" id="ALT" value="Alterar"   type="button" style="width: 2cm" onclick="alterar()" <%=Contato.isPodeALT()?"":"disabled"%>>
					<input name="INC" id="INC" value="Incluir"   type="button" style="width: 2cm" onclick="incluir()" <%=Contato.isPodeINC()?"":"disabled"%>>
					<input name="EXC" id="EXC" value="Excluir"   type="button" style="width: 2cm" onclick="excluir()" <%=Contato.isPodeEXC()?"":"disabled"%>>
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