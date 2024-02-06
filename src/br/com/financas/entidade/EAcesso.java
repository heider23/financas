/**
 * 
 */
package br.com.financas.entidade;

/**
 * @author Heider-TF
 *
 */
public class EAcesso {

	private EUsuario usuario;
	private EAplicacao aplicacao;	
	private char consulta;
	private char altera;
	private char inclui;
	private char exclui;
	
	/**
	 * @return the usuario
	 */
	public EUsuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(EUsuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the aplicacao
	 */
	public EAplicacao getAplicacao() {
		return aplicacao;
	}
	/**
	 * @param aplicacao the aplicacao to set
	 */
	public void setAplicacao(EAplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}
	/**
	 * @return the consulta
	 */
	public char getConsulta() {
		return consulta;
	}
	/**
	 * @param consulta the consulta to set
	 */
	public void setConsulta(char consulta) {
		this.consulta = consulta;
	}
	/**
	 * @return the altera
	 */
	public char getAltera() {
		return altera;
	}
	/**
	 * @param altera the altera to set
	 */
	public void setAltera(char altera) {
		this.altera = altera;
	}
	/**
	 * @return the inclui
	 */
	public char getInclui() {
		return inclui;
	}
	/**
	 * @param inclui the inclui to set
	 */
	public void setInclui(char inclui) {
		this.inclui = inclui;
	}
	/**
	 * @return the exclui
	 */
	public char getExclui() {
		return exclui;
	}
	/**
	 * @param exclui the exclui to set
	 */
	public void setExclui(char exclui) {
		this.exclui = exclui;
	}
	
}
