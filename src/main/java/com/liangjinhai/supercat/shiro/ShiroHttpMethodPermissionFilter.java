package com.liangjinhai.supercat.shiro;

import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/10.
 */
public class ShiroHttpMethodPermissionFilter extends ShiroPermissionsAuthorizationFilter
{

    /**
     * This class's private logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ShiroHttpMethodPermissionFilter.class);

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        String[] perms = (String[]) mappedValue;
        String method = ((HttpServletRequest) request).getMethod();
        String[] resolvedPerms = buildPermissions(perms, method);
        return super.isAccessAllowed(request, response, resolvedPerms);
    }

    protected String[] buildPermissions(String[] configuredPerms, String method) {
        if (configuredPerms == null || configuredPerms.length <= 0 || !StringUtils.hasText(method)) {
            return configuredPerms;
        }
        String[] mappedPerms = new String[configuredPerms.length];

        // loop and append :action
        for (int i = 0; i < configuredPerms.length; i++) {
            mappedPerms[i] = configuredPerms[i] + "?" + method;
        }
        return mappedPerms;
    }

    public enum HttpMethod {

        DELETE("DELETE"),
        GET("GET"),
        HEAD("HEAD"),
        OPTIONS("OPTIONS"),
        POST("POST"),
        PUT("PUT"),
        TRACE("TRACE");

        public final String name;

        HttpMethod(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
