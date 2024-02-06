package br.com.financas.negocio;

import java.util.ArrayList;

import br.com.financas.entidade.EContato;
import br.com.financas.persistencia.PContato;

public class NContato extends Negocio implements IContato {

	
	public int alterar(EContato eContato) throws Exception {
		PContato pContato = new PContato(super.oBanco);
		int registros = 0;
		if (eContato != null) {
			try {
				oBanco.conectar();
				registros = pContato.alterar(eContato);
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

	
	public EContato consultar(EContato eContato) throws Exception {
		PContato pContato = new PContato(super.oBanco);
		if (eContato != null && !eContato.getNome().equals("")) {
			try {
				oBanco.conectar();
				eContato = pContato.consultar(eContato);
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
		return eContato;
	}

	
	public int excluir(EContato eContato) throws Exception {
		PContato pContato = new PContato(super.oBanco);
		int registros = 0;
		if (eContato != null) {
			try {
				oBanco.conectar();
				registros = pContato.excluir(eContato);
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

	
	public int incluir(EContato eContato) throws Exception {
		PContato pContato = new PContato(super.oBanco);
		int registros = 0;
		if (eContato != null) {
			try {
				oBanco.conectar();
				registros = pContato.incluir(eContato);
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

	
	public ArrayList<EContato> listar() throws Exception {
		PContato pContato = new PContato(super.oBanco);
		ArrayList<EContato> lstContatos = new ArrayList<EContato>();
		try {
			oBanco.conectar();
			lstContatos = pContato.listar();
			oBanco.desconectar();
			return lstContatos;
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
