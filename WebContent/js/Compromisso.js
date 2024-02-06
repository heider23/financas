/**
 * Variáveis globais
 **/
var	botaoCON;
var	botaoALT;
var	botaoINC;
var	botaoEXC;
var compromissoSelecionado = null;

function atualizar(pAcao,pMsg) {
	try {
		botaoCON = document.frmCompromisso.CON.disabled == false;
		botaoALT = document.frmCompromisso.ALT.disabled == false;
		botaoINC = document.frmCompromisso.INC.disabled == false;
		botaoEXC = document.frmCompromisso.EXC.disabled == false;
		
		/*
		 * Estas regras para habilitar/desabilitar os botoes
		 */
		if (pMsg == '' && pAcao == 'consultar') {
			podeCON(true);
			podeALT(false);
			podeINC(true);
			podeEXC(false);	
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
	 	document.frmCompromisso.CON.disabled = '';
	 	return true;
	 } else {
	 	document.frmCompromisso.CON.disabled = 'disabled';
	 	return false;
	 }
}

function podeALT(pALT) {
	 if (botaoALT && pALT) {
	 	document.frmCompromisso.ALT.disabled = '';
	 	return true;
	 } else {
	 	document.frmCompromisso.ALT.disabled = 'disabled';
	 	return false;
	 }
}

function podeINC(pINC) {
	 if (botaoINC && pINC) {
	 	document.frmCompromisso.INC.disabled = '';
	 	return true;
	 } else {
	 	document.frmCompromisso.INC.disabled = 'disabled';
	 	return false;
	 }
}

function podeEXC(pEXC) {
	 if (botaoEXC && pEXC) {
	 	document.frmCompromisso.EXC.disabled = '';
	 	return true;
	 } else {
	 	document.frmCompromisso.EXC.disabled = 'disabled';
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
	document.frmCompromisso.acao.value = 'consultar';
	document.frmCompromisso.submit();
}
function alterar() {
	if(confirm("Deseja realmente alterar os dados deste Contato?")) {
		document.frmCompromisso.acao.value = 'alterar';
		document.frmCompromisso.submit();
	}
}
function incluir() {
	document.frmCompromisso.acao.value = 'incluir';
	document.frmCompromisso.submit();
}
function excluir() {
	if (confirm("Deseja realmente excluir este Contato?")) {
		document.frmCompromisso.acao.value = 'excluir';
		document.frmCompromisso.submit();
	}
}
function cancelar() {
	window.location.href="/financas/jsp/Compromisso.jsp";
}
function voltar() {
	window.location.href="/financas/jsp/Portal.jsp";
}
function limparCampos() {
	cancelar();
}
function selecionaCompromisso(objCompromisso) {
	if (compromissoSelecionado != null) {
		compromissoSelecionado.style.backgroundColor = '';
	}
	objCompromisso.style.backgroundColor  = 'green';
	document.frmCompromisso.idCompromisso.value = objCompromisso.cells[0].innerHTML;
	document.frmCompromisso.idContato.value = objCompromisso.cells[5].innerHTML;
	document.frmCompromisso.assunto.value = objCompromisso.cells[1].innerHTML;
	document.frmCompromisso.observacoes.value = objCompromisso.cells[2].innerHTML;
	document.frmCompromisso.dataHoraInicio.value = objCompromisso.cells[3].innerHTML;
	document.frmCompromisso.dataHoraFinal.value = objCompromisso.cells[4].innerHTML;
	document.frmCompromisso.nome.value = objCompromisso.cells[6].innerHTML;	
	podeCON(false);
	podeALT(true);
	podeINC(false);
	podeEXC(true);
	compromissoSelecionado = objCompromisso;
}
