package myAOP;

import myAOP.factory.BeanFactory;

public class AopTest {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        try {
            Object bean = beanFactory.getBean("myAOP.service.UserServiceImpl");
            System.out.println(bean.getClass().getName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
