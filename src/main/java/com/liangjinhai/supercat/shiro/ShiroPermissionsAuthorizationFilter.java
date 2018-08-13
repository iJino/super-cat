package com.liangjinhai.supercat.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 */
public class ShiroPermissionsAuthorizationFilter extends AuthorizationFilter {

    //TODO - complete JavaDoc

    public static void main(String[] args) {
        for (String str :
                "U_AA:GET|AAA:POST".split(":")) {
            System.out.println(str);
        }

    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;
        boolean isPermitted = true;
        if (perms != null && perms.length > 0) {
            if (perms.length == 1) {
                String[] ps = perms[0].split("\\?");
                if (ps.length == 2 && ps[0].contains(":")) {
                    List<Boolean> bool = new ArrayList<>();
                    for (String ma : ps[0].split("\\|")) {
                        String[] value = ma.split(":");
                        if (value.length <= 1) {
                            bool.add(subject.isPermitted(value[0]));
                        } else {
                            bool.add((subject.isPermitted(value[0]) && value[1].equalsIgnoreCase(ps[1])));
                        }
                    }
                    if (!bool.contains(Boolean.TRUE)) {
                        isPermitted = false;
                    }
                } else {
                    if (!subject.isPermitted(ps[0])) {
                        isPermitted = false;
                    }
                }
            } else {
                if (!subject.isPermittedAll(perms)) {
                    isPermitted = false;
                }
            }
        }

        return isPermitted;
    }
}
