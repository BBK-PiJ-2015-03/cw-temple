    package student.NodeTree;

    import game.NodeStatus;

    /**
     * Created by J2FX on 04/02/2016.
     */
    public class ExploreTreeNode implements TreeNode {
        private TreeWrap info;
        private ExploreTreeNode parentNode;
        private ExploreTreeNode lLink = null, mLink = null, rLink = null;

        public ExploreTreeNode(TreeNode parent){
            setParentNode(parent);
        }

        public TreeNode getParentNode() {
            return parentNode;
        }

        public void setParentNode(TreeNode parentNode) {
            this.parentNode = (ExploreTreeNode) parentNode;
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
            this.lLink = (ExploreTreeNode) lLink;
        }

        public void setrLink(TreeNode rLink) {
            this.rLink = (ExploreTreeNode) rLink;
        }

        public void setmLink(TreeNode mLink) {
            this.mLink = (ExploreTreeNode) mLink;
        }

        public void add(NodeStatus node, int i){
            switch (i){
                case 0: TreeNode leftNode = new ExploreTreeNode(this);
                    TreeWrap leftWrap = new ExploreTreeWrap(node.getId(), this.getInfo().getNode());
                    leftNode.setInfo(leftWrap);
                    this.setlLink(leftNode);
                    return;
                case 1: TreeNode middleNode = new ExploreTreeNode(this);
                    TreeWrap middleWrap = new ExploreTreeWrap(node.getId(), this.getInfo().getNode());
                    middleNode.setInfo(middleWrap);
                    this.setmLink(middleNode);
                    return;
                case 2: TreeNode rightNode = new ExploreTreeNode(this);
                    TreeWrap rightWrap = new ExploreTreeWrap(node.getId(), this.getInfo().getNode());
                    rightNode.setInfo(rightWrap);
                    this.setrLink(rightNode);
                    return;
            }
        }
    }
