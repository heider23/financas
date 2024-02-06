<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema Financas Pessoal :: Logout</title>
</head>
<body>
	<table align="center">
		<tr>
			<td align="center" colspan="2">
				<h1><b>Logout efetuado no sistema.</b></h1>
				<br>
				<br>
				<br>
				<input name="login" id="login" value="Efetuar Login" type="button" onclick="login();">
				<br>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	function login() {
		location.href='/financas';
	}
</script>
</html>