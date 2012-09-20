package org.codebone.domain.user;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController{

	@Autowired
	private UserService service;
	
	protected String getContextName(){
		return "user";
	}

	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_USER_READ')")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res, HttpSession session, Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		map.put("data", service.list(page));
		map.put("page", page);

		return getCommonModelAndView(getContextName()+"/list", map, session);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_USER_READ')")
	public ModelAndView search(HttpServletRequest req, HttpServletResponse res, HttpSession session, Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String property = req.getParameter("property");
		String keyword = req.getParameter("keyword");
		if (page == null) {
			page = 0;
		}
		map.put("data", service.search(property, keyword, page));
		map.put("page", page);
		return getCommonModelAndView(getContextName()+"/list", map, session);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_USER_CREATE')")
	public ModelAndView create(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isCreate", "Y");
		return getCommonModelAndView(getContextName()+"/write", map, session);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_USER_CREATE')")
	public RedirectView create_POST(HttpServletRequest req,
			HttpServletResponse res, HttpSession session, @ModelAttribute User model) throws ParseException {
		service.create(model);
		return new RedirectView("");
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_USER_UPDATE')")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res, HttpSession session, String idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", service.read(idx));
		map.put("id", idx);
		map.put("isCreate", "N");
		return getCommonModelAndView(getContextName()+"/write", map, session);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_USER_UPDATE')")
	public RedirectView update_POST(HttpServletRequest req,
			HttpServletResponse res, HttpSession session, @ModelAttribute User model) throws ParseException {
		service.update(model);
		return new RedirectView("");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_USER_DELETE')")
	public RedirectView delete(HttpServletRequest req, HttpServletResponse res, HttpSession session, String idx) {
		User model = (User) service.read(idx).getData();
		service.delete(model);
		return new RedirectView("");
	}
}
