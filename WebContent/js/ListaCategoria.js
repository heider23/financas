function seleciona(id_categoria,nome_selecionado) {
	window.opener.document.getElementById('idCategoria').value = id_categoria;
	window.opener.document.getElementById('nomeCategoria').value = nome_selecionado;
	window.close();
}