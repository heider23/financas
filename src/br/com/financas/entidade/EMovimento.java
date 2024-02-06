/**
 * Nome :EMovimento.java
 * Data :29/09/2008
 * Autor:heider23 - 2008
 */
package br.com.financas.entidade;

import java.util.Date;

import br.com.financas.util.Data;
import br.com.financas.util.Numero;

/**
 * @author heider23
 *
 */
public class EMovimento {
	
	private Integer id;
	private Date data;
	private double valor;
	private String historico;
	private ECategoria eCategoria;
	private EConta eConta;
	private EUsuario eUsuario;

	/**
	 * Constructor para a classe entidade.EMovimento
	 */
	public EMovimento() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}
	
	public String getDataFormatada() {
		try {
			return new Data().formatar(this.getData(),"dd/MM/yyyy");
		} catch (Exception e) {
			return null;
		}
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getValorFormatado() {
		return new Numero().ValToStrFmt(this.getValor(),2);
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public ECategoria getECategoria() {
		return eCategoria;
	}

	public void setECategoria(ECategoria categoria) {
		eCategoria = categoria;
	}

	public EConta getEConta() {
		return eConta;
	}

	public void setEConta(EConta conta) {
		eConta = conta;
	}

	public EUsuario getEUsuario() {
		return eUsuario;
	}

	public void setEUsuario(EUsuario usuario) {
		eUsuario = usuario;
	}

	
}
