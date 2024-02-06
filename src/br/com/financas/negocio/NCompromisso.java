package br.com.financas.negocio;

import java.util.ArrayList;

import br.com.financas.entidade.ECompromisso;
import br.com.financas.persistencia.PCompromisso;

public class NCompromisso extends Negocio implements ICompromisso {

	
	public int alterar(ECompromisso eCompromisso) throws Exception {
		PCompromisso pCompromisso = new PCompromisso(super.oBanco);
		int registros = 0;
		if (eCompromisso != null) {
			try {
				oBanco.conectar();
				registros = pCompromisso.alterar(eCompromisso);
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

	
	public ArrayList<ECompromisso> consultar(ECompromisso eCompromisso) throws Exception {
		PCompromisso pCompromisso = new PCompromisso(super.oBanco);
		ArrayList<ECompromisso> lstCompromissos = null;
		try {
			oBanco.conectar();
			lstCompromissos = pCompromisso.consultar(eCompromisso);
			oBanco.desconectar();
		} catch (Exception e) {
			try {
				oBanco.desconectar();
			} catch (Exception ee) {
				throw new Exception(e.getMessage() + " - " + ee.getMessage()) ;
			}
			throw new Exception(e.getMessage());
		}
		return lstCompromissos;		
	}

	
	public int excluir(ECompromisso eCompromisso) throws Exception {
		PCompromisso pCompromisso = new PCompromisso(super.oBanco);
		int registros = 0;
		if (eCompromisso != null) {
			try {
				oBanco.conectar();
				registros = pCompromisso.excluir(eCompromisso);
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

	
	public int incluir(ECompromisso eCompromisso) throws Exception {
		PCompromisso pCompromisso = new PCompromisso(super.oBanco);
		int registros = 0;
		if (eCompromisso != null) {
			try {
				oBanco.conectar();
				registros = pCompromisso.incluir(eCompromisso);
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

	
	public ArrayList<ECompromisso> listar() throws Exception {
		PCompromisso pCompromisso = new PCompromisso(super.oBanco);
		ArrayList<ECompromisso> lstCompromissos = new ArrayList<ECompromisso>();
		try {
			oBanco.conectar();
			lstCompromissos = pCompromisso.listar();
			oBanco.desconectar();
			return lstCompromissos;
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
