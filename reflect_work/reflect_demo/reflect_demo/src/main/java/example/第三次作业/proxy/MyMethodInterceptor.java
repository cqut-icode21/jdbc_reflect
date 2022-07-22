package example.第三次作业.proxy;

import example.第三次作业.annotation.dao.Transaction;
import example.第三次作业.annotation.processor.TransactionProcessor;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
        System.out.println(method);
        Transaction annotation = method.getAnnotation(Transaction.class);
        if (annotation != null) {
            TransactionProcessor transactionProcessor = new TransactionProcessor();
            return transactionProcessor.process(o, proxy, method, objects);
        }
        return proxy.invokeSuper(o,objects);
    }
}
