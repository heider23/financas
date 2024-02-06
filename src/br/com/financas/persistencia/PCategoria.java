package br.com.financas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.financas.entidade.ECategoria;
import br.com.financas.io.Banco;
import br.com.financas.negocio.ICategoria;

public class PCategoria extends Persistencia implements ICategoria {
	
	Banco oBanco = null;
	
	public PCategoria(Banco banco) {
		this.oBanco = banco;
	}
	
	public int alterar(ECategoria Categoria) throws Exception {
		int registros = 0;
		if (Categoria != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "UPDATE Categoria SET NOME_Categoria = ? WHERE ID_Categoria = ?";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {
					if (Categoria.getNomeCategoria() != null) {
						pstm.setString(1, Categoria.getNomeCategoria()); 
					} else {
						pstm.setNull(1,java.sql.Types.VARCHAR);
					}

					pstm.setInt(2, Categoria.getIdCategoria());
					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	
	public ECategoria consultar(ECategoria eCategoria) throws Exception {
		if (eCategoria != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				if (eCategoria.getNomeCategoria() != null) {
					instrucaoSQL = "SELECT * FROM Categoria WHERE nome_Categoria = ? ";
				} else if (eCategoria.getIdCategoria() != 0) {
					instrucaoSQL = "SELECT * FROM Categoria WHERE id_Categoria = ? ";
				}
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				ResultSet oRSet = null;
				if (pstm != null) {
					if (eCategoria.getNomeCategoria() != null) {
						pstm.setString(1, eCategoria.getNomeCategoria());
					} else if (eCategoria.getIdCategoria() != 0) {
						pstm.setInt(1, eCategoria.getIdCategoria());
					}					
					oRSet = pstm.executeQuery();
				}
				if (oRSet != null && oRSet.next()) {
					eCategoria.setIdCategoria(oRSet.getInt("ID_Categoria"));
					eCategoria.setNomeCategoria(oRSet.getString("NOME_Categoria"));
				} else {
					return null;
				}
				oRSet.close();
			}
		}
		return eCategoria;
	}

	
	public int excluir(ECategoria Categoria) throws Exception {
		int registros = 0;
		if (Categoria != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "DELETE FROM Categoria WHERE ID_Categoria = ?";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {					
					pstm.setInt(1, Categoria.getIdCategoria());					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	
	public int incluir(ECategoria Categoria) throws Exception {
		int registros = 0;
		if (Categoria != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "INSERT INTO Categoria (NOME_Categoria) VALUES (?)";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {
					if (Categoria.getNomeCategoria() != null) {
						pstm.setString(1, Categoria.getNomeCategoria()); 
					} else {
						pstm.setNull(1,java.sql.Types.VARCHAR);
					}
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	public ArrayList<ECategoria> listar() throws Exception {
		if (oBanco.getConexaoAtiva()) {
			ArrayList<ECategoria> lstCategoria = new ArrayList<ECategoria>();
			String instrucaoSQL = new String("SELECT * FROM Categoria");
			PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
			ResultSet oRSet = pstm.executeQuery();
			while (oRSet.next()) {
				ECategoria eCategoria = new ECategoria();
				eCategoria.setIdCategoria(oRSet.getInt("ID_Categoria"));
				eCategoria.setNomeCategoria(oRSet.getString("NOME_Categoria"));
				lstCategoria.add(eCategoria);
			}
			oRSet.close();
			return lstCategoria;			
		}
		return null;
	}

}
