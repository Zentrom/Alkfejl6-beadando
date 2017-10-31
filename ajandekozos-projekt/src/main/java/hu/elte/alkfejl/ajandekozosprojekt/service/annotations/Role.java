package hu.elte.alkfejl.ajandekozosprojekt.service.annotations;

import hu.elte.alkfejl.ajandekozosprojekt.model.UserLogin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Role {
    UserLogin.Role[] value() default {UserLogin.Role.GUEST};
}