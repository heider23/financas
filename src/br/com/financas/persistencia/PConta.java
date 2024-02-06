package br.com.financas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.financas.entidade.EConta;
import br.com.financas.entidade.EUsuario;
import br.com.financas.io.Banco;
import br.com.financas.negocio.IConta;
import br.com.financas.util.Data;

public class PConta extends Persistencia implements IConta {
	
	Banco oBanco = null;
	
	public PConta(Banco banco) {
		this.oBanco = banco;
	}

	
	public int alterar(EConta Conta) throws Exception {
		int registros = 0;
		if (Conta != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "UPDATE CONTA SET ID_USUARIO = ?, NOME_CONTA = ?, DESCRICAO = ?, DATA_CRIACAO = ? WHERE ID_CONTA = ?";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {
					Data oData = new Data();
					if (Conta.getEUsuario() != null && Conta.getEUsuario().getId() != null) {
						pstm.setInt(1, Conta.getEUsuario().getId()); 
					} else {
						pstm.setNull(1,java.sql.Types.INTEGER);
					}
					if (Conta.getNome() != null) {
						pstm.setString(2, Conta.getNome()); 
					} else {
						pstm.setNull(2,java.sql.Types.VARCHAR);
					}
					if (Conta.getDescricao() != null) {
						pstm.setString(3, Conta.getDescricao()); 
					} else {
						pstm.setNull(3,java.sql.Types.VARCHAR);
					}
					if (Conta.getDataCriacao() != null) {
						pstm.setDate(4, java.sql.Date.valueOf(oData.formatar(Conta.getDataCriacao(), "yyyy-MM-dd"))); 
					} else {
						pstm.setNull(4,java.sql.Types.DATE);
					}
					pstm.setInt(5, Conta.getIdConta());
					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	
	public EConta consultar(EConta eConta) throws Exception {
		if (eConta != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				PUsuario pUsuario = new PUsuario(this.oBanco);
				if (eConta.getNome() != null) {
					instrucaoSQL = "SELECT * FROM Conta WHERE nome_conta = ? ";
				} else if (eConta.getIdConta() != 0) {
					instrucaoSQL = "SELECT * FROM Conta WHERE id_Conta = ? ";
				}
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				ResultSet oRSet = null;
				if (pstm != null) {
					if (eConta.getNome() != null) {
						pstm.setString(1, eConta.getNome());
					} else if (eConta.getIdConta() != 0) {
						pstm.setInt(1, eConta.getIdConta());
					}					
					oRSet = pstm.executeQuery();
				}
				if (oRSet != null && oRSet.next()) {
					eConta.setIdConta(oRSet.getInt("ID_CONTA"));
					EUsuario eUsuario = new EUsuario();
					eUsuario.setId(oRSet.getInt("ID_USUARIO"));
					eUsuario = pUsuario.consultar(eUsuario);
					eConta.setEUsuario(eUsuario);
					eConta.setNome(oRSet.getString("NOME_CONTA"));
					eConta.setDescricao(oRSet.getString("DESCRICAO"));
					eConta.setDataCriacao(oRSet.getDate("DATA_CRIACAO"));
				} else {
					return null;
				}
				oRSet.close();
			}
		}
		return eConta;
	}

	
	public int excluir(EConta Conta) throws Exception {
		int registros = 0;
		if (Conta != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "DELETE FROM Conta WHERE ID_CONTA = ?";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {					
					pstm.setInt(1, Conta.getIdConta());					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	
	public int incluir(EConta Conta) throws Exception {
		int registros = 0;
		if (Conta != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "INSERT INTO Conta (ID_USUARIO,NOME_CONTA,DESCRICAO,DATA_CRIACAO) VALUES (?,?,?,?)";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {
					Data oData = new Data();
					if (Conta.getEUsuario() != null && Conta.getEUsuario().getId() != null) {
						pstm.setInt(1, Conta.getEUsuario().getId());
					} else {
						pstm.setNull(1,java.sql.Types.INTEGER);
					}
					if (Conta.getNome() != null) {
						pstm.setString(2, Conta.getNome()); 
					} else {
						pstm.setNull(2,java.sql.Types.VARCHAR);
					}
					if (Conta.getDescricao() != null) {
						pstm.setString(3, Conta.getDescricao()); 
					} else {
						pstm.setNull(3,java.sql.Types.VARCHAR);
					}
					if (Conta.getDataCriacao() != null) {
						pstm.setDate(4, java.sql.Date.valueOf(oData.formatar(Conta.getDataCriacao(), "yyyy-MM-dd"))); 
					} else {
						pstm.setNull(4,java.sql.Types.DATE);
					}
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	public ArrayList<EConta> listar() throws Exception {
		if (oBanco.getConexaoAtiva()) {
			PUsuario pUsuario = new PUsuario(this.oBanco);
			ArrayList<EConta> lstConta = new ArrayList<EConta>();
			String instrucaoSQL = new String("SELECT * FROM Conta");
			PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
			ResultSet oRSet = pstm.executeQuery();
			while (oRSet.next()) {
				EConta eConta = new EConta();
				eConta.setIdConta(oRSet.getInt("ID_CONTA"));
				EUsuario eUsuario = new EUsuario();
				eUsuario.setId(oRSet.getInt("ID_USUARIO"));
				eUsuario = pUsuario.consultar(eUsuario);
				eConta.setEUsuario(eUsuario);
				eConta.setNome(oRSet.getString("NOME_CONTA"));
				eConta.setDescricao(oRSet.getString("DESCRICAO"));
				eConta.setDataCriacao(oRSet.getDate("DATA_CRIACAO"));
				lstConta.add(eConta);
			}
			oRSet.close();
			return lstConta;			
		}
		return null;
	}
	
	public ArrayList<EConta> listar(Integer idUsuario) throws Exception {
		if (oBanco.getConexaoAtiva()) {
			PUsuario pUsuario = new PUsuario(this.oBanco);
			ArrayList<EConta> lstConta = new ArrayList<EConta>();
			String instrucaoSQL = new String("SELECT * FROM Conta WHERE ID_USUARIO = ?");
			PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
			ResultSet oRSet = null;
			if (pstm != null) {
				pstm.setInt(1, idUsuario);
				oRSet = pstm.executeQuery();
			}
			if (oRSet != null) {
				while (oRSet.next()) {
					EConta eConta = new EConta();
					eConta.setIdConta(oRSet.getInt("ID_CONTA"));
					EUsuario eUsuario = new EUsuario();
					eUsuario.setId(oRSet.getInt("ID_USUARIO"));
					eUsuario = pUsuario.consultar(eUsuario);
					eConta.setEUsuario(eUsuario);
					eConta.setNome(oRSet.getString("NOME_CONTA"));
					eConta.setDescricao(oRSet.getString("DESCRICAO"));
					eConta.setDataCriacao(oRSet.getDate("DATA_CRIACAO"));
					lstConta.add(eConta);
				}
				oRSet.close();
			}
			return lstConta;			
		}
		return null;
	}

}
