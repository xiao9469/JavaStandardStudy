package com.testInterface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,
	ElementType.PARAMETER,ElementType.TYPE_PARAMETER,ElementType.LOCAL_VARIABLE,ElementType.TYPE_USE})
public @interface TestAnnotation {
	String values() default "HHH";
	
}
