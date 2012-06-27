package org.codebone.security.menu;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codebone.security.manager.ManagerModel;
import org.codebone.security.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private MenuService menuService;
	
	public ModelAndView getCommonModelAndView(String target, Map<String, Object> map){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ManagerModel currentLoginManager = (ManagerModel) managerService.read(Long.parseLong(auth.getName())).getData();
		List<MenuModel> list = (List<MenuModel>) menuService.listAll().getData();
		System.out.println(list);
		map.put("loginManager", currentLoginManager);
		map.put("menu", list);
		return new ModelAndView(target, map);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res, HttpSession session, Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(page==null){
			page=1;
		}
		map.put("data", menuService.list(page));
		map.put("page", page);
		
		return getCommonModelAndView("menu/list", map);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest req, HttpServletResponse res, HttpSession session, Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String property = req.getParameter("property");
		String keyword = req.getParameter("keyword");
		if(page==null){
			page=0;
		}
		map.put("data", menuService.search(property, keyword, page));
		map.put("page", page);
		return getCommonModelAndView("menu/list", map);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		return getCommonModelAndView("menu/create", map);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create_POST(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ParseException {
		MenuModel menuModel = new MenuModel();
		menuModel.setName(req.getParameter("name"));
		menuModel.setUrl(req.getParameter("url"));
		menuModel.setUrl(req.getParameter("url"));
		menuModel.setPri_order(Integer.parseInt(req.getParameter("pri_order")));
		menuModel.setSub_order(Integer.parseInt(req.getParameter("sub_order")));
		menuModel.setCreatedDate(new Date());
		menuService.create(menuModel);
		/**
		 * Create Complete
		 */
		return list(req, res, session,0);
	}
	
/*	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res, HttpSession session, String idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", managerService.read(idx));
		map.put("id", idx);
		return getCommonModelAndView("manager/update", map);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update_POST(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ParseException {
		ManagerModel managerModel = (ManagerModel) managerService.read(req.getParameter("idx")).getData();
		managerModel.setName(req.getParameter("name"));
		managerModel.setId(req.getParameter("id"));
		managerModel.setEmail(req.getParameter("email"));
		if(req.getParameter("password")!=null){
			managerModel.setPassword(req.getParameter("password"));
		}
		managerModel.setPhoneNumber(req.getParameter("phoneNumber"));
		managerModel.setLevel(Integer.parseInt(req.getParameter("level")));
		managerService.update(managerModel);
		*//**
		 * Update Complete
		 *//*
		return list(req, res, session,0);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res, HttpSession session, String idx) {
		ManagerModel managerModel = (ManagerModel) managerService.read(idx).getData();
		managerService.delete(managerModel);
		
		return list(req, res, session,0);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		return getCommonModelAndView("manager/test", map);
	}*/

}
