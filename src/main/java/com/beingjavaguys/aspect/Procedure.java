package com.beingjavaguys.aspect;

import java.lang.annotation.*;

/**
 * Procedure declaration of REST API.
 * @author horiga
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Procedure {
	String description= "";
}
