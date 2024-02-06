package br.com.financas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.financas.entidade.EContato;
import br.com.financas.io.Banco;
import br.com.financas.negocio.IContato;
import br.com.financas.util.Data;

public class PContato extends Persistencia implements IContato {
	
	Banco oBanco = null;
	
	public PContato(Banco banco) {
		this.oBanco = banco;
	}

	
	public int alterar(EContato contato) throws Exception {
		int registros = 0;
		if (contato != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "UPDATE contato SET NOME = ?,DATA_NASCIMENTO=?,SEXO=?,ENDERECO=?,COMPLEMENTO=?,BAIRRO=?,CEP=?,TELEFONE_FIXO=?,TELEFONE_CELULAR=?,EMAIL=? WHERE ID_CONTATO = ?";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {
					Data oData = new Data();
					if (contato.getNome() != null) {
						pstm.setString(1, contato.getNome()); 
					} else {
						pstm.setNull(1,java.sql.Types.VARCHAR);
					}
					if (contato.getDataNascimento() != null) {
						pstm.setDate(2, java.sql.Date.valueOf(oData.formatar(contato.getDataNascimento(), "yyyy-MM-dd")));
					} else {
						pstm.setNull(2,java.sql.Types.DATE);
					}
					if (contato.getSexo() == 'M' || contato.getSexo() == 'F') {
						pstm.setString(3, String.valueOf(contato.getSexo()));
					} else {
						pstm.setNull(3, java.sql.Types.CHAR);
					}
					if (contato.getEndereco() != null) {
						pstm.setString(4, contato.getEndereco());
					} else {
						pstm.setNull(4, java.sql.Types.VARCHAR);
					}
					if (contato.getComplemento() != null) {
						pstm.setString(5, contato.getComplemento());	
					} else {
						pstm.setNull(5, java.sql.Types.VARCHAR);
					}
					if (contato.getBairro() != null) {
						pstm.setString(6, contato.getBairro());
					} else {
						pstm.setNull(6, java.sql.Types.VARCHAR);
					}
					if (contato.getCep() != null) {
						pstm.setString(7, contato.getCep());
					} else {
						pstm.setNull(7, java.sql.Types.VARCHAR);
					}
					if (contato.getTelefoneFixo() != null) {
						pstm.setString(8, contato.getTelefoneFixo());
					} else {
						pstm.setNull(8, java.sql.Types.VARCHAR);
					}
					if (contato.getTelefoneCelular() != null) {
						pstm.setString(9, contato.getTelefoneCelular());
					} else {
						pstm.setNull(9, java.sql.Types.VARCHAR);
					}
					if (contato.getEmail() != null) {
						pstm.setString(10, contato.getEmail()); 
					} else {
						pstm.setNull(10, java.sql.Types.VARCHAR);
					}
					
					pstm.setInt(11, contato.getIdContato());
					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	
	public EContato consultar(EContato eContato) throws Exception {
		if (eContato != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				if (eContato.getNome() != null) {
					instrucaoSQL = "SELECT * FROM contato WHERE nome = ? ";
				} else if (eContato.getIdContato() != 0) {
					instrucaoSQL = "SELECT * FROM contato WHERE id_contato = ? ";
				}
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				ResultSet oRSet = null;
				if (pstm != null) {
					if (eContato.getNome() != null) {
						pstm.setString(1, eContato.getNome());
					} else if (eContato.getIdContato() != 0) {
						pstm.setInt(1, eContato.getIdContato());
					}					
					oRSet = pstm.executeQuery();
				}
				if (oRSet != null && oRSet.next()) {
					eContato.setIdContato(oRSet.getInt("ID_CONTATO"));
					eContato.setNome(oRSet.getString("NOME"));
					eContato.setDataNascimento(oRSet.getDate("DATA_NASCIMENTO"));
					eContato.setSexo(oRSet.getString("SEXO").charAt(0));
					eContato.setEndereco(oRSet.getString("ENDERECO"));
					eContato.setComplemento(oRSet.getString("COMPLEMENTO"));
					eContato.setBairro(oRSet.getString("BAIRRO"));
					eContato.setCep(oRSet.getString("CEP"));
					eContato.setTelefoneFixo(oRSet.getString("TELEFONE_FIXO"));
					eContato.setTelefoneCelular(oRSet.getString("TELEFONE_CELULAR"));
					eContato.setEmail(oRSet.getString("EMAIL"));	
				} else {
					return null;
				}
				oRSet.close();
			}
		}
		return eContato;
	}

	
	public int excluir(EContato contato) throws Exception {
		int registros = 0;
		if (contato != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "DELETE FROM contato WHERE ID_CONTATO = ?";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {					
					pstm.setInt(1, contato.getIdContato());					
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	
	public int incluir(EContato contato) throws Exception {
		int registros = 0;
		if (contato != null) {
			if (oBanco.getConexaoAtiva()) {
				String instrucaoSQL = new String();
				instrucaoSQL = "INSERT INTO contato (NOME,DATA_NASCIMENTO,SEXO,ENDERECO,COMPLEMENTO,BAIRRO,CEP,TELEFONE_FIXO,TELEFONE_CELULAR,EMAIL) VALUES (?,?,?,?,?,?,?,?,?,?)";				
				PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
				if (pstm != null) {
					Data oData = new Data();
					if (contato.getNome() != null) {
						pstm.setString(1, contato.getNome()); 
					} else {
						pstm.setNull(1,java.sql.Types.VARCHAR);
					}
					if (contato.getDataNascimento() != null) {
						pstm.setDate(2, java.sql.Date.valueOf(oData.formatar(contato.getDataNascimento(), "yyyy-MM-dd")));
					} else {
						pstm.setNull(2,java.sql.Types.DATE);
					}
					if (contato.getSexo() == 'M' || contato.getSexo() == 'F') {
						pstm.setString(3, String.valueOf(contato.getSexo()));
					} else {
						pstm.setNull(3, java.sql.Types.CHAR);
					}
					if (contato.getEndereco() != null) {
						pstm.setString(4, contato.getEndereco());
					} else {
						pstm.setNull(4, java.sql.Types.VARCHAR);
					}
					if (contato.getComplemento() != null) {
						pstm.setString(5, contato.getComplemento());	
					} else {
						pstm.setNull(5, java.sql.Types.VARCHAR);
					}
					if (contato.getBairro() != null) {
						pstm.setString(6, contato.getBairro());
					} else {
						pstm.setNull(6, java.sql.Types.VARCHAR);
					}
					if (contato.getCep() != null) {
						pstm.setString(7, contato.getCep());
					} else {
						pstm.setNull(7, java.sql.Types.VARCHAR);
					}
					if (contato.getTelefoneFixo() != null) {
						pstm.setString(8, contato.getTelefoneFixo());
					} else {
						pstm.setNull(8, java.sql.Types.VARCHAR);
					}
					if (contato.getTelefoneCelular() != null) {
						pstm.setString(9, contato.getTelefoneCelular());
					} else {
						pstm.setNull(9, java.sql.Types.VARCHAR);
					}
					if (contato.getEmail() != null) {
						pstm.setString(10, contato.getEmail()); 
					} else {
						pstm.setNull(10, java.sql.Types.VARCHAR);
					}
					registros = oBanco.executarSQL(pstm);
				}
			}
		}
		return registros;
	}

	public ArrayList<EContato> listar() throws Exception {
		if (oBanco.getConexaoAtiva()) {
			ArrayList<EContato> lstContato = new ArrayList<EContato>();
			String instrucaoSQL = new String("SELECT * FROM contato");
			PreparedStatement pstm = oBanco.criarPS(instrucaoSQL);
			ResultSet oRSet = pstm.executeQuery();
			while (oRSet.next()) {
				EContato eContato = new EContato();
				eContato.setIdContato(oRSet.getInt("ID_CONTATO"));
				eContato.setNome(oRSet.getString("NOME"));
				eContato.setDataNascimento(oRSet.getDate("DATA_NASCIMENTO"));
				eContato.setSexo(oRSet.getString("SEXO").charAt(0));
				eContato.setEndereco(oRSet.getString("ENDERECO"));
				eContato.setComplemento(oRSet.getString("COMPLEMENTO"));
				eContato.setBairro(oRSet.getString("BAIRRO"));
				eContato.setCep(oRSet.getString("CEP"));
				eContato.setTelefoneFixo(oRSet.getString("TELEFONE_FIXO"));
				eContato.setTelefoneCelular(oRSet.getString("TELEFONE_CELULAR"));
				eContato.setEmail(oRSet.getString("EMAIL"));
				lstContato.add(eContato);
			}
			oRSet.close();
			return lstContato;			
		}
		return null;
	}

}
