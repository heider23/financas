package br.com.financas.controle;

import java.io.IOException;

import br.com.financas.entidade.EAcesso;
import br.com.financas.entidade.EContato;
import br.com.financas.negocio.NContato;
import br.com.financas.seguranca.Seguranca;
import br.com.financas.util.Data;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Contato implements Controle {
	
	/**
	 * Atributos da classe
	 */
	private String acao;
	private String msg;
	private String idContato;
	private String nome;
	private String dataNascimento;
	private String sexo;
	private String endereco;
	private String complemento;
	private String bairro;
	private String cep;
	private String telefoneFixo;
	private String telefoneCelular;
	private String email;
	private boolean podeCON;
	private boolean podeALT;
	private boolean podeINC;
	private boolean podeEXC;
	
	/**
	 * Constructor da classe de controle Contato
	 */
	public Contato() {
		this.setAcao("");
		this.setMsg("");
		this.setIdContato("");
		this.setNome("");
		this.setDataNascimento("");
		this.setSexo("M");
		this.setEndereco("");
		this.setComplemento("");
		this.setBairro("");
		this.setCep("");
		this.setTelefoneFixo("");
		this.setTelefoneCelular("");
		this.setEmail("");
		this.setPodeCON(false);
		this.setPodeALT(false);
		this.setPodeINC(false);
		this.setPodeEXC(false);
	}

	/* 
	 * M�todos getter/setter que mapeiam os 
	 * atributos da classe de controle e o arquivo Contato.jsp
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

	public String getIdContato() {
		return idContato;
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	 * Seguran�a da tela de Contatos.
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
	 * L�gica da tela de Contato.
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
			 * Implementa��o da l�gica da tela de Contato.
			 */
			
			/*
			 * Preenche os atributos da classe de controle com os dados da tela Contato.jsp, 
			 * passados como parametro e que ser�o usados dentro da classe. 
			 */
			this.setIdContato(request.getParameter("idContato")!=null ? request.getParameter("idContato"):"");
			this.setNome(request.getParameter("nome")!=null ? request.getParameter("nome"):"");
			this.setDataNascimento(request.getParameter("dataNascimento")!=null ? request.getParameter("dataNascimento"):"");
			this.setSexo(request.getParameter("sexo")!=null ? request.getParameter("sexo"):"");
			this.setEndereco(request.getParameter("endereco")!=null ? request.getParameter("endereco"):"");
			this.setComplemento(request.getParameter("complemento")!=null ? request.getParameter("complemento"):"");
			this.setBairro(request.getParameter("bairro")!=null ? request.getParameter("bairro"):"");
			this.setCep(request.getParameter("cep")!=null ? request.getParameter("cep"):"");
			this.setTelefoneFixo(request.getParameter("telefoneFixo")!=null ? request.getParameter("telefoneFixo"):"");
			this.setTelefoneCelular(request.getParameter("telefoneCelular")!=null ? request.getParameter("telefoneCelular"):"");
			this.setEmail(request.getParameter("email")!=null ? request.getParameter("email"):"");
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

			request.setAttribute("Contato", this);
			
			return true;
		}
	}

	/**
	 * Consulta os dados do contato na tabela de contato
	 * @throws ServletException 
	 */
	private void consultar() throws Exception {
		if (this.podeCON) {
			Data oData = new Data();
			EContato eContato = new EContato();
			NContato nContato = new NContato();
			eContato.setNome(this.getNome());
			eContato = nContato.consultar(eContato);
			if (eContato != null) {
				this.setIdContato(eContato.getIdContato().toString());
				this.setNome(eContato.getNome());
				this.setDataNascimento(oData.formatar(eContato.getDataNascimento(),"dd/MM/yyyy"));
				this.setSexo(String.valueOf(eContato.getSexo()));
				this.setEndereco(eContato.getEndereco());
				this.setComplemento(eContato.getComplemento());
				this.setBairro(eContato.getBairro());
				this.setCep(eContato.getCep());
				this.setTelefoneFixo(eContato.getTelefoneFixo());
				this.setTelefoneCelular(eContato.getTelefoneCelular());
				this.setEmail(eContato.getEmail());			
			} else {
				this.setMsg("Contato n�o encontrado.");
			}		
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar consultas.");
		}
	}

	private void alterar() throws Exception {
		if (this.podeALT) {
			Data oData = new Data();
			EContato eContato = new EContato();
			NContato nContato = new NContato();
			eContato.setIdContato(Integer.parseInt(this.getIdContato()));
			eContato.setNome(this.getNome());
			eContato.setDataNascimento(oData.converter(this.getDataNascimento(), "dd/MM/yyyy"));
			eContato.setSexo(this.getSexo().charAt(0));
			eContato.setEndereco(this.getEndereco());
			eContato.setComplemento(this.getComplemento());
			eContato.setBairro(this.getBairro());
			eContato.setCep(this.getCep());
			eContato.setTelefoneFixo(this.getTelefoneFixo());
			eContato.setTelefoneCelular(this.getTelefoneCelular());
			eContato.setEmail(this.getEmail());
			nContato.alterar(eContato);			
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar altera��es.");	
		}
	}
	
	private void incluir() throws Exception {
		if (this.podeINC) {
			Data oData = new Data();
			EContato eContato = new EContato();
			NContato nContato = new NContato();
			eContato.setIdContato(null);
			eContato.setNome(this.getNome());
			eContato.setDataNascimento(oData.converter(this.getDataNascimento(), "dd/MM/yyyy"));
			eContato.setSexo(this.getSexo().charAt(0));
			eContato.setEndereco(this.getEndereco());
			eContato.setComplemento(this.getComplemento());
			eContato.setBairro(this.getBairro());
			eContato.setCep(this.getCep());
			eContato.setTelefoneFixo(this.getTelefoneFixo());
			eContato.setTelefoneCelular(this.getTelefoneCelular());
			eContato.setEmail(this.getEmail());
			nContato.incluir(eContato);		
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar inclus�es.");
		}
	}
	
	private void excluir() throws Exception {
		if (this.podeEXC) {
			EContato eContato = new EContato();
			NContato nContato = new NContato();
			eContato.setIdContato(Integer.parseInt(this.getIdContato()));
			nContato.excluir(eContato);	
		} else {
			throw new Exception("Voc� n�o tem autorizacao para efetuar exclus�es.");
		}
	}	

}
