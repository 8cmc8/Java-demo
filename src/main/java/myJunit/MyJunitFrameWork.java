package myJunit;

import myJunit.myAnnotation.MyAfter;
import myJunit.myAnnotation.MyBefore;
import myJunit.myAnnotation.MyTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Junit框架
 */
public class MyJunitFrameWork {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<EmployeeDAOTest> clazz = EmployeeDAOTest.class;
        EmployeeDAOTest obj = clazz.newInstance();

        Method[] methods = clazz.getMethods();

        List<Method> beforeMethod = new ArrayList<>();
        List<Method> afterMethod = new ArrayList<>();
        List<Method> testMethod = new ArrayList<>();

        //储存对应的方法
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyBefore.class)) {
                beforeMethod.add(method);
            } else if (method.isAnnotationPresent(MyAfter.class)) {
                afterMethod.add(method);
            } else if (method.isAnnotationPresent(MyTest.class)) {
                testMethod.add(method);
            }
        }

        //迭代执行方法
        for (Method method : testMethod) {
            for (Method methodBefore : beforeMethod) {
                methodBefore.invoke(obj);
            }

            method.invoke(obj);

            for (Method methodAfter : afterMethod) {
                methodAfter.invoke(obj);
            }
        }
    }
}
