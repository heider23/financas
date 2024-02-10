package br.com.financas.controle;

import java.io.IOException;

import br.com.financas.entidade.EUsuario;
import br.com.financas.negocio.NUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Login implements Controle {
	
	String usuario; 
	String senha;
	String msg;
	
	public Login() {
		this.setUsuario("");
		this.setSenha("");
		this.setMsg("");
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario == null ? "":usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha == null ? "":senha;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean doSecurity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return true;
	}
	
	public boolean doLogic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.setUsuario(request.getParameter("usuario")); 
		this.setSenha(request.getParameter("senha"));
		this.setMsg("");
		EUsuario eUsuario = null;
		if (!this.getUsuario().equals("")) {
			try {
				NUsuario nUsuario = new NUsuario();
				eUsuario = nUsuario.consultar(usuario); 
				if ((eUsuario == null) || (!eUsuario.getSenha().equals(senha))) {
					msg = "Usu�rio n�o cadastrado e/ou senha inv�lida.";
					eUsuario = null;
				}
			} catch (Exception e) {
				this.setMsg(e.getMessage());
				e.printStackTrace();
			}
		}
		request.setAttribute("Login", this);
		request.getSession().setAttribute("login", eUsuario);
		return true;
	}

}
