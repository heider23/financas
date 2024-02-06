package br.com.financas.controle;

import java.io.IOException;
import java.util.ArrayList;

import br.com.financas.entidade.EAcesso;
import br.com.financas.entidade.ECategoria;
import br.com.financas.entidade.EConta;
import br.com.financas.entidade.EMovimento;
import br.com.financas.entidade.EUsuario;
import br.com.financas.negocio.NCategoria;
import br.com.financas.negocio.NConta;
import br.com.financas.negocio.NMovimento;
import br.com.financas.seguranca.Seguranca;
import br.com.financas.util.Data;
import br.com.financas.util.Numero;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Movimento implements Controle {
	
	/**
	 * Atributos da classe
	 */
	private String acao;
	private String msg;
	private String idUsuario;
	private String idConta;
	private String idMovimento;
	private String idCategoria;
	private String dataMovimento;
	private String historicoMovimento;
	private String valorMovimento;
	private ArrayList<EConta> lstEConta;
	private ArrayList<ECategoria> lstECategoria;
	private ArrayList<EMovimento> lstEMovimento;
	private boolean podeCON;
	private boolean podeALT;
	private boolean podeINC;
	private boolean podeEXC;
	
	/**
	 * Constructor da classe de controle Movimento
	 */
	public Movimento() {
		this.setAcao("");
		this.setMsg("");
		this.setIdUsuario("");
		this.setIdConta("");
		this.setIdMovimento("");
		this.setIdCategoria("");
		this.setHistoricoMovimento("");
		this.setValorMovimento("");
		this.setPodeCON(false);
		this.setPodeALT(false);
		this.setPodeINC(false);
		this.setPodeEXC(false);
	}

	/* 
	 * M�todos getter/setter que mapeiam os 
	 * atributos da classe de controle e o arquivo Movimento.jsp
	 * 
	 */
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdConta() {
		return idConta;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(String dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public String getHistoricoMovimento() {
		return historicoMovimento;
	}

	public void setHistoricoMovimento(String historicoMovimento) {
		this.historicoMovimento = historicoMovimento;
	}

	public String getValorMovimento() {
		return valorMovimento;
	}

	public void setValorMovimento(String valorMovimento) {
		this.valorMovimento = valorMovimento;
	}

	public ArrayList<EConta> getLstEConta() {
		return lstEConta;
	}

	public void setLstEConta(ArrayList<EConta> lstEConta) {
		this.lstEConta = lstEConta;
	}

	public ArrayList<ECategoria> getLstECategoria() {
		return lstECategoria;
	}

	public void setLstECategoria(ArrayList<ECategoria> lstECategoria) {
		this.lstECategoria = lstECategoria;
	}

	public ArrayList<EMovimento> getLstEMovimento() {
		return lstEMovimento;
	}

	public void setLstEMovimento(ArrayList<EMovimento> lstEMovimento) {
		this.lstEMovimento = lstEMovimento;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getIdMovimento() {
		return idMovimento;
	}

	public void setIdMovimento(String idMovimento) {
		this.idMovimento = idMovimento;
	}

	public boolean isPodeCON() {
		return podeCON;
	}

	public void setPodeCON(boolean podeCON) {
		this.podeCON = podeCON;
	}

	public boolean isPodeALT() {
		return podeALT;
	}

	public void setPodeALT(boolean podeALT) {
		this.podeALT = podeALT;
	}

	public boolean isPodeINC() {
		return podeINC;
	}

	public void setPodeINC(boolean podeINC) {
		this.podeINC = podeINC;
	}

	public boolean isPodeEXC() {
		return podeEXC;
	}

	public void setPodeEXC(boolean podeEXC) {
		this.podeEXC = podeEXC;
	}
	
	/**
	 * Seguran�a da tela de Movimentos.
	 */
	public boolean doSecurity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Instancia a classe Seguranca, setando as permiss�es de acesso do usu�rio 
		 * na sess�o.
		 */
		new Seguranca(request, response);
		/*
		 * Returna true caso objeto acesso tenha seido preenchido na instancia��o da classe
		 * Seguranca.
		 */
		if (request.getSession().getAttribute("acesso") != null) {
			/*
			 * Setar permissoes dos botoes. 
			 */
			EAcesso eAcesso = (EAcesso) request.getSession().getAttribute("acesso");
			this.setPodeCON(eAcesso.getConsulta() == '1');
			this.setPodeALT(eAcesso.getAltera() == '1');
			this.setPodeINC(eAcesso.getInclui() == '1');
			this.setPodeEXC(eAcesso.getExclui() == '1');
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * L�gica da tela de Movimento.
	 */
	public boolean doLogic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Logar com seguran�a.
		 */
		if (!this.doSecurity(request, response)) {
			throw new ServletException("Erro nas valida��es de seguran�a.");
		} else {
			
			/*
			 * Implementa��o da l�gica da tela de Movimento.
			 */
			
			/*
			 * Preenche os atributos da classe de controle com os dados da tela Movimento.jsp, 
			 * passados como parametro e que ser�o usados dentro da classe. 
			 */
			this.setIdMovimento(request.getParameter("idMovimento")!=null ? request.getParameter("idMovimento"):"");
			this.setIdConta(request.getParameter("idConta")!=null ? request.getParameter("idConta"):"");
			this.setIdCategoria(request.getParameter("idCategoria")!=null ? request.getParameter("idCategoria"):"");
			this.setDataMovimento(request.getParameter("dataMovimento")!=null ? request.getParameter("dataMovimento"):"");
			this.setHistoricoMovimento(request.getParameter("historicoMovimento")!=null ? request.getParameter("historicoMovimento"):"");
			this.setValorMovimento(request.getParameter("valorMovimento")!=null ? request.getParameter("valorMovimento"):"");
			this.setAcao(request.getParameter("acao")!=null ? request.getParameter("acao"):"");
			/*
			 * Pega o id do usuario logado
			 */
			EUsuario eUsuario = (EUsuario) request.getSession().getAttribute("login");
			this.setIdUsuario(String.valueOf(eUsuario.getId()));
			/*
			 * Pegas as contas associadas ao usuario logado
			 */
			NConta nConta = new NConta();
			try {
				this.setLstEConta(nConta.listar(Integer.valueOf(this.getIdUsuario())));
			} catch (Exception e) {
				this.setMsg(e.getMessage());
				this.setLstEConta(null);
			}
			/*
			 * Pegas as categorias cadastradas
			 */
			NCategoria nCategoria = new NCategoria();
			try {
				this.setLstECategoria(nCategoria.listar());
			} catch (Exception e) {
				this.setMsg(e.getMessage());
				this.setLstECategoria(null);
			}
			
			/*
			 * Avalia a a��o que o usu�rio solicitou
			 */
			if (this.getAcao() != null) {
				
				try {
					
					if(this.getAcao().equals("consultar")) {
					
						this.consultar();
					
					} else if (this.getAcao().equals("alterar")) {
						
							this.alterar();
							this.setMsg("Altera��o efetuada.");
							
					} else if (this.getAcao().equals("incluir")) {
					
							this.incluir();
							this.setMsg("Inclus�o efetuada.");
					
					} else if (this.getAcao().equals("excluir")) {
						
							this.excluir();
							this.setMsg("Exclus�o efetuada.");
					}
					
				} catch (Exception e) {
					throw new ServletException(e);
				}
			}

			request.setAttribute("Movimento", this);
			
			return true;
		}
	}

	/**
	 * Consulta os dados do Movimento na tabela de Movimento
	 * @throws ServletException 
	 */
	private void consultar() throws Exception {
		if (this.podeCON) {
			Data oData = new Data();
			Numero oNumero = new Numero();
			EMovimento eMovimento = new EMovimento();
			eMovimento.setId(null);
			EUsuario eUsuario = new EUsuario();
			eUsuario.setId(Integer.valueOf(this.getIdUsuario()));
			eMovimento.setEUsuario(eUsuario);
			if (!this.getIdConta().isEmpty() && !this.getIdConta().equals("0")) {
				EConta eConta = new EConta();
				eConta.setIdConta(Integer.valueOf(this.getIdConta()));
				eMovimento.setEConta(eConta);
			} else {
				eMovimento.setEConta(null);
			}
			if (!this.getIdCategoria().isEmpty() && !this.getIdCategoria().equals("0")) {
				ECategoria eCategoria = new ECategoria();
				eCategoria.setIdCategoria(Integer.valueOf(this.getIdCategoria()));
				eMovimento.setECategoria(eCategoria);
			} else {
				eMovimento.setECategoria(null);
			}
			if (!this.getDataMovimento().isEmpty()) {
				eMovimento.setData(oData.converter(this.getDataMovimento(), "dd/MM/yyyy"));
			} else {
				eMovimento.setData(null);
			}
			if (!this.getHistoricoMovimento().isEmpty()) {
				eMovimento.setHistorico(this.getHistoricoMovimento());
			} else {
				eMovimento.setHistorico(null);
			}
			if (!this.getValorMovimento().isEmpty()) {
				eMovimento.setValor(oNumero.StrToVal(this.getValorMovimento()));
			} else {
				eMovimento.setValor(0);
			}
			NMovimento nMovimento = new NMovimento();
			ArrayList<EMovimento> lstMovimento = nMovimento.consultar(eMovimento);
			if (lstMovimento != null && (!lstMovimento.isEmpty())) {
				this.setLstEMovimento(lstMovimento);
			} else {
				this.setMsg("Movimento n�o encontrado.");
			}	
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar consultas.");
		}
	}

	private void alterar() throws Exception {
		if (this.podeALT) {
			Data oData = new Data();
			Numero oNumero = new Numero();
			EMovimento eMovimento = new EMovimento();
			NMovimento nMovimento = new NMovimento();			
			EUsuario usuario = new EUsuario();
			usuario.setId(Integer.valueOf(this.getIdUsuario()));
			eMovimento.setEUsuario(usuario);
			EConta conta = new EConta();
			conta.setIdConta(Integer.valueOf(this.getIdConta()));
			eMovimento.setEConta(conta);
			ECategoria categoria = new ECategoria();
			categoria.setIdCategoria(Integer.valueOf(this.getIdCategoria()));
			eMovimento.setECategoria(categoria);
			eMovimento.setId(Integer.valueOf(this.getIdMovimento()));
			eMovimento.setData(oData.converter(this.getDataMovimento(), "dd/MM/yyyy"));
			eMovimento.setHistorico(this.getHistoricoMovimento());
			eMovimento.setValor(oNumero.StrToVal(this.getValorMovimento()));
			nMovimento.alterar(eMovimento);	
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar altera��es.");	
		}
	}
	
	private void incluir() throws Exception {
		if (this.podeINC) {
			Data oData = new Data();
			Numero oNumero = new Numero();
			EMovimento eMovimento = new EMovimento();
			NMovimento nMovimento = new NMovimento();			
			EUsuario usuario = new EUsuario();
			usuario.setId(Integer.valueOf(this.getIdUsuario()));
			eMovimento.setEUsuario(usuario);
			EConta conta = new EConta();
			conta.setIdConta(Integer.valueOf(this.getIdConta()));
			eMovimento.setEConta(conta);
			ECategoria categoria = new ECategoria();
			categoria.setIdCategoria(Integer.valueOf(this.getIdCategoria()));
			eMovimento.setECategoria(categoria);
			eMovimento.setId(null);
			eMovimento.setData(oData.converter(this.getDataMovimento(), "dd/MM/yyyy"));
			eMovimento.setHistorico(this.getHistoricoMovimento());
			eMovimento.setValor(oNumero.StrToVal(this.getValorMovimento()));
			nMovimento.incluir(eMovimento);		
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar inclus�es.");
		}
	}
	
	private void excluir() throws Exception {
		if (this.podeEXC) {
			EMovimento eMovimento = new EMovimento();
			NMovimento nMovimento = new NMovimento();
			eMovimento.setId(Integer.valueOf(this.getIdMovimento()));
			nMovimento.excluir(eMovimento);
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar exclus�es.");
		}
	}	

}
