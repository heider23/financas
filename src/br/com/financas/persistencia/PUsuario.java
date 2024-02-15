/**
 * 
 */
package br.com.financas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.financas.entidade.EUsuario;
import br.com.financas.io.Banco;
import br.com.financas.negocio.IUsuario;

/**
 * @author Administrador
 *
 */
public class PUsuario extends Persistencia implements IUsuario {

	Banco oBanco = null;
	
	/**
	 * Constructor da classe.
	 * @param transacao - indica a utilizacao ou nao de uma transacao.
	 */
	public PUsuario(Banco oBanco) {
			this.oBanco = oBanco;
	}

	public EUsuario consultar(String usuario) throws Exception {
		EUsuario eUsuario = null;
		if (usuario != null && !usuario.equals("")) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "SELECT * FROM usuario WHERE apelido = ?";
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				ResultSet objResultSet = null;
				if (pstm != null) {
					pstm.setString(1, usuario);
					objResultSet = pstm.executeQuery();
				}
				if (objResultSet != null && objResultSet.next()) {
					eUsuario = new EUsuario();
					eUsuario.setId(new Integer(objResultSet.getInt("id_usuario")));
					eUsuario.setNome(objResultSet.getString("nome"));
					eUsuario.setApelido(objResultSet.getString("apelido"));
					eUsuario.setSenha(objResultSet.getString("senha"));
					eUsuario.setStatus(objResultSet.getString("status").charAt(0));
				}
				objResultSet.close();
			}
		}
		return eUsuario;
	}

	public EUsuario consultar(EUsuario eUsuario) throws Exception {
		if (eUsuario != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "SELECT * FROM usuario WHERE id_usuario = ?";
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				ResultSet objResultSet = null;
				if (pstm != null) {
					pstm.setInt(1, eUsuario.getId());
					objResultSet = pstm.executeQuery();
				}
				if (objResultSet != null && objResultSet.next()) {
					eUsuario = new EUsuario();
					eUsuario.setId(new Integer(objResultSet.getInt("id_usuario")));
					eUsuario.setNome(objResultSet.getString("nome"));
					eUsuario.setApelido(objResultSet.getString("apelido"));
					eUsuario.setSenha(objResultSet.getString("senha"));
					eUsuario.setStatus(objResultSet.getString("status").charAt(0));
				}
				objResultSet.close();
			}
		}
		return eUsuario;
	}
	public int alterar(EUsuario usuario) throws Exception {
		int registros = 0;
		if (usuario != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "UPDATE Usuario SET NOME = ?, STATUS = ?, APELIDO = ? ,SENHA = ? WHERE  ID_USUARIO = ?";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null && usuario.getId() > 0) {
					if (usuario.getNome() != null && !usuario.getNome().isEmpty()) {
						pstm.setString(1, usuario.getNome());
					} else {
						pstm.setNull(1, java.sql.Types.VARCHAR);
					}
					if (usuario.getStatus() != ' ') {
						pstm.setString(2, String.valueOf(usuario.getStatus()));
					} else {
						pstm.setNull(2, java.sql.Types.CHAR);
					}
					if (usuario.getApelido() != null && !usuario.getApelido().isEmpty()) {
						pstm.setString(3, usuario.getApelido());
					} else {
						pstm.setNull(3, java.sql.Types.VARCHAR);
					}
					if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
						pstm.setString(4, usuario.getSenha());
					} else {
						pstm.setNull(4, java.sql.Types.VARCHAR);
					}		
					pstm.setInt(5, usuario.getId());
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	public int excluir(EUsuario usuario) throws Exception {
		int registros = 0;
		if (usuario != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "delete from Usuario WHERE ID_USUARIO = ?";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null && usuario.getId() > 0) {
					pstm.setInt(1, usuario.getId());
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	public int incluir(EUsuario usuario) throws Exception {
		int registros = 0;
		if (usuario != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "INSERT INTO Usuario (NOME,STATUS,APELIDO,SENHA) VALUES (?,?,?,?)";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {
					if (usuario.getNome() != null && !usuario.getNome().isEmpty()) {
						pstm.setString(1, usuario.getNome());
					} else {
						pstm.setNull(1, java.sql.Types.VARCHAR);
					}
					if (usuario.getStatus() != ' ') {
						pstm.setString(2, String.valueOf(usuario.getStatus()));
					} else {
						pstm.setNull(2, java.sql.Types.CHAR);
					}
					if (usuario.getApelido() != null && !usuario.getApelido().isEmpty()) {
						pstm.setString(3, usuario.getApelido());
					} else {
						pstm.setNull(3, java.sql.Types.VARCHAR);
					}
					if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
						pstm.setString(4, usuario.getSenha());
					} else {
						pstm.setNull(4, java.sql.Types.VARCHAR);
					}					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	public List<EUsuario> listar(EUsuario usuario) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EUsuario> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
