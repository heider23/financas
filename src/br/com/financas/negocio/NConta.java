package br.com.financas.negocio;

import java.util.ArrayList;

import br.com.financas.entidade.EConta;
import br.com.financas.persistencia.PConta;

public class NConta extends Negocio implements IConta {

	
	public int alterar(EConta eConta) throws Exception {
		PConta pConta = new PConta(super.oBanco);
		int registros = 0;
		if (eConta != null) {
			try {
				oBanco.conectar();
				registros = pConta.alterar(eConta);
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

	
	public EConta consultar(EConta eConta) throws Exception {
		PConta pConta = new PConta(super.oBanco);
		if (eConta != null && !eConta.getNome().equals("")) {
			try {
				oBanco.conectar();
				eConta = pConta.consultar(eConta);
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
		return eConta;
	}

	
	public int excluir(EConta eConta) throws Exception {
		PConta pConta = new PConta(super.oBanco);
		int registros = 0;
		if (eConta != null) {
			try {
				oBanco.conectar();
				registros = pConta.excluir(eConta);
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

	
	public int incluir(EConta eConta) throws Exception {
		PConta pConta = new PConta(super.oBanco);
		int registros = 0;
		if (eConta != null) {
			try {
				oBanco.conectar();
				registros = pConta.incluir(eConta);
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

	
	public ArrayList<EConta> listar() throws Exception {
		PConta pConta = new PConta(super.oBanco);
		ArrayList<EConta> lstContas = new ArrayList<EConta>();
		try {
			oBanco.conectar();
			lstContas = pConta.listar();
			oBanco.desconectar();
			return lstContas;
		} catch (Exception e) {
			try {
				oBanco.desconectar();
			} catch (Exception ee) {
				throw new Exception(e.getMessage() + " - " + ee.getMessage()) ;
			}
			throw new Exception(e.getMessage());
		}
	}
	
	public ArrayList<EConta> listar(Integer idUsuario) throws Exception {
		PConta pConta = new PConta(super.oBanco);
		ArrayList<EConta> lstContas = new ArrayList<EConta>();
		try {
			oBanco.conectar();
			lstContas = pConta.listar(idUsuario);
			oBanco.desconectar();
			return lstContas;
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
