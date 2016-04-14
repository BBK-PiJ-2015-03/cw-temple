package student.NodeTree;

import game.NodeStatus;

/**
 * Created by J2FX on 04/02/2016.
 */
public class EscapeTreeWrap {
    private Long node;
    private Long parentNode;
    private int distance;
    private int gold;
    private NodeStatus rootNode;
    private boolean visited = false;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Long getParentNode() {
        return parentNode;
    }

    public void setParentNode(Long parentNode) {
        this.parentNode = parentNode;
    }

    public Long getNode() {
        return node;
    }

    public void setNode(Long node) {
        this.node = node;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public NodeStatus getRootNode() {
        return rootNode;
    }

    public void setRootNode(NodeStatus rootNode) {
        this.rootNode = rootNode;
    }

    public EscapeTreeWrap(Long node, Long parentNode, int distance, int gold) {
        setNode(node);
        setParentNode(parentNode);
        setDistance(distance);
        setGold(gold);
    }
}
