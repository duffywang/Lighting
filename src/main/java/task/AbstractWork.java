package src.main.java.task;

import src.main.java.WorkContext;

/**
 * @auther : Duffy
 * @date : 2020-10-25
 **/
public abstract class AbstractWork<T> implements Work {
    /**
     * Request Context including
     */
    private WorkContext workContext;

    /**
     * Alias
     */
    private String alias;

    /**
     * weight decides priority
     */
    private int weight;

    /**
     * Default timeout time, prevent long time waiting
     */
    private int timeout = 1000;

    private String workName = getClass().getSimpleName();


    //---------Override Work interface method-------------------------

    @Override
    public String getWorkName() {
        return null;
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean callback(Object result) throws InterruptedException {
        return false;
    }

    @Override
    public void failed(Throwable e) {

    }

    @Override
    public boolean match() {
        return true;
    }


    //------------AbstractWork method----------------

    public abstract void sendRequest();

    protected boolean markFinished() {

    }

    protected boolean markProcessing() {

    }

    protected boolean markFailure() {

    }

    /**
     *
     * */
    public boolean shouldDo() {
        return match() && getWorkContext()
    }


    //------------Getter and Setter------------------

    public WorkContext getWorkContext() {
        return workContext;
    }

    public void setWorkContext(WorkContext workContext) {
        this.workContext = workContext;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }
}
