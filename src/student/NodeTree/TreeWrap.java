package student.NodeTree;

import game.NodeStatus;

/**
 * Created by J2FX on 04/02/2016.
 */
public interface TreeWrap {

    public Long getParentNode();

    public void setParentNode(Long parentNode);

    public Long getNode();

    public void setNode(Long node);

    public boolean getVisited();

    public void setVisited(boolean visited);

    public NodeStatus getRootNode();

    public void setRootNode(NodeStatus rootNode);
}
