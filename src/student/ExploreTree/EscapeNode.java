package student.ExploreTree;

import game.Node;

/**
 * Created by J2FX on 25/02/2016.
 */
public class EscapeNode {
    private Node node;
    private long distance = Integer.MAX_VALUE;
    private boolean visited = false;
    private Node prevNode;

    public EscapeNode(Node node, long distance, boolean visited, Node prevNode){
        this.node = node;
        this.distance = distance;
        this.visited = visited;
        this.prevNode = prevNode;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }
}
