package org.codebone.framework.generic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.codebone.security.manager.Manager;
import org.codebone.security.manager.ManagerService;
import org.codebone.security.menu.Menu;
import org.codebone.security.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

@Controller
public abstract class AbstractController<M> {

	@Autowired
	protected ManagerService managerService;

	@Autowired
	protected MenuService menuService;

	protected abstract String getContextName();

	public ModelAndView getCommonModelAndView(String target,
			Map<String, Object> map, HttpSession session) {
		SecurityContext value = (SecurityContext) session
				.getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication authentication = value.getAuthentication();
		Manager currentLoginManager = (Manager) managerService.read(
				authentication.getName()).getData();
		List<Menu> menuList = null;
		if (session.getAttribute("menuList") == null) {
			menuList = (List<Menu>) menuService.listAll().getData();
			session.setAttribute("menuList", menuList);
		} else {
			menuList = (List<Menu>) session.getAttribute("menuList");
		}
		map.put("loginManager", currentLoginManager);
		map.put("menu", menuList);
		return new ModelAndView(target, map);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
