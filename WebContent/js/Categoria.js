/**
 * Variáveis globais
 **/
var	botaoCON;
var	botaoALT;
var	botaoINC;
var	botaoEXC;

function atualizar(pAcao,pMsg) {
	try {

		botaoCON = document.frmCategoria.CON.disabled == false;
		botaoALT = document.frmCategoria.ALT.disabled == false;
		botaoINC = document.frmCategoria.INC.disabled == false;
		botaoEXC = document.frmCategoria.EXC.disabled == false;
		
		/*
		 * Estas regras para habilitar/desabilitar os botoes
		 */
		if (pMsg == '' && pAcao == 'consultar') {
			podeCON(false);
			podeALT(true);
			podeINC(false);
			podeEXC(true);
		} else if (pAcao == 'alterar') {
			podeCON(true);
			podeALT(false);
			podeINC(true);
			podeEXC(false);	
			limparCampos();	
		} else if (pAcao == 'incluir') {
			podeCON(true);
			podeALT(false);
			podeINC(true);
			podeEXC(false);		
			limparCampos();
		} else if (pAcao == 'excluir') {
			podeCON(true);
			podeALT(false);
			podeINC(false);
			podeEXC(false);
			limparCampos();		
		} else {
			podeCON(true);
			podeALT(false);
			podeINC(true);
			podeEXC(false);		
		}	
		if(pMsg != '') {
			alert(pMsg);
		}	
	} catch(e) {
		alert(e + ' - ' + e.message);
	}
} 

function podeCON(pCON) {
	 if (botaoCON && pCON) {
	 	document.frmCategoria.CON.disabled = '';
	 	return true;
	 } else {
	 	document.frmCategoria.CON.disabled = 'disabled';
	 	return false;
	 }
}

function podeALT(pALT) {
	 if (botaoALT && pALT) {
	 	document.frmCategoria.ALT.disabled = '';
	 	return true;
	 } else {
	 	document.frmCategoria.ALT.disabled = 'disabled';
	 	return false;
	 }
}

function podeINC(pINC) {
	 if (botaoINC && pINC) {
	 	document.frmCategoria.INC.disabled = '';
	 	return true;
	 } else {
	 	document.frmCategoria.INC.disabled = 'disabled';
	 	return false;
	 }
}

function podeEXC(pEXC) {
	 if (botaoEXC && pEXC) {
	 	document.frmCategoria.EXC.disabled = '';
	 	return true;
	 } else {
	 	document.frmCategoria.EXC.disabled = 'disabled';
	 	return false;
	 }
}

function browseCategoria() {
	var width  = 300;
	var height = 200;
	var left   = (screen.width  - width)/2;
	var top    = (screen.height - height)/2;
	var params = 'width='+width+', height='+height;
	params += ', top='+top+', left='+left;
	params += ', directories=no';
	params += ', location=no';
	params += ', menubar=no';
	params += ', resizable=no';
	params += ', scrollbars=no';
	params += ', status=no';
	params += ', toolbar=no';
	janela=window.open("/financas/jsp/ListaCategoria.jsp","browseCategoria", params);
	if (window.focus) {
		janela.focus();
	}
	return false;
}

function consultar() {
	if (document.frmCategoria.nomeCategoria.value != '') {
		document.frmCategoria.acao.value = 'consultar';
		document.frmCategoria.submit();
	} else {
		alert("Informe o nome do Categoria que deseja consultar.");
		document.frmCategoria.nomeCategoria.focus();
		return;
	}
}
function alterar() {
	if(confirm("Deseja realmente alterar os dados deste Categoria?")) {
		document.frmCategoria.acao.value = 'alterar';
		document.frmCategoria.submit();
	}
}
function incluir() {
	document.frmCategoria.acao.value = 'incluir';
	document.frmCategoria.submit();
}
function excluir() {
	if (confirm("Deseja realmente excluir esta Categoria?")) {
		document.frmCategoria.acao.value = 'excluir';
		document.frmCategoria.submit();
	}
}
function cancelar() {
	window.location.href="/financas/jsp/Categoria.jsp";
}
function voltar() {
	window.location.href="/financas/jsp/Portal.jsp";
}
function limparCampos() {
	cancelar();
}
