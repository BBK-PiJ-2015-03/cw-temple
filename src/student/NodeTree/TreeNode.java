package student.NodeTree;

import game.NodeStatus;

public interface TreeNode {

    public TreeNode getParentNode();

    public void setParentNode(TreeNode parentNode);

    public TreeNode getlLink();

    public TreeNode getmLink();

    public TreeNode getrLink();

    public TreeWrap getInfo();

    public void setInfo(TreeWrap info);

    public void setlLink(TreeNode lLink);

    public void setrLink(TreeNode rLink);

    public void setmLink(TreeNode mLink);

    public void add(NodeStatus node, int i);

}
