package example.第三次作业.proxy;

import example.第三次作业.dao.impl.CRUDIml;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author LIXIN
 */
public class DaoFactory {
    public static <T> CRUDIml<T> getInstance(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(CRUDIml.class);
        //设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());
        //这里的creat方法就是正式创建代理类
        return (CRUDIml<T>) enhancer.create();
    }

}
