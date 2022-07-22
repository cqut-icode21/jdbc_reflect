package example.common;

public abstract class Test {
    /**
     * @return 运行代码
     */
    protected abstract boolean run();

    /***
     * 执行测试
     */
    public void test() {
        System.out.println("题目信息：" + info());
    }

    /**
     * @return 题目信息
     */
    protected abstract String info();

}
