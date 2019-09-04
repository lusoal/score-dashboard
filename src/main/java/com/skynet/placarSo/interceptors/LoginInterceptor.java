package com.skynet.placarSo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Controller
public class LoginInterceptor extends HandlerInterceptorAdapter {

	//@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//
//			throws Exception {
//		// pega a sessão
//		
//		HttpSession session = request.getSession();
//		// se ainda não logou, manda para a página de login
//		if (session.getAttribute("usuarioLogado") == null) {
//			System.out.println("Nao to logado");
//			response.sendRedirect("/naologado/");
//			//return false;
//		}
//			
//		// se já logou, deixa a requisição passar e chegar no controller
//		return true;
//	}
}
