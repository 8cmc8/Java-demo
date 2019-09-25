package myJunit;

import myJunit.myAnnotation.MyAfter;
import myJunit.myAnnotation.MyBefore;
import myJunit.myAnnotation.MyTest;

/**
 * 使用自定义注解的测试类
 */
public class EmployeeDAOTest {
    @MyBefore
    public void init() {
        System.out.println("初始化...");
    }

    @MyAfter
    public void destroy() {
        System.out.println("销毁...");
    }

    @MyTest
    public void testSave() {
        System.out.println("save...");
    }

    @MyTest
    public void testDelete() {
        System.out.println("delete...");
    }
}
