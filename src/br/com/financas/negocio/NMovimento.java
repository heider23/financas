package br.com.financas.negocio;

import java.util.ArrayList;

import br.com.financas.entidade.EMovimento;
import br.com.financas.persistencia.PMovimento;

public class NMovimento extends Negocio implements IMovimento {

	
	public int alterar(EMovimento eMovimento) throws Exception {
		PMovimento pMovimento = new PMovimento(super.oBanco);
		int registros = 0;
		if (eMovimento != null) {
			try {
				oBanco.conectar();
				registros = pMovimento.alterar(eMovimento);
				oBanco.desconectar();
			} catch (Exception e) {
				try {
					oBanco.desconectar();
				} catch (Exception ee) {
					throw new Exception(e.getMessage() + " - " + ee.getMessage()) ;
				}
				throw new Exception(e.getMessage());
			}
		}
		return registros;
	}

	
	public ArrayList<EMovimento> consultar(EMovimento eMovimento) throws Exception {
		PMovimento pMovimento = new PMovimento(super.oBanco);
		ArrayList<EMovimento> lstMovimento = null;
		if (eMovimento != null) {
			try {
				oBanco.conectar();
				lstMovimento = pMovimento.consultar(eMovimento);
				oBanco.desconectar();
			} catch (Exception e) {
				try {
					oBanco.desconectar();
				} catch (Exception ee) {
					throw new Exception(e.getMessage() + " - " + ee.getMessage()) ;
				}
				throw new Exception(e.getMessage());
			}
		}
		return lstMovimento;
	}

	
	public int excluir(EMovimento eMovimento) throws Exception {
		PMovimento pMovimento = new PMovimento(super.oBanco);
		int registros = 0;
		if (eMovimento != null) {
			try {
				oBanco.conectar();
				registros = pMovimento.excluir(eMovimento);
				oBanco.desconectar();
			} catch (Exception e) {
				try {
					oBanco.desconectar();
				} catch (Exception ee) {
					throw new Exception(e.getMessage() + " - " + ee.getMessage()) ;
				}
				throw new Exception(e.getMessage());
			}
		}
		return registros;
	}

	
	public int incluir(EMovimento eMovimento) throws Exception {
		PMovimento pMovimento = new PMovimento(super.oBanco);
		int registros = 0;
		if (eMovimento != null) {
			try {
				oBanco.conectar();
				registros = pMovimento.incluir(eMovimento);
				oBanco.desconectar();
			} catch (Exception e) {
				try {
					oBanco.desconectar();
				} catch (Exception ee) {
					throw new Exception(e.getMessage() + " - " + ee.getMessage()) ;
				}
				throw new Exception(e.getMessage());
			}
		}
		return registros;
	}

	
	public ArrayList<EMovimento> listar() throws Exception {
		PMovimento pMovimento = new PMovimento(super.oBanco);
		ArrayList<EMovimento> lstMovimentos = new ArrayList<EMovimento>();
		try {
			oBanco.conectar();
			lstMovimentos = pMovimento.listar();
			oBanco.desconectar();
			return lstMovimentos;
		} catch (Exception e) {
			try {
				oBanco.desconectar();
			} catch (Exception ee) {
				throw new Exception(e.getMessage() + " - " + ee.getMessage()) ;
			}
			throw new Exception(e.getMessage());
		}
	}

}
