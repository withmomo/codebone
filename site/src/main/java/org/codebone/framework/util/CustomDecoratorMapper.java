package org.codebone.framework.util;

import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;
import com.opensymphony.module.sitemesh.mapper.ConfigLoader;

/**
 * 서블릿 경로가 아닌 컨텍스트 경로를 제외한 요청 URI를 이용해서 패턴 매칭을 수행한다.
 * 
 * Sitemesh 2.4.1 버전의 ConfigDecoratorMapper로부터 코드를 가져와서 작성하였음.
 * 
 * Link : http://javacan.tistory.com/entry/UsingCustomDecoratorMapperForSiteMeshURLPatternMatching
 * 
 * @author 최범균
 * @version 2010. 3. 5.
 */
public class CustomDecoratorMapper extends AbstractDecoratorMapper {

    private ConfigLoader configLoader = null;

    /** Create new ConfigLoader using '/WEB-INF/decorators.xml' file. */
    public void init(Config config, Properties properties,
            DecoratorMapper parent) throws InstantiationException {
        super.init(config, properties, parent);
        try {
            String fileName = properties.getProperty("config",
                    "/WEB-INF/decorators.xml");
            configLoader = new ConfigLoader(fileName, config);
        } catch (Exception e) {
            throw new InstantiationException(e.toString());
        }
    }


    /**
     * Retrieve {@link com.opensymphony.module.sitemesh.Decorator} based on
     * 'pattern' tag.
     */
    public Decorator getDecorator(HttpServletRequest request, Page page) {
        String thisPath = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (thisPath.startsWith(contextPath)) {
            thisPath = thisPath.substring(contextPath.length());
        }
        String name = null;
        try {
            name = configLoader.getMappedName(thisPath);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        Decorator result = getNamedDecorator(request, name);
        return result == null ? super.getDecorator(request, page) : result;
    }

    /**
     * Retrieve Decorator named in 'name' attribute. Checks the role if
     * specified.
     */
    public Decorator getNamedDecorator(HttpServletRequest request, String name) {
        Decorator result = null;
        try {
            result = configLoader.getDecoratorByName(name);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        if (result == null
                || (result.getRole() != null && !request.isUserInRole(result
                        .getRole()))) {
            // if the result is null or the user is not in the role
            return super.getNamedDecorator(request, name);
        } else {
            return result;
        }
    }

}