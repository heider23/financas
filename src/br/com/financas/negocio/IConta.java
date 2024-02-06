package br.com.financas.negocio;

import java.util.ArrayList;

import br.com.financas.entidade.EConta;

public interface IConta {

	public EConta consultar(EConta oEConta) throws Exception;
	public int alterar(EConta oEConta) throws Exception;
	public int incluir(EConta oEConta) throws Exception;
	public int excluir(EConta oEConta) throws Exception;
	public ArrayList<EConta> listar() throws Exception;
	
}
