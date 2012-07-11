package org.codebone.security.group;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codebone.framework.generic.AbstractController;
import org.codebone.framework.generic.AbstractService;
import org.codebone.security.menu.MenuModel;
import org.codebone.security.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/group")
public class GroupController extends AbstractController<MenuModel>{

	@Autowired
	private GroupService service;
	
	@Override
	public AbstractService getService() {
		return service;
	}

	@Override
	protected String getContextName() {
		return "group";
	}
}
