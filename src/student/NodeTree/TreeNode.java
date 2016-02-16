package student.NodeTree;

import game.NodeStatus;

/**
 * Created by J2FX on 04/02/2016.
 */
public class TreeNode {
    private TreeWrap info;
    private TreeNode parentNode;
    private TreeNode lLink = null, mLink = null, rLink = null;

    public TreeNode(TreeNode parent){
        setParentNode(parent);
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
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
        this.lLink = lLink;
    }

    public void setrLink(TreeNode rLink) {
        this.rLink = rLink;
    }

    public void setmLink(TreeNode mLink) {
        this.mLink = mLink;
    }

    public void exploreAdd(NodeStatus node, int i){
        switch (i){
            case 0: TreeNode leftNode = new TreeNode(this);
                    TreeWrap leftWrap = new TreeWrap(node.getId(), this.getInfo().getNode());
                    leftNode.setInfo(leftWrap);
                    this.setlLink(leftNode);
                    return;
            case 1: TreeNode middleNode = new TreeNode(this);
                    TreeWrap middleWrap = new TreeWrap(node.getId(), this.getInfo().getNode());
                    middleNode.setInfo(middleWrap);
                    this.setmLink(middleNode);
                    return;
            case 2: TreeNode rightNode = new TreeNode(this);
                    TreeWrap rightWrap = new TreeWrap(node.getId(), this.getInfo().getNode());
                    rightNode.setInfo(rightWrap);
                    this.setrLink(rightNode);
                    return;
        }
    }

}
