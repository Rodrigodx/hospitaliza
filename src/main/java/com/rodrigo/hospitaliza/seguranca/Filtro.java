package com.rodrigo.hospitaliza.seguranca;

import java.io.IOException;

import com.rodrigo.hospitaliza.beans.UsuarioLogadoBean;

import jakarta.inject.Inject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.Filter;

@WebFilter(urlPatterns = { "/triagem.xhtml", "/atendimento.xhtml" })
public class Filtro implements Filter {

	@Inject
	private UsuarioLogadoBean usuarioLogado;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();

		if (!usuarioLogado.isLogado()) {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
			return;
		}
		
		if (uri.contains("/triagem.xhtml") && !usuarioLogado.HasRole("ENFERMEIRO")) {
            res.sendRedirect(req.getContextPath() + "/acesso-negado.xhtml");
            return;
        }

		chain.doFilter(request, response);
	}
}