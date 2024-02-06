package br.com.financas.controle;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

interface Controle {
	
	public boolean doLogic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
	
	public boolean doSecurity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
	
}