/**
 * Nome :ECompromisso.java
 * Data :29/09/2008
 * Autor:heider23 - 2008
 */
package br.com.financas.entidade;

import java.util.Date;

import br.com.financas.util.Data;

/**
 * @author heider23
 *
 */
public class ECompromisso {

	private Integer idCompromisso;
	private Date dataHoraInicio;
	private Date dataHoraFinal;	
	private String assunto;
	private String observacoes;
	private EContato eContato;
	
	/**
	 * Constructor para a classe entidade.ECompromisso
	 */
	public ECompromisso() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the idCompromisso
	 */
	public Integer getIdCompromisso() {
		return idCompromisso;
	}

	/**
	 * @param idCompromisso the idCompromisso to set
	 */
	public void setIdCompromisso(Integer idCompromisso) {
		this.idCompromisso = idCompromisso;
	}

	/**
	 * @return the dataHoraInicio
	 */
	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}
	public String getDataHoraInicioFormatada() {
		try {
			return new Data().formatar(this.getDataHoraInicio(),"dd/MM/yyyy HH:mm");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param dataHoraInicio the dataHoraInicio to set
	 */
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * @return the dataHoraFinal
	 */
	public Date getDataHoraFinal() {
		return dataHoraFinal;
	}
	public String getDataHoraFinalFormatada() {
		try {
			return new Data().formatar(this.getDataHoraFinal(),"dd/MM/yyyy HH:mm");
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * @param dataHoraFinal the dataHoraFinal to set
	 */
	public void setDataHoraFinal(Date dataHoraFinal) {
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

	public EContato getEContato() {
		return eContato;
	}

	public void setEContato(EContato contato) {
		eContato = contato;
	}

}
