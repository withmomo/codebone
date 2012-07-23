package org.codebone.security.group;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codebone.framework.BaseModel;
import org.codebone.framework.generic.AbstractController;
import org.codebone.framework.generic.AbstractService;
import org.codebone.security.manager.ManagerModel;
import org.codebone.security.manager.ManagerService;
import org.codebone.security.menu.MenuModel;
import org.codebone.security.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private GroupService service;
	
	@Autowired
	protected ManagerService managerService;

	@Autowired
	protected MenuService menuService;
	
	public AbstractService getService() {
		return service;
	}
	
	protected String getContextName() {
		return "group";
	}
	
	public ModelAndView getCommonModelAndView(String target,
			Map<String, Object> map) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		ManagerModel currentLoginManager = (ManagerModel) managerService.read(
				auth.getName()).getData();
		List<MenuModel> list = (List<MenuModel>) menuService.listAll()
				.getData();
		System.out.println(list);
		map.put("loginManager", currentLoginManager);
		map.put("menu", list);
		return new ModelAndView(target, map);
	}
	
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer page) {
		return list(req, res, session, page, null);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_GROUP_READ')")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer page, Long groupIdx) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		BaseModel groupList = getService().list(page);
		if(groupIdx == null){
			GroupModel first = (GroupModel) ((List) groupList.getData()).get(0);
			groupIdx = first.getIdx();
		}
		map.put("data", groupList);
		map.put("authorities", ((GroupService) getService()).getAuthorities(groupIdx));
		map.put("page", page);
		map.put("groupIdx", groupIdx);

		return getCommonModelAndView(getContextName()+"/list", map);
	}
	
	

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_GROUP_READ')")
	public ModelAndView search(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String property = req.getParameter("property");
		String keyword = req.getParameter("keyword");
		if (page == null) {
			page = 0;
		}
		map.put("data", getService().search(property, keyword, page));
		map.put("page", page);
		return getCommonModelAndView(getContextName()+"/list", map);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_GROUP_CREATE')")
	public ModelAndView create(HttpServletRequest req, HttpServletResponse res,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isCreate", "Y");
		return getCommonModelAndView(getContextName()+"/update", map);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_GROUP_CREATE')")
	public ModelAndView create_POST(HttpServletRequest req,
			HttpServletResponse res, HttpSession session,
			@ModelAttribute GroupModel model) throws ParseException {
		getService().create(model);
		/**
		 * Create Complete
		 */
		return list(req, res, session, 0);
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_GROUP_UPDATE')")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, String idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", getService().read(idx));
		map.put("id", idx);
		map.put("isCreate", "N");
		return getCommonModelAndView(getContextName()+"/update", map);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_GROUP_UPDATE')")
	public ModelAndView update_POST(HttpServletRequest req,
			HttpServletResponse res, HttpSession session,
			@ModelAttribute GroupModel managerModel) throws ParseException {
		getService().update(managerModel);
		/**
		 * Update Complete
		 */
		return list(req, res, session, 0);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_GROUP_DELETE')")
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, String idx) {
		GroupModel model = (GroupModel) getService().read(idx).getData();
		getService().delete(model);

		return list(req, res, session, 0);
	}
}
