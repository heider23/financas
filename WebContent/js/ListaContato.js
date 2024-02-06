function seleciona(id_contato,nome_selecionado) {
	window.opener.document.getElementById('idContato').value = id_contato;
	window.opener.document.getElementById('nome').value = nome_selecionado;
	window.close();
}