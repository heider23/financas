package br.com.financas.seguranca;

import java.io.IOException;

import br.com.financas.entidade.EAcesso;
import br.com.financas.entidade.EUsuario;
import br.com.financas.negocio.NAcesso;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Seguranca {

	public Seguranca(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Dados do usu�rio logado.
		 */
		Object obj = request.getSession(true).getAttribute("login");
		/*
		 * Se usu�rio nao estiver logado
		 */
		if (obj == null) {
			return;
		} else {
			if (!(obj instanceof EUsuario)) {
				this.erroSeguranca("O objeto de login � inv�lido.");
			} else {
				/*
				 * Dados do usu�rio logado.
				 */
				EUsuario oEUsuario = (EUsuario) obj;	
				/*
				 * Recurso.
				 */
				String recurso = request.getRequestURI();
				/*
				 * Consulta permissoes de acesso do usu�rio.
				 */
				EAcesso oEAcesso = null;
				try {
					NAcesso oNAcesso = new NAcesso();
					oEAcesso = oNAcesso.consultar(oEUsuario.getId(),recurso);
					if (oEAcesso == null) {
						throw new Exception("O usuário "+oEUsuario.getApelido()+", não tem acesso a " + recurso);
					}
				} catch (Exception e) {
					this.erroSeguranca(e.getMessage());
				}
				/*
				 * Guarda na sess�o as informa��es das permiss�es de acesso do usu�rio.
				 */
				request.getSession().setAttribute("acesso", oEAcesso);
			}
		}
	}

	private void erroSeguranca(String log) throws ServletException {
		throw new ServletException(log);
	}

}
