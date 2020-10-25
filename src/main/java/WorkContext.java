package src.main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther : Duffy
 * @date : 2020-10-25
 **/
public class WorkContext {

    /**
     * Key support 3 types
     *  1.class simpeName
     *  2.alias, need setAlias in advance
     *  3.custom Key
     * Value : business data through the context
     * */
    private Map<String, Object> context = new HashMap<>();

    private Graph graph;

    /**
     * 可动态配置化支持禁止执行任务
     */
    private List<String> forbiddenWorks = new ArrayList<>();


    //-----------Construct-------------------

    public WorkContext(Graph graph) {
        this.graph = graph;
    }

    public WorkContext() {
    }

    //-----------Getter and Setter------------

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public List<String> getForbiddenWorks() {
        return forbiddenWorks;
    }

    public void setForbiddenWorks(List<String> forbiddenWorks) {
        this.forbiddenWorks = forbiddenWorks;
    }
}
