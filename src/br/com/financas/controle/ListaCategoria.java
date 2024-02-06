package br.com.financas.controle;

import java.io.IOException;
import java.util.ArrayList;

import br.com.financas.entidade.EAcesso;
import br.com.financas.entidade.ECategoria;
import br.com.financas.negocio.NCategoria;
import br.com.financas.seguranca.Seguranca;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListaCategoria implements Controle {
	
	/**
	 * Atributos da classe
	 */
	private ArrayList<ECategoria> listaCategoria;
	private boolean podeCON;
	private boolean podeALT;
	private boolean podeINC;
	private boolean podeEXC;

	/**
	 * Constructor da classe de controle ListaCategoria
	 */
	public ListaCategoria() {
		this.setListaCategoria(null);
		this.setPodeCON(false);
		this.setPodeALT(false);
		this.setPodeINC(false);
		this.setPodeEXC(false);
	}
	
	/**
	 * @return the listaCategoria
	 */
	public ArrayList<ECategoria> getListaCategoria() {
		return listaCategoria;
	}

	/**
	 * @param listaCategoria the listaCategoria to set
	 */
	public void setListaCategoria(ArrayList<ECategoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
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
	 * Seguran�a da tela ListaCategoria.
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
	 * L�gica da tela ListaCategoria.
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
		 * Listagem dos Categorias
		 */
		try {
			NCategoria nCategoria = new NCategoria();
			this.setListaCategoria(nCategoria.listar());
		} catch (Exception e) {
			throw new ServletException(e);
		}
		request.setAttribute("ListaCategoria", this);
		return true;
		
	}
}
