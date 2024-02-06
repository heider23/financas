/**
 * Nome :EConta.java
 * Data :29/09/2008
 * Autor:heider23 - 2008
 */
package br.com.financas.entidade;

import java.util.Date;


/**
 * @author heider23
 *
 */
public class EConta {
	
	private Integer idConta;
	private EUsuario eUsuario;
	private String nome;
	private String descricao;
	private Date dataCriacao;
	
	public Integer getIdConta() {
		return idConta;
	}
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	public EUsuario getEUsuario() {
		return eUsuario;
	}
	public void setEUsuario(EUsuario usuario) {
		eUsuario = usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}	
	
}
