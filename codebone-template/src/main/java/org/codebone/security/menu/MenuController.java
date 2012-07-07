package org.codebone.security.menu;

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
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/menu")
public class MenuController extends AbstractController<MenuModel>{

	@Autowired
	private MenuService service;
	
	@Override
	public AbstractService getService() {
		return service;
	}

	@Override
	protected String getContextName() {
		return "menu";
	}
	
	@Override
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, String idx) {
		((MenuService) getService()).deleteFamily(idx);
		return list(req, res, session, 0);
	}
	
	@RequestMapping(value = "/changeOrder", method = RequestMethod.GET)
	public View changeOrder(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer priOrder, Integer subOrder, String up) {
		boolean isUp, isFirst;
		if(up.equals("Y")){
			isUp = true;
		}else{
			isUp = false;
		}
		((MenuService) getService()).changeOrder(priOrder, subOrder, isUp);
		return new RedirectView("list");
	}
	
	@RequestMapping(value = "/changeLevel", method = RequestMethod.GET)
	public View chageLevel(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer priOrder, Integer subOrder, Long idx) {
		((MenuService) getService()).changeLevel(priOrder, subOrder, idx);
		return new RedirectView("list");
	}
}
