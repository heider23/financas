<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:useBean id="Login" scope="request" class="br.com.financas.controle.Login"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sistema Financas Pessoal :: Login</title>
<link href="../css/financas.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="298" border="1" align="center" cellpadding="0"
	cellspacing="1" bordercolor="#000000">
	<tr>
		<td width="415">
		<form name="frmLogin" id="frmLogin" method="post">
		<table width="296" align="center">
			<tr>
				<td align="left" colspan="2">
				<h1><b>Login</b></h1>
				</td>
			</tr>
			<tr>
				<td width="95">Usuario:</td>
				<td width="202"><input name="usuario" type="text" id="usuario"
					size="35" value='<%=Login.getUsuario() %>'></td>
			</tr>
			<tr>
				<td>Senha:</td>
				<td><input name="senha" type="password" id="senha" size="35">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Entrar"> <input type="reset" value="Limpar">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center" class="tabela"><a href="#">N&atilde;o
				sou cadastrado</a></td>
			</tr>
			<tr>
				<td colspan="2" align="center" class="tabela"><a href="#">Esqueci
				minha senha</a></td>
			</tr>
			<tr>
				<td colspan="2">
					<font color="red"><%=Login.getMsg()%></font>
				</td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	document.forms["frmLogin"].elements["usuario"].focus();
</script>
</html>