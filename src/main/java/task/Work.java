package src.main.java.task;

/**
 * @auther : wangyufei
 * @date : 2020-10-25
 **/
public interface Work<T> {

    /**
     * 任务的别名
     * */
    String getWorkName();

    /**
     * 具体执行调用过程
     * */
    void execute();

    /**
     * 调用成功时回调处理
     * */
    boolean callback(T result) throws InterruptedException;

    /**
     * 调用失败时回调处理
     * */
    void failed(Throwable e);

    /**
     * */
    boolean match();
}
