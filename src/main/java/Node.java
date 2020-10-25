package src.main.java;


import src.main.java.entity.NodeStatusConstant;
import src.main.java.task.AbstractWork;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther : Duffy
 * @date : 2020-10-25
 **/
public class Node implements Comparable{

    private AtomicInteger state = new AtomicInteger(NodeStatusConstant.NOT_STARTED);

    private AbstractWork work;

    private Map<Node, Relation> preNodeMap;

    private Map<Node, Relation> postNodeMap;


    public Node(AbstractWork work) {
        this.work = work;
        preNodeMap = new HashMap<>();
        postNodeMap = new HashMap<>();
    }

    public Map<Node, Relation> getPreNodeMap(){
        return preNodeMap;
    }
    public Map<Node, Relation> getPostNodeMap(){
        return postNodeMap;
    }

    public String getName(){
        return work.getWorkName();
    }

    protected void addPostNode(Node node, Relation relation){
        postNodeMap.put(node, relation);
    }

    protected void addPreNode(Node node, Relation relation){
        preNodeMap.put(node, relation);
    }

    protected boolean markStatus(int nodeStatus){
        if (state.get() == NodeStatusConstant.FAILURE || state.get() == NodeStatusConstant.FINISHED){
            return false;
        }
        if (nodeStatus == NodeStatusConstant.NOT_STARTED){
            return false;
        }
        if (nodeStatus == NodeStatusConstant.PROCESSING){
            return state.compareAndSet(NodeStatusConstant.NOT_STARTED, NodeStatusConstant.PROCESSING);
        }
        int preState = state.getAndSet(nodeStatus);
        
        if (preState == nodeStatus){
            return false;
        }
        
        Map<Node, Relation> treeMap = new TreeMap<>(postNodeMap);
        for (Map.Entry<Node, Relation> entry : treeMap.entrySet()) {
            Node postNode = entry.getKey();
            postNode.markSelf();
            
            if (postNode.getState().get() != NodeStatusConstant.FINISHED && postNode.getState().get() != NodeStatusConstant.FAILURE){
                postNode.tryInvoke();
            }
        }
        return true;
    }

    private void markSelf() {
    }

    private void tryInvoke() {
        if (ready()){
            getWork().sendRequest();
        }
    }

    private boolean ready() {
        boolean ready = true;
        for (Map.Entry<Node, Relation> entry : preNodeMap.entrySet()) {
            Node preNode = entry.getKey();
            int curState = preNode.getState().get();
            Relation relation = entry.getValue();
            if (curState == NodeStatusConstant.NOT_STARTED || curState == NodeStatusConstant.PROCESSING){
                ready = false;
                break;
            }
            if (relation == Relation.INVOKE_FAILURE && curState != NodeStatusConstant.FAILURE){
                ready = false;
                break;
            }
            if (relation == Relation.INVOKE_SUCCESS && curState != NodeStatusConstant.FINISHED){
                ready = false;
                break;
            }

        }
        return ready;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o)return 0;
        if (null == o)return -1;

        Node dest = (Node) o;

        if (this.getWork().getWeight() > dest.getWork().getWeight()){
            return -1;
        }else {
            return 1;
        }
    }


    public AtomicInteger getState() {
        return state;
    }

    public void setState(AtomicInteger state) {
        this.state = state;
    }

    public AbstractWork getWork() {
        return work;
    }

    public void setWork(AbstractWork work) {
        this.work = work;
    }

    public void setPreNodeMap(Map<Node, Relation> preNodeMap) {
        this.preNodeMap = preNodeMap;
    }

    public void setPostNodeMap(Map<Node, Relation> postNodeMap) {
        this.postNodeMap = postNodeMap;
    }
}
