package br.com.financas.entidade;

import java.util.Date;

public class EErro {
	private String local;
	private String descricao;
	private Date dataHora;	
	
	public EErro() {
		super();
	}
	public EErro(String local, String descricao, Date dataHora) {
		super();
		this.local = local;
		this.descricao = descricao;
		this.dataHora = dataHora;
	}	
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}

}
