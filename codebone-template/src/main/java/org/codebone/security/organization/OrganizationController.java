package org.codebone.security.organization;

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
import org.codebone.security.manager.Manager;
import org.codebone.security.manager.ManagerService;
import org.codebone.security.menu.Menu;
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
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	private OrganizationService service;
	
	@Autowired
	protected ManagerService managerService;

	@Autowired
	protected MenuService menuService;
	
	public AbstractService getService() {
		return service;
	}
	
	protected String getContextName() {
		return "organization";
	}
	
	public ModelAndView getCommonModelAndView(String target,
			Map<String, Object> map) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Manager currentLoginManager = (Manager) managerService.read(
				auth.getName()).getData();
		List<Menu> list = (List<Menu>) menuService.listAll()
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
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ORGANIZATION_READ')")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, Integer page, Long organizationIdx) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		BaseModel organizationList = getService().list(page);
		if(organizationIdx == null){
			Organization first = (Organization) ((List) organizationList.getData()).get(0);
			organizationIdx = first.getIdx();
		}
		map.put("data", organizationList);
		map.put("authorities", ((OrganizationService) getService()).getAuthorities(organizationIdx));
		map.put("page", page);
		map.put("organizationIdx", organizationIdx);

		return getCommonModelAndView(getContextName()+"/list", map);
	}
	
	

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ORGANIZATION_READ')")
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
	@PreAuthorize("hasRole('ROLE_ORGANIZATION_CREATE')")
	public ModelAndView create(HttpServletRequest req, HttpServletResponse res,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isCreate", "Y");
		return getCommonModelAndView(getContextName()+"/write", map);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ORGANIZATION_CREATE')")
	public RedirectView create_POST(HttpServletRequest req,
			HttpServletResponse res, HttpSession session,
			@ModelAttribute Organization model) throws ParseException {
		getService().create(model);
		/**
		 * Create Complete
		 */
		return new RedirectView("");
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ORGANIZATION_UPDATE')")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, String idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", getService().read(idx));
		map.put("id", idx);
		map.put("isCreate", "N");
		return getCommonModelAndView(getContextName()+"/write", map);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ORGANIZATION_UPDATE')")
	public RedirectView update_POST(HttpServletRequest req,
			HttpServletResponse res, HttpSession session,
			@ModelAttribute Organization managerModel) throws ParseException {
		getService().update(managerModel);
		/**
		 * Update Complete
		 */
		return new RedirectView("");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ORGANIZATION_DELETE')")
	public RedirectView delete(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, String idx) {
		Organization model = (Organization) getService().read(idx).getData();
		getService().delete(model);

		return new RedirectView("");
	}
}
