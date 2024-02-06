package br.com.financas.negocio;

import java.util.List;

import br.com.financas.entidade.EUsuario;

public interface IUsuario {

	public EUsuario consultar(String usuario) throws Exception;
	public int alterar(EUsuario oEUsuario) throws Exception;
	public int incluir(EUsuario oEUsuario) throws Exception;
	public int excluir(EUsuario oEUsuario) throws Exception;
	public List<EUsuario> listar(EUsuario oEUsuario) throws Exception;
	public List<EUsuario> listar() throws Exception;
	
}
