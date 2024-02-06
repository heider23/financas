package br.com.financas.controle;

import java.io.IOException;
import java.util.ArrayList;

import br.com.financas.entidade.EAcesso;
import br.com.financas.entidade.EContato;
import br.com.financas.negocio.NContato;
import br.com.financas.seguranca.Seguranca;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListaContato implements Controle {
	
	/**
	 * Atributos da classe
	 */
	private ArrayList<EContato> listaContato;
	private boolean podeCON;
	private boolean podeALT;
	private boolean podeINC;
	private boolean podeEXC;

	/**
	 * Constructor da classe de controle ListaContato
	 */
	public ListaContato() {
		this.setListaContato(null);
		this.setPodeCON(false);
		this.setPodeALT(false);
		this.setPodeINC(false);
		this.setPodeEXC(false);
	}
	
	/**
	 * @return the listaContato
	 */
	public ArrayList<EContato> getListaContato() {
		return listaContato;
	}

	/**
	 * @param listaContato the listaContato to set
	 */
	public void setListaContato(ArrayList<EContato> listaContato) {
		this.listaContato = listaContato;
	}

	/**
	 * @return the podeCON
	 */
	public boolean isPodeCON() {
		return podeCON;
	}

	/**
	 * @param podeCON the podeCON to set
	 */
	public void setPodeCON(boolean podeCON) {
		this.podeCON = podeCON;
	}

	/**
	 * @return the podeALT
	 */
	public boolean isPodeALT() {
		return podeALT;
	}

	/**
	 * @param podeALT the podeALT to set
	 */
	public void setPodeALT(boolean podeALT) {
		this.podeALT = podeALT;
	}

	/**
	 * @return the podeINC
	 */
	public boolean isPodeINC() {
		return podeINC;
	}

	/**
	 * @param podeINC the podeINC to set
	 */
	public void setPodeINC(boolean podeINC) {
		this.podeINC = podeINC;
	}

	/**
	 * @return the podeEXC
	 */
	public boolean isPodeEXC() {
		return podeEXC;
	}

	/**
	 * @param podeEXC the podeEXC to set
	 */
	public void setPodeEXC(boolean podeEXC) {
		this.podeEXC = podeEXC;
	}
	
	/**
	 * Seguran�a da tela ListaContato.
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
	 * L�gica da tela ListaContato.
	 */
	public boolean doLogic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Logar com seguran�a.
		 */
		if (!this.doSecurity(request, response)) {
			return false;
		} else {
			if (!this.podeCON) {
				return false;
			}
		}
		/*
		 * Listagem dos contatos
		 */
		try {
			NContato nContato = new NContato();
			this.setListaContato(nContato.listar());
		} catch (Exception e) {
			throw new ServletException(e);
		}
		request.setAttribute("ListaContato", this);
		return true;
		
	}
}
