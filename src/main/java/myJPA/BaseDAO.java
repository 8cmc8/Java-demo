package myJPA;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;


public class BaseDAO<T> {
    private static BasicDataSource dataSource = new BasicDataSource();
    static {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    //泛型参数的Class对象
    private Class<T> beanClass;

    public BaseDAO() {
        //获得父类的泛型参数的Class对象
        beanClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    //插入数据
    public void add(T bean) {
        Field[] declaredFields = beanClass.getDeclaredFields();

        //拼接sql，读取beanClass的注解获取表名
        String sql = "insert into " + beanClass.getAnnotation(Table.class).value() + " values(";
        for (int i = 0; i < declaredFields.length; i++) {
            sql += "?";
            if (i < declaredFields.length - 1) {
                sql += ",";
            }
        }
        sql += ")";

        //获取bean字段的值
        Object[] params = getValue(declaredFields, bean);
        int num = jdbcTemplate.update(sql, params);
        System.out.println(num);
    }

    //获取bean字段值
    private Object[] getValue(Field[] fields, T bean) {
        ArrayList<Object> paramList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                Object obj = fields[i].get(bean);
                paramList.add(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        int size = paramList.size();
        Object[] params = paramList.toArray(new Object[size]);
        return params;
    }

    //其他crud

}
