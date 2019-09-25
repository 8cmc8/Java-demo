package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)//保留策略必须为RUNTIME（想要被反射读取，需要运行时仍可读取）
public @interface MyAnnotation {
    String getValue() default "no description";
}
