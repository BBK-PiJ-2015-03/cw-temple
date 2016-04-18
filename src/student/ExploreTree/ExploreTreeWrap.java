package student.ExploreTree;

import game.NodeStatus;

/**
 * Created by J2FX on 04/02/2016.
 */
public class ExploreTreeWrap implements TreeWrap {
    private Long node;
    private Long parentNode;
    private NodeStatus rootNode;
    private boolean visited = false;

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

    public ExploreTreeWrap(Long node, Long parentNode) {
        setNode(node);
        setParentNode(parentNode);
    }
}
