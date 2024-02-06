package br.com.financas.negocio;

import br.com.financas.Financas;
import br.com.financas.io.Banco;

public abstract class Negocio extends Financas {
  
	Banco oBanco;
	
	public Negocio() {
		if (this.oBanco == null) {
			oBanco = new Banco();
		}
	}
	
}
