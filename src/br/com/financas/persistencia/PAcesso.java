package br.com.financas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.financas.entidade.EAcesso;
import br.com.financas.entidade.EUsuario;
import br.com.financas.io.Banco;
import br.com.financas.negocio.IAcesso;

public class PAcesso extends Persistencia implements IAcesso {
	
	Banco oBanco = null;

	public PAcesso(Banco banco) {
		this.oBanco = banco;
	}
	
	public EAcesso consultar(Integer usuario, String recurso) throws Exception {
		EAcesso eAcesso = null;
		if (usuario != null && recurso != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "SELECT * FROM acesso WHERE id_usuario = ? and nome_recurso = ?";
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				ResultSet objResultSet = null;
				if (pstm != null) {
					pstm.setInt(1, usuario);
					pstm.setString(2, recurso);
					objResultSet = pstm.executeQuery();
				}
				if (objResultSet != null && objResultSet.next()) {
					eAcesso = new EAcesso();
					eAcesso.setAplicacao(null);
					eAcesso.setUsuario(null);
					eAcesso.setConsulta(objResultSet.getString("consulta").charAt(0));
					eAcesso.setAltera(objResultSet.getString("altera").charAt(0));
					eAcesso.setInclui(objResultSet.getString("inclui").charAt(0));
					eAcesso.setExclui(objResultSet.getString("exclui").charAt(0));					
				}
				objResultSet.close();
			}
		}
		return eAcesso;
	}

	public int alterar(EAcesso acesso) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int incluir(EAcesso acesso) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int excluir(EAcesso acesso) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<EAcesso> listar(EUsuario usuario) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EAcesso> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
