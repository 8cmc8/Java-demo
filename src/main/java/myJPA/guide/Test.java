package myJPA.guide;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * 测试通过反射在父类构造器中取得子类对应父类的泛型参数的Class类对象
 */
public class Test {
    public static void main(String[] args) {
        new B();
    }
}

class A<T, V> {
    public A() {
        Class<? extends A> aClass = this.getClass();
        System.out.println(aClass);
        System.out.println(aClass.getName());
        System.out.println(aClass.getSimpleName());

        //获得泛型类型的父类
        Type genericSuperclass = aClass.getGenericSuperclass();
        System.out.println(genericSuperclass);

        //向下转型，以获得更多方法（子类方法总比父类多）
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        System.out.println(parameterizedType);

        //获得泛型的类数组
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(Arrays.toString(actualTypeArguments));

        //获取第一个泛型的类并转为Class对象
        Class actualTypeArgument = (Class) actualTypeArguments[0];
        System.out.println(actualTypeArgument);
    }
}

class B extends A<String, Integer> {

}
