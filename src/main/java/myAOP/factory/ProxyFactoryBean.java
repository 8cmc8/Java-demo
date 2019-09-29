package myAOP.factory;

import myAOP.utils.TransactionManager;
import java.lang.reflect.Proxy;

/**
 * 代理对象工厂
 */
public class ProxyFactoryBean {
    //通知
    private TransactionManager txManager;
    //目标对象
    private Object target;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy1, method, args) -> {
            try {
                //开启事务
                txManager.beginTransaction();
                //执行操作
                Object result = method.invoke(target, args);
                //提交事务
                txManager.commit();
                //返回结果
                return result;
            } catch (Exception e) {
                //回滚事务
                txManager.rollback();
                throw new RuntimeException();
            } finally {
                //释放连接
                txManager.release();
            }
        });

        return proxy;
    }
}
