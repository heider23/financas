package br.com.financas.negocio;

import java.util.ArrayList;

import br.com.financas.entidade.ECategoria;

public interface ICategoria {

	public ECategoria consultar(ECategoria oECategoria) throws Exception;
	public int alterar(ECategoria oECategoria) throws Exception;
	public int incluir(ECategoria oECategoria) throws Exception;
	public int excluir(ECategoria oECategoria) throws Exception;
	public ArrayList<ECategoria> listar() throws Exception;
	
}
