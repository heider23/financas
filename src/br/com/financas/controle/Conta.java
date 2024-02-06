package br.com.financas.controle;

import java.io.IOException;

import br.com.financas.entidade.EAcesso;
import br.com.financas.entidade.EConta;
import br.com.financas.entidade.EUsuario;
import br.com.financas.negocio.NConta;
import br.com.financas.seguranca.Seguranca;
import br.com.financas.util.Data;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Conta implements Controle {
	
	/**
	 * Atributos da classe
	 */
	private String acao;
	private String msg;
	private String idConta;
	private String idUsuario;
	private String nomeConta;
	private String descricao;
	private String dataCriacao;
	private boolean podeCON;
	private boolean podeALT;
	private boolean podeINC;
	private boolean podeEXC;
	
	/**
	 * Constructor da classe de controle Conta
	 */
	public Conta() {
		this.setAcao("");
		this.setMsg("");
		this.setIdConta("");
		this.setNomeConta("");
		this.setDescricao("");
		this.setDataCriacao("");
		this.setPodeCON(false);
		this.setPodeALT(false);
		this.setPodeINC(false);
		this.setPodeEXC(false);
	}

	/* 
	 * M�todos getter/setter que mapeiam os 
	 * atributos da classe de controle e o arquivo Conta.jsp
	 * 
	 */
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

	public String getIdConta() {
		return idConta;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
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
	 * Seguran�a da tela de Contas.
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
	 * L�gica da tela de Conta.
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
			 * Implementa��o da l�gica da tela de Conta.
			 */
			
			/*
			 * Preenche os atributos da classe de controle com os dados da tela Conta.jsp, 
			 * passados como parametro e que ser�o usados dentro da classe. 
			 */
			this.setIdConta(request.getParameter("idConta")!=null ? request.getParameter("idConta"):"");
			this.setNomeConta(request.getParameter("nomeConta")!=null ? request.getParameter("nomeConta"):"");
			this.setDescricao(request.getParameter("descricao")!=null ? request.getParameter("descricao"):"");
			this.setDataCriacao(request.getParameter("dataCriacao")!=null ? request.getParameter("dataCriacao"):"");
			this.setAcao(request.getParameter("acao")!=null ? request.getParameter("acao"):"");
			/*
			 * Pega o id do usuario logado
			 */
			EUsuario eUsuario = (EUsuario) request.getSession().getAttribute("login");
			this.setIdUsuario(String.valueOf(eUsuario.getId()));
			
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

			request.setAttribute("Conta", this);
			
			return true;
		}
	}

	/**
	 * Consulta os dados do Conta na tabela de Conta
	 * @throws ServletException 
	 */
	private void consultar() throws Exception {
		if (this.podeCON) {
			EConta eConta = new EConta();
			NConta nConta = new NConta();
			eConta.setNome(this.getNomeConta());
			eConta = nConta.consultar(eConta);
			if (eConta != null) {
				Data oData = new Data();
				this.setIdConta(eConta.getIdConta().toString());
				this.setIdUsuario(eConta.getEUsuario().getId().toString());
				this.setNomeConta(eConta.getNome());
				this.setDescricao(eConta.getDescricao());
				this.setDataCriacao(oData.formatar(eConta.getDataCriacao(), "dd/MM/yyyy"));
			} else {
				this.setMsg("Conta n�o encontrado.");
			}		
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar consultas.");
		}
	}

	private void alterar() throws Exception {
		if (this.podeALT) {
			Data oData = new Data();
			EConta eConta = new EConta();
			NConta nConta = new NConta();
			eConta.setIdConta(Integer.parseInt(this.getIdConta()));
			EUsuario eUsuario = new EUsuario();
			eUsuario.setId(Integer.parseInt(this.getIdUsuario()));
			eConta.setEUsuario(eUsuario);
			eConta.setNome(this.getNomeConta());
			eConta.setDescricao(this.getDescricao());
			eConta.setDataCriacao(oData.converter(this.getDataCriacao(),"dd/MM/yyyy"));
			nConta.alterar(eConta);			
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar altera��es.");	
		}
	}
	
	private void incluir() throws Exception {
		if (this.podeINC) {
			Data oData = new Data();
			EConta eConta = new EConta();
			NConta nConta = new NConta();
			eConta.setIdConta(null);
			EUsuario eUsuario = new EUsuario();
			eUsuario.setId(Integer.parseInt(this.getIdUsuario()));
			eConta.setEUsuario(eUsuario);
			eConta.setNome(this.getNomeConta());
			eConta.setDescricao(this.getDescricao());
			eConta.setDataCriacao(oData.converter(this.getDataCriacao(),"dd/MM/yyyy"));
			nConta.incluir(eConta);		
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar inclus�es.");
		}
	}
	
	private void excluir() throws Exception {
		if (this.podeEXC) {
			EConta eConta = new EConta();
			NConta nConta = new NConta();
			eConta.setIdConta(Integer.parseInt(this.getIdConta()));
			nConta.excluir(eConta);	
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar exclus�es.");
		}
	}	

}
