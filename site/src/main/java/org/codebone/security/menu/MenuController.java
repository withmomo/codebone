package org.codebone.security.menu;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codebone.framework.generic.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/menu")
@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MENU_ADMIN')")
public class MenuController extends AbstractController<Object> {

	@Autowired
	private MenuService service;

	protected String getContextName() {
		return "menu";
	}

	@RequestMapping(value = "/changeOrder", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MENU_UPDATE')")
	public View changeOrder(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer priOrder, Integer subOrder, String up) {
		boolean isUp;
		if (up.equals("Y")) {
			isUp = true;
		} else {
			isUp = false;
		}
		service.changeOrder(priOrder, subOrder, isUp);
		List<Menu> menuList = (List<Menu>) menuService.listAll().getData();
		session.setAttribute("menuList", menuList);
		return new RedirectView("");
	}

	@RequestMapping(value = "/changeLevel", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MENU_UPDATE')")
	public View chageLevel(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer priOrder, Integer subOrder, Long idx) {
		service.changeLevel(priOrder, subOrder, idx);
		List<Menu> menuList = (List<Menu>) menuService.listAll().getData();
		session.setAttribute("menuList", menuList);
		return new RedirectView("");
	}

	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MENU_READ')")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		map.put("data", service.list(page));
		map.put("page", page);

		return getCommonModelAndView(getContextName() + "/list", map, session);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MENU_READ')")
	public ModelAndView search(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String property = req.getParameter("property");
		String keyword = req.getParameter("keyword");
		if (page == null) {
			page = 0;
		}
		map.put("data",service.search(property, keyword, page));
		map.put("page", page);
		return getCommonModelAndView(getContextName() + "/list", map, session);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MENU_CREATE')")
	public ModelAndView create(HttpServletRequest req, HttpServletResponse res,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isCreate", "Y");
		return getCommonModelAndView(getContextName() + "/write", map, session);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MENU_CREATE')")
	public RedirectView create_POST(HttpServletRequest req,
			HttpServletResponse res, HttpSession session,
			@ModelAttribute Menu model) throws ParseException {
		service.create(model);
		/**
		 * Create Complete
		 */
		List<Menu> menuList = (List<Menu>) menuService.listAll().getData();
		session.setAttribute("menuList", menuList);
		return new RedirectView("");
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MENU_UPDATE')")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, String idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", service.read(idx));
		map.put("id", idx);
		map.put("isCreate", "N");
		return getCommonModelAndView(getContextName() + "/write", map, session);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MENU_UPDATE')")
	public RedirectView update_POST(HttpServletRequest req,
			HttpServletResponse res, HttpSession session,
			@ModelAttribute Menu managerModel) throws ParseException {
		service.update(managerModel);
		/**
		 * Update Complete
		 */
		List<Menu> menuList = (List<Menu>) menuService.listAll().getData();
		session.setAttribute("menuList", menuList);
		return new RedirectView("");
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MENU_DELETE')")
	public RedirectView delete(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, String idx) {
		service.deleteFamily(idx);
		List<Menu> menuList = (List<Menu>) menuService.listAll().getData();
		session.setAttribute("menuList", menuList);
		return new RedirectView("");
	}
}
