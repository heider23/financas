/**
 * Variáveis globais
 **/
var	botaoCON;
var	botaoALT;
var	botaoINC;
var	botaoEXC;

function atualizar(pAcao,pMsg) {
	try {
		botaoCON = document.frmContato.CON.disabled == false;
		botaoALT = document.frmContato.ALT.disabled == false;
		botaoINC = document.frmContato.INC.disabled == false;
		botaoEXC = document.frmContato.EXC.disabled == false;
		
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
	 	document.frmContato.CON.disabled = '';
	 	return true;
	 } else {
	 	document.frmContato.CON.disabled = 'disabled';
	 	return false;
	 }
}

function podeALT(pALT) {
	 if (botaoALT && pALT) {
	 	document.frmContato.ALT.disabled = '';
	 	return true;
	 } else {
	 	document.frmContato.ALT.disabled = 'disabled';
	 	return false;
	 }
}

function podeINC(pINC) {
	 if (botaoINC && pINC) {
	 	document.frmContato.INC.disabled = '';
	 	return true;
	 } else {
	 	document.frmContato.INC.disabled = 'disabled';
	 	return false;
	 }
}

function podeEXC(pEXC) {
	 if (botaoEXC && pEXC) {
	 	document.frmContato.EXC.disabled = '';
	 	return true;
	 } else {
	 	document.frmContato.EXC.disabled = 'disabled';
	 	return false;
	 }
}

function browseContato() {
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
	janela=window.open("/financas/jsp/ListaContato.jsp","browseContato", params);
	if (window.focus) {
		janela.focus();
	}
	return false;
}

function consultar() {
	if (document.frmContato.nome.value != '') {
		document.frmContato.acao.value = 'consultar';
		document.frmContato.submit();
	} else {
		alert("Informe o nome do Contato que deseja consultar.");
		document.frmContato.nome.focus();
		return;
	}
}
function alterar() {
	if(confirm("Deseja realmente alterar os dados deste Contato?")) {
		document.frmContato.acao.value = 'alterar';
		document.frmContato.submit();
	}
}
function incluir() {
	document.frmContato.acao.value = 'incluir';
	document.frmContato.submit();
}
function excluir() {
	if (confirm("Deseja realmente excluir este Contato?")) {
		document.frmContato.acao.value = 'excluir';
		document.frmContato.submit();
	}
}
function cancelar() {
	window.location.href="/financas/jsp/Contato.jsp";
}
function voltar() {
	window.location.href="/financas/jsp/Portal.jsp";
}
function limparCampos() {
	cancelar();
}
