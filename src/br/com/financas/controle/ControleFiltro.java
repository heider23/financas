package br.com.financas.controle;

import java.io.IOException;
import java.util.Date;

import org.apache.jasper.JasperException;

import br.com.financas.entidade.EErro;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControleFiltro implements Filter {
	
	protected FilterConfig config = null;
	protected String nomeClasse = new String("Index");
	protected EErro eErro = new EErro();

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		System.out.println("DADOS DO FILTRO");
		System.out.println("Nome     : "+ this.config.getFilterName());
		System.out.println("Contexto : " + this.config.getServletContext().getServletContextName());
	}
	
	public void doFilter(ServletRequest req, 
			ServletResponse res, FilterChain chain) 
		throws IOException, ServletException {
		
		if (!(req instanceof HttpServletRequest)) {
			throw new ServletException("O filtro requer uma requisi��o HTTP.");
		}
		
		boolean doFilter = true;
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String uri= request.getRequestURI();
		int iniciar = uri.lastIndexOf("/")+1;
		int parar = uri.lastIndexOf(".");
		int javascript = uri.indexOf(".js");
		int jsp = uri.indexOf(".jsp");
		int css = uri.indexOf(".css");
		int jpg = uri.indexOf(".jpg");
		int gif = uri.indexOf(".gif");
		// Nao filtrar recursos jsvascript, css, jpg, gif
		if ((jsp < 0 && javascript > 0) || css > 0 || jpg > 0 || gif > 0) {
			chain.doFilter(request, response);
			return;
		}
		/*
		 * Tenta pegar o nome da classe de controle associada 
		 * ao recurso solicitado.
		 */
		if (iniciar < parar) {
				nomeClasse = uri.substring(iniciar,parar);
		} else {
			if (parar < 0) {
				nomeClasse = uri.substring(iniciar).isEmpty() ? nomeClasse: uri.substring(iniciar);
			}
		}
		/*
		 * Tenta carregar e executar a classe de controle assoaciada
		 * ao recurso solicitado. 
		 */
		try {
			Object obj = Class.forName("br.com.financas.controle." + nomeClasse).newInstance();
			if (!(obj instanceof Controle)) {
				throw new ServletException("A classe de controle n�o foi implementada para o recurso : " + nomeClasse);
			}
			Controle controle = (Controle) obj;
			doFilter = controle.doLogic(request, response);
			if (doFilter) {
				/*
				 * Se esta vindo da tela de login e o objeto login nao for nulo.
				 */
				if((uri.indexOf("Login")>0) && request.getSession().getAttribute("login")!=null) {
					response.sendRedirect("/financas/jsp/Portal.jsp");
					//RequestDispatcher rd = request.getRequestDispatcher("/jsp/Portal.jsp");
					//rd.forward(request, response);
				} else {
					/*
					 * Efetuou login anteriormente
					 */
					chain.doFilter(request, response);
				}
			} else {
				/*
				 * Nao efetuou login anteriormente
				 */
				response.sendRedirect("/financas/jsp/Login.jsp");
				//RequestDispatcher rd = request.getRequestDispatcher("/jsp/Login.jsp");
				//rd.forward(request, response);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			this.tratamentoErro(e);
			request.getSession(true).setAttribute("eErro", eErro);
			response.sendRedirect("/financas/jsp/ErrorPage.jsp");
			//RequestDispatcher rd = request.getRequestDispatcher("/jsp/ErrorPage.jsp");
			//rd.forward(request, response);
		} 
	}
	
	public void destroy() {
		
	}
	
	private void tratamentoErro(Object erro) {
		String local = getClass().getCanonicalName();
		String descricao = "N/D";
		Date dataHora = new Date();
		if (erro instanceof ClassNotFoundException) {
			ClassNotFoundException cnfe = (ClassNotFoundException) erro;
			descricao = cnfe.getMessage() + " - " + cnfe.getClass().getSimpleName();
		} else if (erro instanceof InstantiationException) {
			InstantiationException ie = (InstantiationException) erro;
			descricao = ie.getMessage() + " - " + ie.getClass().getSimpleName();
		} else if (erro instanceof IllegalAccessException) {
			IllegalAccessException iae = (IllegalAccessException) erro;
			descricao = iae.getMessage() + " - " + iae.getClass().getSimpleName();
		} else if (erro instanceof JasperException) {
			JasperException je = (JasperException) erro;
			descricao = je.getMessage() + " - " + je.getClass().getSimpleName();
		} else if (erro instanceof Exception) {
			Exception e = (Exception) erro;
			descricao = e.getMessage() + " - " + e.getClass().getSimpleName();
			e.printStackTrace();
		} 
		eErro.setLocal(local);
		eErro.setDescricao(descricao);
		eErro.setDataHora(dataHora);		
	}
}
