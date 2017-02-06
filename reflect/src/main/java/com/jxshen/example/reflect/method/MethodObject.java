package com.jxshen.example.reflect.method;

import java.util.List;

public class MethodObject {

    /**
     * A complete method has attributes including:<br>
     * Method Annotation<br>
     * Method Modifier<br>
     * Method Generic Parameter<br>
     * Method Return Value<br>
     * Method Name<br>
     * Method Parameter(Generic, Annotation)<br>
     * Method Exception
     * 
     * @param list
     * @param params
     * @return
     * @throws RuntimeException
     * @throws Exception
     */
    @MethodAnnotation1
    @MethodAnnotation2
    private <T> boolean add(int index,@ParameterAnnotation List<T> list, T... params)
            throws RuntimeException, Exception {
        
        if (null == list) {
            throw new RuntimeException("list is null");
        }
        
        if (null == params) {
            return false;
        }
        
        for (T t : params) {
            list.add(t);
        }
        
        return true;
    }
}
