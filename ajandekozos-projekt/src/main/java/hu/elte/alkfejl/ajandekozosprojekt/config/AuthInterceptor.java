package hu.elte.alkfejl.ajandekozosprojekt.config;

import hu.elte.alkfejl.ajandekozosprojekt.model.UserLogin;
import hu.elte.alkfejl.ajandekozosprojekt.service.UserService;
import hu.elte.alkfejl.ajandekozosprojekt.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<UserLogin.Role> routeRoles = getRoles((HandlerMethod) handler);
        UserLogin userLogin = userService.getUserLogin();

        // when there are no restrictions, we let the user through
        if (routeRoles.isEmpty() || routeRoles.contains(UserLogin.Role.GUEST)) {
            return true;
        }
        // check role
        if (userService.isLoggedIn() && routeRoles.contains(userLogin.getRole())) {
            return true;
        }
        response.setStatus(401);
        return false;
    }

    private List<UserLogin.Role> getRoles(HandlerMethod handler) {
        Role role = handler.getMethodAnnotation(Role.class);
        return null;//role == null ? Collections.emptyList() : Arrays.asList(role.value());
    }
}