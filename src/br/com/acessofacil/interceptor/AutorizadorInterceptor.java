package br.com.acessofacil.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object controller) throws Exception {
		String uri = request.getRequestURI();
		if (	   uri.endsWith("index") || uri.endsWith("cadastro")
				|| uri.endsWith("login") || uri.endsWith("loginEstabelecimento")
				|| uri.endsWith("novoUsuario") || uri.endsWith("fazerLogin")
				|| uri.endsWith("admin") || uri.endsWith("fazerLoginAdmin")
				|| uri.endsWith("usuarioInvalido") || uri.endsWith("loginEstab")
				|| uri.contains("css") || uri.contains("js") || uri.contains("fonts") || uri.contains("estabelecimento") || uri.contains("getAdress")
				|| uri.contains("image") || uri.contains("jpg") || uri.contains("JPG") || uri.contains("")){
			return true;
		}
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}
		response.sendRedirect("login");
		return false;
	}
}
