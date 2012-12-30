package org.codebone.security.bootstrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codebone.framework.generic.AbstractController;
import org.codebone.security.authorities.Authorities;
import org.codebone.security.authorities.AuthoritiesService;
import org.codebone.security.manager.Manager;
import org.codebone.security.manager.ManagerService;
import org.codebone.security.menu.Menu;
import org.codebone.security.menu.MenuService;
import org.codebone.security.organization.Organization;
import org.codebone.security.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bootstrap")
public class BootstrapController extends AbstractController{
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private MenuService menuService;
	
	protected String getContextName(){
		return "bootstrap";
	}
	
	@RequestMapping(value = "/install", method = RequestMethod.GET)
	public ModelAndView install(HttpServletRequest req, HttpServletResponse res,
			HttpSession session) {
		boolean managerIsNew = managerService.isNew();
		boolean authoritiesIsNew = authoritiesService.isNew();
		boolean organizationIsNew = organizationService.isNew();
		if(managerIsNew && authoritiesIsNew && organizationIsNew){
			return new ModelAndView(getContextName()+"/install", null);
		}else{
			return new ModelAndView(getContextName()+"/error", null);
		}
		
	}
	
	@RequestMapping(value = "/install", method = RequestMethod.POST)
	public ModelAndView installPost(HttpServletRequest req, HttpServletResponse res,
			HttpSession session, String id, String password, String passwordCheck, String name, String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean managerIsNew = managerService.isNew();
		boolean authoritiesIsNew = authoritiesService.isNew();
		boolean organizationIsNew = organizationService.isNew();
		
		if(!(managerIsNew && authoritiesIsNew && organizationIsNew)){
			return new ModelAndView(getContextName()+"/error", null);
		}
		
		if(!password.equals(passwordCheck)){
			map.put("error", "incorrect password");
			return new ModelAndView(getContextName()+"/install", map);
		}
		
		Manager manager = new Manager();
		manager.setEmail(email);
		manager.setId(id);
		manager.setPassword(password);
		manager.setName(name);
		managerService.create(manager);
		map.put("manager", manager);
		makeBaseAuthorities(manager);
		makeBaseMenu();
		
		return new ModelAndView(getContextName()+"/complete", map);
	}
	
	private void makeBaseAuthorities(Manager manager){
		Authorities auth1 = new Authorities();
		auth1.setAuthority("ROLE_ADMIN");
		authoritiesService.create(auth1);
		
		Authorities auth2 = new Authorities();
		auth2.setAuthority("ROLE_USER");
		authoritiesService.create(auth2);
		
		Organization organization = new Organization();
		organization.setName("Administrator Group");
		List managerList = new ArrayList();
		List authList = new ArrayList();
		managerList.add(manager);
		authList.add(auth1);
		authList.add(auth2);
		organization.setManagerList(managerList);
		organization.setAuthoritiesList(authList);
		organizationService.create(organization);
	}
	
	private void makeBaseMenu(){
		Menu m = new Menu();
		m.setName("관리자 메뉴");
		m.setPriOrder(0);
		m.setSubOrder(0);
		menuService.create(m);
		
		m = new Menu();
		m.setName("관리자");
		m.setUrl("/app/manager");
		m.setPriOrder(0);
		m.setSubOrder(1);
		menuService.create(m);
		
		m = new Menu();
		m.setName("메뉴");
		m.setUrl("/app/menu");
		m.setPriOrder(0);
		m.setSubOrder(2);
		menuService.create(m);
		
		m = new Menu();
		m.setName("권한");
		m.setUrl("/app/organization");
		m.setPriOrder(0);
		m.setSubOrder(3);
		menuService.create(m);
		
		m = new Menu();
		m.setIsSeparate("Y");
		m.setPriOrder(1);
		m.setSubOrder(0);
		menuService.create(m);
		
		m = new Menu();
		m.setName("사용자 메뉴");
		m.setPriOrder(2);
		m.setSubOrder(0);
		menuService.create(m);
		
		m = new Menu();
		m.setName("Codebone WebSite");
		m.setUrl("https://github.com/withmomo/codebone");
		m.setPriOrder(2);
		m.setSubOrder(1);
		m.setIsExternal("Y");
		menuService.create(m);
	}

}
