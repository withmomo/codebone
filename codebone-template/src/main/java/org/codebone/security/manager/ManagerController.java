package org.codebone.security.manager;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService service;

	@Autowired
	private MenuService menuService;

	public ModelAndView getCommonModelAndView(String target,
			Map<String, Object> map) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		ManagerModel currentLoginManager = (ManagerModel) service.read(
				Long.parseLong(auth.getName())).getData();
		List<MenuModel> list = (List<MenuModel>) menuService.listAll()
				.getData();
		System.out.println(list);
		map.put("loginManager", currentLoginManager);
		map.put("menu", list);
		return new ModelAndView(target, map);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		map.put("data", service.list(page));
		map.put("page", page);

		return getCommonModelAndView("manager/list", map);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String property = req.getParameter("property");
		String keyword = req.getParameter("keyword");
		if (page == null) {
			page = 0;
		}
		map.put("data", service.search(property, keyword, page));
		map.put("page", page);
		return getCommonModelAndView("manager/list", map);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest req, HttpServletResponse res,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isCreate", "Y");
		return getCommonModelAndView("manager/update", map);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create_POST(HttpServletRequest req,
			HttpServletResponse res, HttpSession session,
			@ModelAttribute ManagerModel managerModel) throws ParseException {
		service.create(managerModel);
		/**
		 * Create Complete
		 */
		return list(req, res, session, 0);
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, String idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", service.read(idx));
		map.put("id", idx);
		map.put("isCreate", "N");
		return getCommonModelAndView("manager/update", map);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update_POST(HttpServletRequest req,
			HttpServletResponse res, HttpSession session,
			@ModelAttribute ManagerModel managerModel) throws ParseException {
		service.update(managerModel);
		/**
		 * Update Complete
		 */
		return list(req, res, session, 0);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, String idx) {
		ManagerModel managerModel = (ManagerModel) service.read(idx).getData();
		service.delete(managerModel);

		return list(req, res, session, 0);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		return getCommonModelAndView("manager/test", map);
	}

}
