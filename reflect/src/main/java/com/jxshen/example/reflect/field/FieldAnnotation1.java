package com.jxshen.example.reflect.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来注解Field的注解，测试用<br>
 * 注解类的RetentionPolicy为CLASS或者SOURCE时，在运行时是无法通过反射获得注解的
 * @author jxshen
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldAnnotation1 {

}
