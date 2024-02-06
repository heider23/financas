package br.com.financas.controle;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Index implements Controle {

	public boolean doLogic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * N�o precisa logar para acessar o Index.jsp
		 */
		return true;
	}

	public boolean doSecurity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		return false;		
	}

}
