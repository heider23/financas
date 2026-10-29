/**
 * 
 */
package br.com.financas.entidade;

/**
 * @author Heider-TF
 *
 */
public class EAcesso {

	private Integer idUsuario;
	private String nomeRecurso;	
	private char consulta;
	private char altera;
	private char inclui;
	private char exclui;
	
	/**
	 * @return the usuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the nomeRecurso
	 */
	public String getNomeRecurso() {
		return nomeRecurso;
	}
	/**
	 * @param nomeRecurso the nomeRecurso to set
	 */
	public void setNomeRecurso(String nomeRecurso) {
		this.nomeRecurso = nomeRecurso;
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
