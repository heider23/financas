package br.com.financas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.financas.entidade.ECompromisso;
import br.com.financas.entidade.EContato;
import br.com.financas.io.Banco;
import br.com.financas.negocio.ICompromisso;
import br.com.financas.util.Data;

public class PCompromisso extends Persistencia implements ICompromisso {
	
	Banco oBanco = null;
	
	public PCompromisso(Banco banco) {
		this.oBanco = banco;
	}

	
	public int alterar(ECompromisso compromisso) throws Exception {
		int registros = 0;
		if (compromisso != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "UPDATE compromisso SET DATA_HORA_INICIO = ?,DATA_HORA_FINAL = ?,ASSUNTO = ?,OBSERVACOES = ?,ID_CONTATO = ? WHERE ID_COMPROMISSO = ?";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {
					Data oData = new Data();
					if (compromisso.getDataHoraInicio() != null) {
						pstm.setTimestamp(1, java.sql.Timestamp.valueOf(oData.formatar(compromisso.getDataHoraInicio(), "yyyy-MM-dd HH:mm:ss")));
					} else {
						pstm.setNull(1,java.sql.Types.TIMESTAMP);
					}
					if (compromisso.getDataHoraFinal() != null) {
						pstm.setTimestamp(2, java.sql.Timestamp.valueOf(oData.formatar(compromisso.getDataHoraFinal(), "yyyy-MM-dd HH:mm:ss")));
					} else {
						pstm.setNull(2,java.sql.Types.DATE);
					}
					if (compromisso.getAssunto() != null) {
						pstm.setString(3, compromisso.getAssunto()); 
					} else {
						pstm.setNull(3,java.sql.Types.VARCHAR);
					}
					if (compromisso.getObservacoes() != null) {
						pstm.setString(4, compromisso.getObservacoes()); 
					} else {
						pstm.setNull(4,java.sql.Types.VARCHAR);
					}
					if (compromisso.getEContato().getIdContato() != null) {
						pstm.setInt(5, compromisso.getEContato().getIdContato()); 
					} else {
						pstm.setNull(5,java.sql.Types.INTEGER);
					}					
					
					pstm.setInt(6, compromisso.getIdCompromisso());
					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	
	public ArrayList<ECompromisso> consultar(ECompromisso eCompromisso) throws Exception {
		if (oBanco.getConexaoAtiva()) {
			ArrayList<ECompromisso> lstCompromisso = new ArrayList<ECompromisso>();
			PContato pContato = new PContato(this.oBanco);
			Data oData = new Data();
			String instrucaoSQL = new String("");
			
			String periodo = new String("");			
			String assunto = new String("");			
			String observacoes = new String("");			
			String idContato = new String("");
			
			if (eCompromisso.getDataHoraInicio() != null && eCompromisso.getDataHoraFinal() != null) {
				periodo = "DATA_HORA_INICIO >= '" +
				           oData.formatar(eCompromisso.getDataHoraInicio(), "yyyy-MM-dd HH:mm:ss") +
				          "' AND DATA_HORA_FINAL <= '" +
				           oData.formatar(eCompromisso.getDataHoraFinal(), "yyyy-MM-dd HH:mm:ss") +
				          "' AND ";
				instrucaoSQL += periodo;
			}
			if (eCompromisso.getAssunto() != null) {
				assunto = "ASSUNTO like '%" +
					      eCompromisso.getAssunto()+ 
					      "%' AND ";
				instrucaoSQL += assunto;
			}
			if (eCompromisso.getObservacoes() != null) {
				observacoes = "OBSERVACOES like '%" +
				              eCompromisso.getObservacoes() +
				              "%' AND ";
				instrucaoSQL += observacoes;
			}
			if (eCompromisso.getEContato() != null && 
					eCompromisso.getEContato().getIdContato() != null) {
				idContato = "ID_CONTATO = " +
				            eCompromisso.getEContato().getIdContato() +
				            " AND ";				
				instrucaoSQL += idContato;
			}
			instrucaoSQL = instrucaoSQL.substring(0,instrucaoSQL.lastIndexOf("AND"));
			instrucaoSQL = "SELECT * FROM COMPROMISSO WHERE " + instrucaoSQL;
			ResultSet oRSet = oBanco.consultarSQL(instrucaoSQL);
			if (oRSet != null) {
				while (oRSet.next()) {
					ECompromisso compromisso = new ECompromisso();
					compromisso.setIdCompromisso(oRSet.getInt("ID_COMPROMISSO"));
					compromisso.setDataHoraInicio(oRSet.getTimestamp("DATA_HORA_INICIO"));
					compromisso.setDataHoraFinal(oRSet.getTimestamp("DATA_HORA_FINAL"));
					compromisso.setAssunto(oRSet.getString("ASSUNTO"));
					compromisso.setObservacoes(oRSet.getString("OBSERVACOES"));
					EContato eContato = new EContato();
					eContato.setIdContato(oRSet.getInt("ID_CONTATO"));
					eContato = pContato.consultar(eContato);
					compromisso.setEContato(eContato);	
					lstCompromisso.add(compromisso);
				}
				oRSet.close();
				return lstCompromisso;
			}
		}
		return null;
	}

	
	public int excluir(ECompromisso compromisso) throws Exception {
		int registros = 0;
		if (compromisso != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "DELETE FROM compromisso WHERE ID_COMPROMISSO = ?";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {					
					pstm.setInt(1, compromisso.getIdCompromisso());					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	
	public int incluir(ECompromisso compromisso) throws Exception {
		int registros = 0;
		if (compromisso != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "INSERT INTO compromisso (DATA_HORA_INICIO,DATA_HORA_FINAL,ASSUNTO,OBSERVACOES,ID_CONTATO) VALUES (?,?,?,?,?)";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {
					Data oData = new Data();
					if (compromisso.getDataHoraInicio() != null) {
						pstm.setTimestamp(1, java.sql.Timestamp.valueOf(oData.formatar(compromisso.getDataHoraInicio(), "yyyy-MM-dd HH:mm:ss")));
					} else {
						pstm.setNull(1,java.sql.Types.DATE);
					}
					if (compromisso.getDataHoraFinal() != null) {
						pstm.setTimestamp(2, java.sql.Timestamp.valueOf(oData.formatar(compromisso.getDataHoraFinal(), "yyyy-MM-dd HH:mm:ss")));
					} else {
						pstm.setNull(2,java.sql.Types.DATE);
					}
					if (compromisso.getAssunto() != null) {
						pstm.setString(3, compromisso.getAssunto()); 
					} else {
						pstm.setNull(3,java.sql.Types.VARCHAR);
					}
					if (compromisso.getObservacoes() != null) {
						pstm.setString(4, compromisso.getObservacoes()); 
					} else {
						pstm.setNull(4,java.sql.Types.VARCHAR);
					}
					if (compromisso.getEContato().getIdContato() != null) {
						pstm.setInt(5, compromisso.getEContato().getIdContato()); 
					} else {
						pstm.setNull(5,java.sql.Types.INTEGER);
					}					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	public ArrayList<ECompromisso> listar() throws Exception {
		if (oBanco.getConexaoAtiva()) {
			PContato pContato = new PContato(this.oBanco);
			String instrucaoSQL = new String("SELECT * FROM compromisso ");
			ArrayList<ECompromisso> lstCompromisso = new ArrayList<ECompromisso>();
			PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
			ResultSet oRSet = pstm.executeQuery();
			while (oRSet.next()) {
				ECompromisso compromisso = new ECompromisso();
				compromisso.setIdCompromisso(oRSet.getInt("ID_COMPROMISSO"));
				compromisso.setDataHoraInicio(oRSet.getDate("DATA_HORA_INICIO"));
				compromisso.setDataHoraFinal(oRSet.getDate("DATA_HORA_FINAL"));
				compromisso.setAssunto(oRSet.getString("ASSUNTO"));
				compromisso.setObservacoes(oRSet.getString("OBSERVACOES"));
				EContato eContato = new EContato();
				eContato.setIdContato(oRSet.getInt("ID_CONTATO"));
				eContato = pContato.consultar(eContato);
				compromisso.setEContato(eContato);	
				lstCompromisso.add(compromisso);
			}
			oRSet.close();
			return lstCompromisso;			
		}
		return null;
	}

}
