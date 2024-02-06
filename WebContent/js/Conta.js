/**
 * Variáveis globais
 **/
var	botaoCON;
var	botaoALT;
var	botaoINC;
var	botaoEXC;

function atualizar(pAcao,pMsg) {
	try {
		botaoCON = document.frmConta.CON.disabled == false;
		botaoALT = document.frmConta.ALT.disabled == false;
		botaoINC = document.frmConta.INC.disabled == false;
		botaoEXC = document.frmConta.EXC.disabled == false;
		
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
	 	document.frmConta.CON.disabled = '';
	 	return true;
	 } else {
	 	document.frmConta.CON.disabled = 'disabled';
	 	return false;
	 }
}

function podeALT(pALT) {
	 if (botaoALT && pALT) {
	 	document.frmConta.ALT.disabled = '';
	 	return true;
	 } else {
	 	document.frmConta.ALT.disabled = 'disabled';
	 	return false;
	 }
}

function podeINC(pINC) {
	 if (botaoINC && pINC) {
	 	document.frmConta.INC.disabled = '';
	 	return true;
	 } else {
	 	document.frmConta.INC.disabled = 'disabled';
	 	return false;
	 }
}

function podeEXC(pEXC) {
	 if (botaoEXC && pEXC) {
	 	document.frmConta.EXC.disabled = '';
	 	return true;
	 } else {
	 	document.frmConta.EXC.disabled = 'disabled';
	 	return false;
	 }
}

function browseConta() {
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
	janela=window.open("/financas/jsp/ListaConta.jsp","browseConta", params);
	if (window.focus) {
		janela.focus();
	}
	return false;
}

function consultar() {
	if (document.frmConta.nomeConta.value != '') {
		document.frmConta.acao.value = 'consultar';
		document.frmConta.submit();
	} else {
		alert("Informe o nome do Contato que deseja consultar.");
		document.frmConta.nome.focus();
		return;
	}
}
function alterar() {
	if(confirm("Deseja realmente alterar os dados deste Contato?")) {
		document.frmConta.acao.value = 'alterar';
		document.frmConta.submit();
	}
}
function incluir() {
	document.frmConta.acao.value = 'incluir';
	document.frmConta.submit();
}
function excluir() {
	if (confirm("Deseja realmente excluir este Contato?")) {
		document.frmConta.acao.value = 'excluir';
		document.frmConta.submit();
	}
}
function cancelar() {
	window.location.href="/financas/jsp/Conta.jsp";
}
function voltar() {
	window.location.href="/financas/jsp/Portal.jsp";
}
function limparCampos() {
	cancelar();
}
