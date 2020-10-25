package src.main.java;

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

}
