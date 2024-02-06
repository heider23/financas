package br.com.financas.negocio;

import java.util.List;

import br.com.financas.entidade.EAcesso;
import br.com.financas.entidade.EUsuario;

public interface IAcesso {
	
	public EAcesso consultar(Integer usuario, String recurso) throws Exception;
	public int alterar(EAcesso oEAcesso) throws Exception;
	public int incluir(EAcesso oEAcesso) throws Exception;
	public int excluir(EAcesso oEAcesso) throws Exception;
	public List<EAcesso> listar(EUsuario oEUsuario) throws Exception;
	public List<EAcesso> listar() throws Exception;
	
}
