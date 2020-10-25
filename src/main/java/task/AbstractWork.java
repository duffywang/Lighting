package src.main.java.task;

import src.main.java.Graph;
import src.main.java.Node;
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
        Graph graph = getWorkContext().getGraph();
        Node node = graph.getByName(getWorkName());
        return graph.markNodeFinished(node);
    }

    protected boolean markProcessing() {
        Graph graph = getWorkContext().getGraph();
        Node node = graph.getByName(getWorkName());
        return graph.markNodeProcessing(node);
    }

    /**
     *
     * */
    protected boolean markFailure() {
        Graph graph = getWorkContext().getGraph();
        Node node = graph.getByName(getWorkName());
        return graph.markNodeFailure(node);
    }


    /**
     * execute or not
     *  1.business logic eg: version time,etc.
     *  2.forbidden list
     * */
    public boolean shouldDo() {
        return match() && !getWorkContext().getForbiddenWorks().contains(getClass().getName());
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
