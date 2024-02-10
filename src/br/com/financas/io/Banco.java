package br.com.financas.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;


public class Banco {

	private Connection oConn = null;
	private boolean transacao = false;
	private boolean conexaoAtiva = false;
	private String sqlState = new String();

	private Properties properties = loadProperties();
	/*
	 * Dados do Banco
	 */
	private String nomeBanco = properties.getProperty("database.name");
	private String nomeUsuarioSistema = properties.getProperty("database.user");
	private String senhaUsuarioSistema = properties.getProperty("database.password");

	/*
	 * Configura��o para acesso com o MySql
	 */
	private String strDriver = properties.getProperty("database.driver");
	private String strConexao = properties.getProperty("database.url");

	private Properties loadProperties() {
		Properties properties = new Properties();
		try {
						
			InputStream inputStream = new FileInputStream(this.getPasta()+"database.properties");
			properties.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	private String getPasta() throws Exception {
		URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
		File file = new File(url.toURI());
		String pasta  = file.getAbsolutePath();
		pasta = pasta.substring(0, pasta.length()-8)+"\\";
		return pasta;
	}

	/*
	 * Constructor padrao
	 */
	public Banco() {

	}

	public String obterSistema() {
		return this.nomeBanco.trim();
	}

	public final boolean conectar() throws Exception {
		return obterConexao();
	}

	private boolean obterConexao() throws Exception {
		if (!this.getConexaoAtiva()) {
			try {
				Class.forName(strDriver);
				oConn = DriverManager.getConnection(strConexao + this.nomeBanco, this.nomeUsuarioSistema,
						this.senhaUsuarioSistema);
				this.setConexaoAtiva(true);
			} catch (SQLException e) {
				throw new SQLException(e.getMessage());
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
		return (oConn != null);
	}

	public void setConexaoAtiva(boolean conexaoAtiva) {
		this.conexaoAtiva = conexaoAtiva;
	}

	public boolean getConexaoAtiva() {
		return this.conexaoAtiva;
	}

	public final boolean desconectar() throws Exception {

		if (this.getTransacao()) {
			fecharTransacao();
		}
		if (!this.oConn.isClosed()) {
			this.oConn.close();
			this.setConexaoAtiva(false);
		}

		return true;

	}

	public final void abrirTransacao() throws Exception {

		try {

			oConn.setAutoCommit(false);

		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}

	}

	public final void fecharTransacao() throws Exception {
		try {
			oConn.commit();
			oConn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	public final void cancelarTransacao() throws Exception {

		this.sqlState = "";

		try {
			oConn.rollback();
			oConn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean getTransacao() {
		return this.transacao;
	}

	public void setTransacao(boolean transacao) {
		this.transacao = transacao;
	}

	public final PreparedStatement criarPS(String instrucaoSQL) throws Exception {

		this.sqlState = "";

		try {
			PreparedStatement oPStmt = oConn.prepareStatement(instrucaoSQL);
			return oPStmt;
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}

	}

	public final CallableStatement criarCS(String StoredProcedure) throws Exception {

		this.sqlState = "";

		try {
			CallableStatement oCStmt = oConn.prepareCall(StoredProcedure);
			return oCStmt;
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	public final ResultSet consultarSQL(String instrucaoSQL) throws Exception {

		this.sqlState = "";

		try {
			Statement oStmt = oConn.createStatement();

			ResultSet oRS = oStmt.executeQuery(instrucaoSQL);

			return oRS;
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	public final ResultSet consultarSQL(PreparedStatement oPStmt) throws Exception {
		return this.consultarSQL(oPStmt, true);
	}

	public final ResultSet consultarSQL(PreparedStatement oPStmt, boolean fecharPstmt) throws Exception {
		this.sqlState = "";
		try {
			ResultSet oRS = oPStmt.executeQuery();
			return oRS;
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	public final int executarSQL(String instrucaoSQL) throws Exception {

		this.sqlState = "";

		try {
			Statement oStmt = oConn.createStatement();

			int QtdLinhasAtualizadas = oStmt.executeUpdate(instrucaoSQL);

			oStmt.close();

			return QtdLinhasAtualizadas;
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	public final int executarSQL(PreparedStatement oPStmt) throws Exception {
		return this.executarSQL(oPStmt, true);
	}

	public final int executarSQL(PreparedStatement oPStmt, boolean fecharPstmt) throws Exception {

		this.sqlState = "";

		try {
			int QtdLinhasAtualizadas = oPStmt.executeUpdate();

			if (fecharPstmt) {
				oPStmt.close();
			}

			return QtdLinhasAtualizadas;
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	public final Date obterTimeStamp() throws Exception {

		String SQL = "select current timestamp as TIMESTAMP from sysibm.sysdummy1";

		Date TimeStamp = null;

		boolean flagConexao = true;

		try {
			if (this.oConn == null) {
				this.conectar();
				flagConexao = false;
			}

			Statement oStmt = oConn.createStatement();

			ResultSet oRS = oStmt.executeQuery(SQL);

			if (oRS.next())
				TimeStamp = oRS.getTimestamp("TIMESTAMP");

			oRS.close();
			oStmt.close();

			if (flagConexao)
				this.desconectar();

			return TimeStamp;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public final String obterSQLState() {
		return this.sqlState.trim();
	}

}
