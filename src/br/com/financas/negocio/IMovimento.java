package br.com.financas.negocio;

import java.util.ArrayList;

import br.com.financas.entidade.EMovimento;

public interface IMovimento {

	public ArrayList<EMovimento> consultar(EMovimento oEMovimento) throws Exception;
	public int alterar(EMovimento oEMovimento) throws Exception;
	public int incluir(EMovimento oEMovimento) throws Exception;
	public int excluir(EMovimento oEMovimento) throws Exception;
	public ArrayList<EMovimento> listar() throws Exception;
	
}
