package src.main.java;

import src.main.java.entity.NodeStatusConstant;
import src.main.java.util.EmptyUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @auther : wangyufei
 * @date : 2020-10-25
 **/
public class Graph {

    private List<Node> nodes;

    private CountDownLatch countDownLatch;

    private WorkContext context;


    public Graph() {
        nodes = new CopyOnWriteArrayList<>();
    }

    public void addNode(Node node){
        if (null != node){
            nodes.add(node);
        }
    }

    public void addEdge(Node preNode, Node postNode){
        if (null == preNode || null == postNode || preNode == postNode){
            return;
        }

    }

    public Node getByName(String name) {
        if (!EmptyUtils.isEmpty(name)){
            for (Node node : nodes) {
                if (name.equals(node.getName())){
                    return node;
                }
            }
        }
        return null;
    }

    public boolean markNodeFinished(Node node) {
        return markNodeStatus(node, NodeStatusConstant.FINISHED);

    }

    public boolean markNodeProcessing(Node node) {
        return markNodeStatus(node, NodeStatusConstant.PROCESSING);
    }

    public boolean markNodeFailure(Node node) {
        return markNodeStatus(node, NodeStatusConstant.FAILURE);
    }

    private boolean markNodeStatus(Node node, int nodeStatus) {
        boolean flag = node.markStatus(nodeStatus);
        if (flag && (nodeStatus == NodeStatusConstant.FINISHED || nodeStatus == NodeStatusConstant.FAILURE)){
            countDownLatch.countDown();
        }
        return flag;
    }


}
