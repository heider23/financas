package br.com.financas.controle;

import java.io.IOException;

import br.com.financas.seguranca.Seguranca;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Portal implements Controle {

	Object login;
	Object acesso;

	public boolean doLogic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Logar com seguran�a.
		 */
		if (!this.doSecurity(request, response)) {
			return false;
		}
		return true;
	}

	public boolean doSecurity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Instancia a classe Seguranca, e preenche os dados do usu�rio e acesso
		 * na sess�o.
		 */
		new Seguranca(request, response);
		/*
		 * Returna true caso objeto acesso tenha seido preenchido na instancia��o da classe
		 * Seguranca.
		 */
		if (request.getSession().getAttribute("acesso") != null) {
			return true;
		} else {
			return false;
		}
	}

}
