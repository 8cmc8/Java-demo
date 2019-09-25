package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 读取注解
 */
public class ReadAnnotation {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        Class<UseAnnotation> clazz = UseAnnotation.class;

        MyAnnotation annotationOnClass = clazz.getAnnotation(MyAnnotation.class);
        System.out.println(annotationOnClass.getValue());

        Field name = clazz.getField("name");
        MyAnnotation annotationOnField = name.getAnnotation(MyAnnotation.class);
        System.out.println(annotationOnField.getValue());

        Method hello = clazz.getMethod("hello", null);
        MyAnnotation annotationOnHelloMethod = hello.getAnnotation(MyAnnotation.class);
        System.out.println(annotationOnHelloMethod.getValue());

        Method defaultMethod = clazz.getMethod("defaultMethod", null);
        MyAnnotation annotationOnDefaultMethod = defaultMethod.getAnnotation(MyAnnotation.class);
        System.out.println(annotationOnDefaultMethod.getValue());
    }
}
