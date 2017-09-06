package com.jxshen.example.lambda;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class RepeatableAnnotationsTest {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface Filters {
        Filter[] value();
    }
    
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    private @interface Filter {
        String value();
    }
    
    @Filter("filter1")
    @Filter("filter2")
    private interface Filterable{
        
    }
    
    public static void main(String[] args) {
        for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
            System.out.println(filter.value());
        }
        for (Filters filters : Filterable.class.getAnnotationsByType(Filters.class)) {
            System.out.println(filters.value());
        }
    }
}
