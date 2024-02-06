package br.com.financas.negocio;

import java.util.ArrayList;

import br.com.financas.entidade.EContato;

public interface IContato {

	public EContato consultar(EContato oEContato) throws Exception;
	public int alterar(EContato oEContato) throws Exception;
	public int incluir(EContato oEContato) throws Exception;
	public int excluir(EContato oEContato) throws Exception;
	public ArrayList<EContato> listar() throws Exception;
	
}
