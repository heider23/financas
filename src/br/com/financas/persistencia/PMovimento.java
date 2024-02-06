package br.com.financas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.financas.entidade.ECategoria;
import br.com.financas.entidade.EConta;
import br.com.financas.entidade.EMovimento;
import br.com.financas.entidade.EUsuario;
import br.com.financas.io.Banco;
import br.com.financas.negocio.IMovimento;
import br.com.financas.util.Data;

public class PMovimento extends Persistencia implements IMovimento {
	
	Banco oBanco = null;
	
	public PMovimento(Banco banco) {
		this.oBanco = banco;
	}
	
	public int alterar(EMovimento Movimento) throws Exception {
		int registros = 0;
		if (Movimento != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "UPDATE Movimento SET ID_USUARIO = ?,ID_CONTA = ?,ID_CATEGORIA = ?,DATA = ?,HISTORICO = ?,VALOR = ? WHERE ID_MOVIMENTO = ? ";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {
					Data oData = new Data();
					if (Movimento.getEUsuario() != null && Movimento.getEUsuario().getId() != null) {
						pstm.setInt(1, Movimento.getEUsuario().getId());
					} else {
						pstm.setNull(1,java.sql.Types.INTEGER);
					}
					if (Movimento.getEConta() != null && Movimento.getEConta().getIdConta() != null) {
						pstm.setInt(2, Movimento.getEConta().getIdConta());
					} else {
						pstm.setNull(2,java.sql.Types.INTEGER);
					}
					if (Movimento.getECategoria() != null && Movimento.getECategoria().getIdCategoria() != null) {
						pstm.setInt(3, Movimento.getECategoria().getIdCategoria());
					} else {
						pstm.setNull(3,java.sql.Types.INTEGER);
					}
					if (Movimento.getData()!= null) {
						pstm.setDate(4, java.sql.Date.valueOf(oData.formatar(Movimento.getData(), "yyyy-MM-dd"))); 
					} else {
						pstm.setNull(4,java.sql.Types.DATE);
					}
					if (Movimento.getHistorico()!= null) {
						pstm.setString(5, Movimento.getHistorico()); 
					} else {
						pstm.setNull(5,java.sql.Types.VARCHAR);
					}
					pstm.setDouble(6, Movimento.getValor());	
					pstm.setInt(7, Movimento.getId());
					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	
	public ArrayList<EMovimento> consultar(EMovimento eMovimento) throws Exception {
		if (eMovimento != null) {
			if (oBanco.getConexaoAtiva()) {
				ArrayList<EMovimento> lstMovimento = new ArrayList<EMovimento>();
				
				PUsuario pUsuario = new PUsuario(this.oBanco);
				PConta pConta = new PConta(this.oBanco);
				PCategoria pCategoria = new PCategoria(this.oBanco);
				
				Data oData = new Data();
				String instrucaoSQL = new String("");
				
				String usuario = new String("");
				String conta = new String("");
				String categoria = new String("");
				String periodo = new String("");			
				String historico = new String("");
				String valor = new String("");

				if (eMovimento.getEUsuario() != null && 
						eMovimento.getEUsuario().getId() != null) {
					usuario = "ID_USUARIO = " +
								eMovimento.getEUsuario().getId() +
					            " AND ";				
					instrucaoSQL += usuario;
				}
				if (eMovimento.getEConta() != null && 
						eMovimento.getEConta().getIdConta() != null) {
					conta = "ID_CONTA = " +
								eMovimento.getEConta().getIdConta() +
					            " AND ";				
					instrucaoSQL += conta;
				}
				if (eMovimento.getECategoria() != null && 
						eMovimento.getECategoria().getIdCategoria() != null) {
					categoria = "ID_CATEGORIA = " +
								eMovimento.getECategoria().getIdCategoria() +
					            " AND ";				
					instrucaoSQL += categoria;
				}
				if (eMovimento.getData() != null) {
					periodo = "DATA >= '" +
					           oData.formatar(eMovimento.getData(), "yyyy-MM-dd") +
					          "' AND ";
					instrucaoSQL += periodo;
				}
				if (eMovimento.getHistorico() != null) {
					historico = "HISTORICO like '%" +
								eMovimento.getHistorico()+ 
						        "%' AND ";
					instrucaoSQL += historico;
				}
				if (eMovimento.getValor() != 0) {
					valor = "VALOR = " +
								eMovimento.getValor()+ 
						        " AND ";
					instrucaoSQL += valor;
				}
				instrucaoSQL = instrucaoSQL.substring(0,instrucaoSQL.lastIndexOf("AND"));
				instrucaoSQL = "SELECT * FROM MOVIMENTO WHERE " + instrucaoSQL + "ORDER BY ID_USUARIO,DATA ";
				ResultSet oRSet = oBanco.consultarSQL(instrucaoSQL);
				if (oRSet != null) {
					while (oRSet.next()) {
						EMovimento movimento = new EMovimento();
						movimento.setId(oRSet.getInt("ID_MOVIMENTO"));
						EUsuario eUsuario = new EUsuario();
						eUsuario.setId(oRSet.getInt("ID_USUARIO"));
						eUsuario = pUsuario.consultar(eUsuario);
						movimento.setEUsuario(eUsuario);
						EConta eConta = new EConta();
						eConta.setIdConta(oRSet.getInt("ID_CONTA"));
						eConta = pConta.consultar(eConta);
						movimento.setEConta(eConta);
						ECategoria eCategoria = new ECategoria();
						eCategoria.setIdCategoria(oRSet.getInt("ID_CATEGORIA"));
						eCategoria = pCategoria.consultar(eCategoria);
						movimento.setECategoria(eCategoria);
						movimento.setData(oRSet.getDate("DATA"));
						movimento.setHistorico(oRSet.getString("HISTORICO"));
						movimento.setValor(oRSet.getDouble("VALOR"));
						lstMovimento.add(movimento);
					}
					oRSet.close();
					return lstMovimento;
				}
			}
		}
		return null;
	}

	
	public int excluir(EMovimento Movimento) throws Exception {
		int registros = 0;
		if (Movimento != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "DELETE FROM Movimento WHERE ID_Movimento = ?";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {					
					pstm.setInt(1, Movimento.getId());					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	
	public int incluir(EMovimento Movimento) throws Exception {
		int registros = 0;
		if (Movimento != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "INSERT INTO Movimento (ID_USUARIO,ID_CONTA,ID_CATEGORIA,DATA,HISTORICO,VALOR) VALUES (?,?,?,?,?,?)";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {
					Data oData = new Data();
					if (Movimento.getEUsuario() != null && Movimento.getEUsuario().getId() != null) {
						pstm.setInt(1, Movimento.getEUsuario().getId());
					} else {
						pstm.setNull(1,java.sql.Types.INTEGER);
					}
					if (Movimento.getEConta() != null && Movimento.getEConta().getIdConta() != null) {
						pstm.setInt(2, Movimento.getEConta().getIdConta());
					} else {
						pstm.setNull(2,java.sql.Types.INTEGER);
					}
					if (Movimento.getECategoria() != null && Movimento.getECategoria().getIdCategoria() != null) {
						pstm.setInt(3, Movimento.getECategoria().getIdCategoria());
					} else {
						pstm.setNull(3,java.sql.Types.INTEGER);
					}
					if (Movimento.getData()!= null) {
						pstm.setDate(4, java.sql.Date.valueOf(oData.formatar(Movimento.getData(), "yyyy-MM-dd"))); 
					} else {
						pstm.setNull(4,java.sql.Types.DATE);
					}
					if (Movimento.getHistorico()!= null) {
						pstm.setString(5, Movimento.getHistorico()); 
					} else {
						pstm.setNull(5,java.sql.Types.VARCHAR);
					}
					
					pstm.setDouble(6, Movimento.getValor());	

					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	public ArrayList<EMovimento> listar() throws Exception {
		if (oBanco.getConexaoAtiva()) {
						
		}
		return null;
	}

}
