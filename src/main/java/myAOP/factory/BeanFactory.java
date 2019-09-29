package myAOP.factory;

import myAOP.annotation.MyTransaction;
import myAOP.utils.ConnectionUtils;
import myAOP.utils.TransactionManager;

/**
 * 实例对象工厂
 */
public class BeanFactory {
    public Object getBean(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //得到目标类的Class对象
        Class<?> clazz = Class.forName(name);
        //得到目标对象
        Object bean = clazz.newInstance();
        //得到注解信息(MyTransaction)
        MyTransaction annotation = bean.getClass().getAnnotation(MyTransaction.class);
        //如果有MyTransaction注解则返回代理对象
        if (annotation != null) {
            ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
            TransactionManager txManager = new TransactionManager();
            txManager.setConnectionUtils(new ConnectionUtils());
            //装配通知和目标对象
            proxyFactoryBean.setTxManager(txManager);
            proxyFactoryBean.setTarget(bean);
            //获得代理对象
            Object proxyBean = proxyFactoryBean.getProxy();
            return proxyBean;
        }
        return bean;
    }
}
