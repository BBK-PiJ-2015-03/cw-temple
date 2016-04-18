package student.EscapeAlgorithm;

import game.Node;

/**
 * Vertex wrapper class, this will hold the distance of a node and the node itself.
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

    /**
     * Compare a vertex by distance (cost) and return the 'cheapest'
     *
     * @param vertex to compare
     * @return closest vertex - boolean
     */
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
