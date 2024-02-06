/**
 * Variáveis globais
 **/
var	botaoCON;
var	botaoALT;
var	botaoINC;
var	botaoEXC;
var movimentoSelecionado = null;

function atualizar(pAcao,pMsg) {
	try {
		botaoCON = document.frmMovimento.CON.disabled == false;
		botaoALT = document.frmMovimento.ALT.disabled == false;
		botaoINC = document.frmMovimento.INC.disabled == false;
		botaoEXC = document.frmMovimento.EXC.disabled == false;
		
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
	 	document.frmMovimento.CON.disabled = '';
	 	return true;
	 } else {
	 	document.frmMovimento.CON.disabled = 'disabled';
	 	return false;
	 }
}

function podeALT(pALT) {
	 if (botaoALT && pALT) {
	 	document.frmMovimento.ALT.disabled = '';
	 	return true;
	 } else {
	 	document.frmMovimento.ALT.disabled = 'disabled';
	 	return false;
	 }
}

function podeINC(pINC) {
	 if (botaoINC && pINC) {
	 	document.frmMovimento.INC.disabled = '';
	 	return true;
	 } else {
	 	document.frmMovimento.INC.disabled = 'disabled';
	 	return false;
	 }
}

function podeEXC(pEXC) {
	 if (botaoEXC && pEXC) {
	 	document.frmMovimento.EXC.disabled = '';
	 	return true;
	 } else {
	 	document.frmMovimento.EXC.disabled = 'disabled';
	 	return false;
	 }
}

function consultar() {
		document.frmMovimento.acao.value = 'consultar';
		document.frmMovimento.submit();
		return;
}
function alterar() {
	if(confirm("Deseja realmente alterar os dados deste Movimento?")) {
		document.frmMovimento.acao.value = 'alterar';
		document.frmMovimento.submit();
	}
}
function incluir() {
	document.frmMovimento.acao.value = 'incluir';
	document.frmMovimento.submit();
}
function excluir() {
	if (confirm("Deseja realmente excluir este Movimento?")) {
		document.frmMovimento.acao.value = 'excluir';
		document.frmMovimento.submit();
	}
}
function cancelar() {
	window.location.href="/financas/jsp/Movimento.jsp";
}
function voltar() {
	window.location.href="/financas/jsp/Portal.jsp";
}
function limparCampos() {
	cancelar();
}
function selecionaMovimento(objMovimento, idMovimento, idConta, idCategoria) {
	if (movimentoSelecionado != null) {
		movimentoSelecionado.style.backgroundColor = '';
	}
	objMovimento.style.backgroundColor  = 'green';
	document.frmMovimento.idMovimento.value = idMovimento;
	document.frmMovimento.idConta.value = idConta;
	document.frmMovimento.idCategoria.value = idCategoria;
	document.frmMovimento.dataMovimento.value = objMovimento.cells[0].innerHTML
	document.frmMovimento.historicoMovimento.value = objMovimento.cells[2].innerHTML
	document.frmMovimento.valorMovimento.value = objMovimento.cells[3].innerHTML	
	podeCON(false);
	podeALT(true);
	podeINC(false);
	podeEXC(true);
	movimentoSelecionado = objMovimento;
}
function setarSelect(elementId,valor) {
	var obj = eval(document.getElementById(elementId));
	obj.value = valor;
}