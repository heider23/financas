package br.com.financas.controle;

import java.io.IOException;
import java.util.ArrayList;

import br.com.financas.entidade.EAcesso;
import br.com.financas.entidade.ECompromisso;
import br.com.financas.entidade.EContato;
import br.com.financas.negocio.NCompromisso;
import br.com.financas.seguranca.Seguranca;
import br.com.financas.util.Data;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Compromisso implements Controle {
	
	/**
	 * Atributos da classe
	 */
	private String acao;
	private String msg;
	private String idCompromisso;
	private String dataHoraInicio;
	private String dataHoraFinal;	
	private String assunto;
	private String observacoes;
	private String idContato;
	private String nome;
	private ArrayList<ECompromisso> lstECompromisso;
	private boolean podeCON;
	private boolean podeALT;
	private boolean podeINC;
	private boolean podeEXC;
	
	/**
	 * Constructor da classe de controle Compromisso
	 */
	public Compromisso() {
		this.setAcao("");
		this.setMsg("");
		this.setIdCompromisso("");
		this.setAssunto("");
		this.setObservacoes("");
		this.setDataHoraInicio("");
		this.setDataHoraFinal("");
		this.setIdContato("");
		this.setNome("");
		this.setPodeCON(false);
		this.setPodeALT(false);
		this.setPodeINC(false);
		this.setPodeEXC(false);
	}
	

	/* 
	 * M�todos getter/setter que mapeiam os 
	 * atributos da classe de controle e o arquivo Compromisso.jsp
	 * 
	 */
	
	/**
	 * @return the idCompromisso
	 */
	public String getIdCompromisso() {
		return idCompromisso;
	}

	/**
	 * @param idCompromisso the idCompromisso to set
	 */
	public void setIdCompromisso(String idCompromisso) {
		this.idCompromisso = idCompromisso;
	}

	/**
	 * @return the dataHoraInicio
	 */
	public String getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * @param dataHoraInicio the dataHoraInicio to set
	 */
	public void setDataHoraInicio(String dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * @return the dataHoraFinal
	 */
	public String getDataHoraFinal() {
		return dataHoraFinal;
	}

	/**
	 * @param dataHoraFinal the dataHoraFinal to set
	 */
	public void setDataHoraFinal(String dataHoraFinal) {
		this.dataHoraFinal = dataHoraFinal;
	}

	/**
	 * @return the assunto
	 */
	public String getAssunto() {
		return assunto;
	}

	/**
	 * @param assunto the assunto to set
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	/**
	 * @return the observacoes
	 */
	public String getObservacoes() {
		return observacoes;
	}

	/**
	 * @param observacoes the observacoes to set
	 */
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public String getIdContato() {
		return idContato;
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	/**
	 * @return the lstECompromisso
	 */
	public ArrayList<ECompromisso> getLstECompromisso() {
		return lstECompromisso;
	}


	/**
	 * @param lstECompromisso the lstECompromisso to set
	 */
	public void setLstECompromisso(ArrayList<ECompromisso> lstECompromisso) {
		this.lstECompromisso = lstECompromisso;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
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
	 * Seguran�a da tela de Compromisso.
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
	 * L�gica da tela de Compromisso.
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
			 * Implementa��o da l�gica da tela de Compromisso.
			 */
			
			/*
			 * Preenche os atributos da classe de controle com os dados da tela Compromisso.jsp, 
			 * passados como parametro e que ser�o usados dentro da classe. 
			 */
			this.setIdCompromisso(request.getParameter("idCompromisso")!=null ? request.getParameter("idCompromisso"):"");
			this.setAssunto(request.getParameter("assunto")!=null ? request.getParameter("assunto"):"");
			this.setObservacoes(request.getParameter("observacoes")!=null ? request.getParameter("observacoes"):"");
			this.setDataHoraInicio(request.getParameter("dataHoraInicio")!=null ? request.getParameter("dataHoraInicio"):"");
			this.setDataHoraFinal(request.getParameter("dataHoraFinal")!=null ? request.getParameter("dataHoraFinal"):"");
			this.setIdContato(request.getParameter("idContato")!=null ? request.getParameter("idContato"):"");
			this.setNome(request.getParameter("nome")!=null ? request.getParameter("nome"):"");
			this.setAcao(request.getParameter("acao")!=null ? request.getParameter("acao"):"");
			
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

			request.setAttribute("Compromisso", this);
			
			return true;
		}
	}

	/**
	 * Consulta os dados do compromisso na tabela de compromisso
	 * @throws ServletException 
	 */
	private void consultar() throws Exception {
		if (this.podeCON) {
			Data oData = new Data();
			ECompromisso eCompromisso = new ECompromisso();
			eCompromisso.setIdCompromisso(null);
			if (!this.getAssunto().isEmpty()) {
				eCompromisso.setAssunto(this.getAssunto());
			} else {
				eCompromisso.setAssunto(null);
			}
			if (!this.getObservacoes().isEmpty()) {
				eCompromisso.setObservacoes(this.getObservacoes());
			} else {
				eCompromisso.setObservacoes(null);
			}
			if (!this.getDataHoraInicio().isEmpty()) {
				eCompromisso.setDataHoraInicio(oData.converter(this.getDataHoraInicio(), "dd/MM/yyyy hh:mm"));
			} else {
				eCompromisso.setDataHoraInicio(null);
			}
			if (!this.getDataHoraFinal().isEmpty()) {
				eCompromisso.setDataHoraFinal(oData.converter(this.getDataHoraFinal(), "dd/MM/yyyy hh:mm"));
			} else {
				eCompromisso.setDataHoraFinal(null);
			}
			if (!this.getIdContato().isEmpty()) {
				EContato eContato = new EContato();
				eContato.setIdContato(Integer.parseInt(this.getIdContato()));
				eCompromisso.setEContato(eContato);
			} else {
				eCompromisso.setEContato(null);
			}
			NCompromisso nCompromisso = new NCompromisso();
			ArrayList<ECompromisso> lstCompromisso = nCompromisso.consultar(eCompromisso);
			if (lstCompromisso != null && (!lstCompromisso.isEmpty())) {
				this.setLstECompromisso(lstCompromisso);
			} else {
				this.setMsg("Compromisso n�o encontrado.");
			}		
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar consultas.");
		}
	}

	private void alterar() throws Exception {
		if (this.podeALT) {
			Data oData = new Data();
			ECompromisso eCompromisso = new ECompromisso();
			NCompromisso nCompromisso = new NCompromisso();
			eCompromisso.setIdCompromisso(Integer.parseInt(this.getIdCompromisso()));
			eCompromisso.setAssunto(this.getAssunto());
			eCompromisso.setObservacoes(this.getObservacoes());
			eCompromisso.setDataHoraInicio(oData.converter(this.getDataHoraInicio(),"dd/MM/yyyy hh:mm"));
			eCompromisso.setDataHoraFinal(oData.converter(this.getDataHoraFinal(),"dd/MM/yyyy hh:mm"));
			EContato eContato = new EContato();
			eContato.setIdContato(Integer.parseInt(this.getIdContato()));
			eCompromisso.setEContato(eContato);
			nCompromisso.alterar(eCompromisso);
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar altera��es.");	
		}
	}
	
	private void incluir() throws Exception {
		if (this.podeINC) {
			Data oData = new Data();
			ECompromisso eCompromisso = new ECompromisso();
			NCompromisso nCompromisso = new NCompromisso();
			eCompromisso.setIdCompromisso(null);
			eCompromisso.setAssunto(this.getAssunto());
			eCompromisso.setObservacoes(this.getObservacoes());
			eCompromisso.setDataHoraInicio(oData.converter(this.getDataHoraInicio(),"dd/MM/yyyy hh:mm"));
			eCompromisso.setDataHoraFinal(oData.converter(this.getDataHoraFinal(),"dd/MM/yyyy hh:mm"));
			EContato eContato = new EContato();
			eContato.setIdContato(Integer.parseInt(this.getIdContato()));
			eCompromisso.setEContato(eContato);
			nCompromisso.incluir(eCompromisso);
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar inclus�es.");
		}
	}
	
	private void excluir() throws Exception {
		if (this.podeEXC) {
			ECompromisso eCompromisso = new ECompromisso();
			NCompromisso nCompromisso = new NCompromisso();
			eCompromisso.setIdCompromisso(Integer.parseInt(this.getIdCompromisso()));
			nCompromisso.excluir(eCompromisso);
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar exclus�es.");
		}
	}	

}
