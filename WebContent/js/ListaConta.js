function seleciona(id_conta,nome_selecionado) {
	window.opener.document.getElementById('idConta').value = id_conta;
	window.opener.document.getElementById('nomeConta').value = nome_selecionado;
	window.close();
}