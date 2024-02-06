package br.com.financas.negocio;

import java.util.ArrayList;

import br.com.financas.entidade.ECompromisso;

public interface ICompromisso {

	public ArrayList<ECompromisso> consultar(ECompromisso oECompromisso) throws Exception;
	public int alterar(ECompromisso oECompromisso) throws Exception;
	public int incluir(ECompromisso oECompromisso) throws Exception;
	public int excluir(ECompromisso oECompromisso) throws Exception;
	public ArrayList<ECompromisso> listar() throws Exception;
	
}
