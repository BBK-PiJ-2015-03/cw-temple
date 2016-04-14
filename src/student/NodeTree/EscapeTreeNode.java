package student.NodeTree;

import game.NodeStatus;

/**
 * Created by J2FX on 04/02/2016.
 */
public class EscapeTreeNode implements TreeNode {
    private TreeWrap info;
    private long Distance;
    private EscapeTreeNode parentNode;
    private EscapeTreeNode lLink = null, mLink = null, rLink = null;

    public EscapeTreeNode(EscapeTreeNode parent){
        setParentNode(parent);
    }

    public EscapeTreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = (EscapeTreeNode) parentNode;
    }

    public TreeNode getlLink() {
        return lLink;
    }

    public TreeNode getmLink() {
        return mLink;
    }

    public TreeNode getrLink() {
        return rLink;
    }

    public TreeWrap getInfo() {
        return info;
    }

    public void setInfo(TreeWrap info) {
        this.info = info;
    }

    public void setlLink(TreeNode lLink) {
        this.lLink = (EscapeTreeNode) lLink;
    }

    public void setrLink(TreeNode rLink) {
        this.rLink = (EscapeTreeNode) rLink;
    }

    public void setmLink(TreeNode mLink) {
        this.mLink = (EscapeTreeNode) mLink;
    }

    @Override
    public void add(NodeStatus node, int i) {

    }


/*
    public void add(NodeStatus node, int i){
        switch (i){
            case 0: TreeNode leftNode = new EscapeTreeNode(this);
                    TreeWrap leftWrap = new TreeWrap(node.getId(), this.getInfo().getNode(), node.getEdge(node).length(), node.getTile().getGold());
                    leftNode.setInfo(leftWrap);
                    this.setlLink(leftNode);
                    return;
            case 1: TreeNode middleNode = new EscapeTreeNode(this);
                    TreeWrap middleWrap = new TreeWrap(node.getId(), this.getInfo().getNode(), node.getEdge(node).length(), node.getTile().getGold());
                    middleNode.setInfo(middleWrap);
                    this.setmLink(middleNode);
                    return;
            case 2: TreeNode rightNode = new EscapeTreeNode(this);
                    TreeWrap rightWrap = new TreeWrap(node.getId(), this.getInfo().getNode(), node.getEdge(node).length(), node.getTile().getGold());
                    rightNode.setInfo(rightWrap);
                    this.setrLink(rightNode);
                    return;
        }
    }
*/

}
