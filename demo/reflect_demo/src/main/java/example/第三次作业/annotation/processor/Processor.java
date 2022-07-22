package example.第三次作业.annotation.processor;

import example.第三次作业.annotation.processor.impl.DefaultDispatcher;
import example.第三次作业.dao.DataNotValidException;

public class Processor {
    public static  Object origin = null;
    public static void startProcess(Object origin) {
        Processor.origin = origin;
        DefaultDispatcher defaultDispatcher = new DefaultDispatcher();
        try {
            defaultDispatcher.dispatcher(origin, null);
        } catch (DataNotValidException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
