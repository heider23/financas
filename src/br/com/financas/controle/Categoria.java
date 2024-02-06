package br.com.financas.controle;

import java.io.IOException;

import br.com.financas.entidade.EAcesso;
import br.com.financas.entidade.ECategoria;
import br.com.financas.negocio.NCategoria;
import br.com.financas.seguranca.Seguranca;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Categoria implements Controle {
	
	/**
	 * Atributos da classe
	 */
	private String acao;
	private String msg;
	private String idCategoria;
	private String nomeCategoria;
	private boolean podeCON;
	private boolean podeALT;
	private boolean podeINC;
	private boolean podeEXC;
	
	/**
	 * Constructor da classe de controle Categoria
	 */
	public Categoria() {
		this.setAcao("");
		this.setMsg("");
		this.setIdCategoria("");
		this.setNomeCategoria("");
		this.setPodeCON(false);
		this.setPodeALT(false);
		this.setPodeINC(false);
		this.setPodeEXC(false);
	}

	/* 
	 * M�todos getter/setter que mapeiam os 
	 * atributos da classe de controle e o arquivo Categoria.jsp
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

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
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
	 * Seguran�a da tela de Categorias.
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
	 * L�gica da tela de Categoria.
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
			 * Implementa��o da l�gica da tela de Categoria.
			 */
			
			/*
			 * Preenche os atributos da classe de controle com os dados da tela Categoria.jsp, 
			 * passados como parametro e que ser�o usados dentro da classe. 
			 */
			this.setIdCategoria(request.getParameter("idCategoria")!=null ? request.getParameter("idCategoria"):"");
			this.setNomeCategoria(request.getParameter("nomeCategoria")!=null ? request.getParameter("nomeCategoria"):"");
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

			request.setAttribute("Categoria", this);
			
			return true;
		}
	}

	/**
	 * Consulta os dados do Categoria na tabela de Categoria
	 * @throws ServletException 
	 */
	private void consultar() throws Exception {
		if (this.podeCON) {
			ECategoria eCategoria = new ECategoria();
			NCategoria nCategoria = new NCategoria();
			if (!this.getIdCategoria().equals("")) {
				eCategoria.setIdCategoria(Integer.valueOf(this.getIdCategoria()));
			} else {
				eCategoria.setIdCategoria(null);
			}
			eCategoria.setNomeCategoria(this.getNomeCategoria());
			eCategoria = nCategoria.consultar(eCategoria);
			if (eCategoria != null) {
				this.setIdCategoria(eCategoria.getIdCategoria().toString());
				this.setNomeCategoria(eCategoria.getNomeCategoria());
			} else {
				this.setMsg("Categoria n�o encontrado.");
			}		
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar consultas.");
		}
	}

	private void alterar() throws Exception {
		if (this.podeALT) {
			ECategoria eCategoria = new ECategoria();
			NCategoria nCategoria = new NCategoria();
			eCategoria.setIdCategoria(Integer.valueOf(this.getIdCategoria()));
			eCategoria.setNomeCategoria(this.getNomeCategoria());
			nCategoria.alterar(eCategoria);			
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar altera��es.");	
		}
	}
	
	private void incluir() throws Exception {
		if (this.podeINC) {
			ECategoria eCategoria = new ECategoria();
			NCategoria nCategoria = new NCategoria();
			eCategoria.setIdCategoria(null);
			eCategoria.setNomeCategoria(this.getNomeCategoria());
			nCategoria.incluir(eCategoria);		
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar inclus�es.");
		}
	}
	
	private void excluir() throws Exception {
		if (this.podeEXC) {
			ECategoria eCategoria = new ECategoria();
			NCategoria nCategoria = new NCategoria();
			eCategoria.setIdCategoria(Integer.valueOf(this.getIdCategoria()));
			nCategoria.excluir(eCategoria);	
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar exclus�es.");
		}
	}	

}
