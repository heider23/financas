package br.com.financas.negocio;

import java.util.List;

import br.com.financas.entidade.EUsuario;
import br.com.financas.persistencia.PUsuario;

public class NUsuario extends Negocio implements IUsuario {	
	
	public EUsuario consultar(String usuario) throws Exception {
		EUsuario eUsuario = null;
		if (usuario != null && !usuario.equals("")) {
			PUsuario pUsuario = new PUsuario(super.oBanco);
			try {
				super.oBanco.conectar();
				eUsuario = pUsuario.consultar(usuario);
				super.oBanco.desconectar();
			} catch (Exception e) {
				try {
					if (super.oBanco != null) {
						super.oBanco.desconectar();
					}
				} catch (Exception ee) {
					throw new Exception (e.getMessage() + ":" + ee.getMessage());
				}
				throw new Exception (e.getMessage());
			}
		}
		return eUsuario;
	}

	public EUsuario consultar(EUsuario eUsuario) throws Exception {
		if (eUsuario != null) {
			PUsuario pUsuario = new PUsuario(super.oBanco);
			try {
				super.oBanco.conectar();
				eUsuario = pUsuario.consultar(eUsuario);
				super.oBanco.desconectar();
			} catch (Exception e) {
				try {
					if (super.oBanco != null) {
						super.oBanco.desconectar();
					}
				} catch (Exception ee) {
					throw new Exception (e.getMessage() + ":" + ee.getMessage());
				}
				throw new Exception (e.getMessage());
			}
		}
		return eUsuario;
	}
	public int alterar(EUsuario usuario) throws Exception {
		PUsuario pUsuario = new PUsuario(super.oBanco);
		int registros = 0;
		if (usuario != null) {
			try {
				super.oBanco.conectar();
				registros =  pUsuario.alterar(usuario);
				super.oBanco.desconectar();
			} catch (Exception e) {
				try {
					if (super.oBanco != null) {
						super.oBanco.desconectar();
					}
				} catch (Exception ee) {
					throw new Exception (e.getMessage() + ":" + ee.getMessage());
				}
				throw new Exception (e.getMessage());
			}
		}
		return registros;
	}

	public int incluir(EUsuario usuario) throws Exception {
		PUsuario pUsuario = new PUsuario(super.oBanco);
		int registros = 0;
		if (usuario != null) {
			try {
				super.oBanco.conectar();
				registros =  pUsuario.incluir(usuario);
				super.oBanco.desconectar();
			} catch (Exception e) {
				try {
					if (super.oBanco != null) {
						super.oBanco.desconectar();
					}
				} catch (Exception ee) {
					throw new Exception (e.getMessage() + ":" + ee.getMessage());
				}
				throw new Exception (e.getMessage());
			}
		}
		return registros;
	}

	public int excluir(EUsuario usuario) throws Exception {
		PUsuario pUsuario = new PUsuario(super.oBanco);
		int registros = 0;
		if (usuario != null) {
			try {
				super.oBanco.conectar();
				registros =  pUsuario.excluir(usuario);
				super.oBanco.desconectar();
			} catch (Exception e) {
				try {
					if (super.oBanco != null) {
						super.oBanco.desconectar();
					}
				} catch (Exception ee) {
					throw new Exception (e.getMessage() + ":" + ee.getMessage());
				}
				throw new Exception (e.getMessage());
			}
		}
		return registros;
	}
	
	public List<EUsuario> listar(EUsuario usuario) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EUsuario> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
