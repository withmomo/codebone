package org.codebone.security.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codebone.security.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private ManagerService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res, HttpSession session, Integer page) {
		return new ModelAndView("auth/login");
	}
}
