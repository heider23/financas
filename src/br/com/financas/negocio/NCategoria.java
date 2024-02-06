package br.com.financas.negocio;

import java.util.ArrayList;

import br.com.financas.entidade.ECategoria;
import br.com.financas.persistencia.PCategoria;

public class NCategoria extends Negocio implements ICategoria {

	
	public int alterar(ECategoria eCategoria) throws Exception {
		PCategoria pCategoria = new PCategoria(super.oBanco);
		int registros = 0;
		if (eCategoria != null) {
			try {
				oBanco.conectar();
				registros = pCategoria.alterar(eCategoria);
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

	
	public ECategoria consultar(ECategoria eCategoria) throws Exception {
		PCategoria pCategoria = new PCategoria(super.oBanco);
		if (eCategoria != null) {
			try {
				oBanco.conectar();
				eCategoria = pCategoria.consultar(eCategoria);
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
		return eCategoria;
	}

	
	public int excluir(ECategoria eCategoria) throws Exception {
		PCategoria pCategoria = new PCategoria(super.oBanco);
		int registros = 0;
		if (eCategoria != null) {
			try {
				oBanco.conectar();
				registros = pCategoria.excluir(eCategoria);
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

	
	public int incluir(ECategoria eCategoria) throws Exception {
		PCategoria pCategoria = new PCategoria(super.oBanco);
		int registros = 0;
		if (eCategoria != null) {
			try {
				oBanco.conectar();
				registros = pCategoria.incluir(eCategoria);
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

	
	public ArrayList<ECategoria> listar() throws Exception {
		PCategoria pCategoria = new PCategoria(super.oBanco);
		ArrayList<ECategoria> lstCategorias = new ArrayList<ECategoria>();
		try {
			oBanco.conectar();
			lstCategorias = pCategoria.listar();
			oBanco.desconectar();
			return lstCategorias;
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
