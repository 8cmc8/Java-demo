package annotation;

/**
 * 使用自定义注解的类
 */
@MyAnnotation(getValue = "annotation on class")
public class UseAnnotation {
    @MyAnnotation(getValue = "annotation on field")
    public String name;

    @MyAnnotation(getValue = "annotation on method")
    public void hello() {

    }

    @MyAnnotation
    public void defaultMethod() {

    }
}
