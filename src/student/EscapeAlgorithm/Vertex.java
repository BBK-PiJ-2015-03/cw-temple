package student.EscapeAlgorithm;

import game.Node;

/**
 * Created by J2FX on 18/04/2016.
 */
public class Vertex implements Comparable<Vertex>{
    long cost = Long.MAX_VALUE;
    Node node;

    public Vertex(Node node) {
        this.node = node;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public int compareTo(Vertex o) {
        if (cost > o.getCost()) {
            return 1;
        } else if (cost  < o.getCost()) {
            return -1;
        } else {
            return 0;
        }
    }
}
