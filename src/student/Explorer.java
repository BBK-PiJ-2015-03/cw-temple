

package student;

import game.EscapeState;
import game.ExplorationState;
import game.Node;
import game.NodeStatus;
import student.EscapeAlgorithm.Escape;
import student.ExploreTree.EscapeNode;
import student.ExploreTree.ExploreTreeNode;
import student.ExploreTree.ExploreTreeWrap;
import student.ExploreTree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class Explorer {
    private TreeNode root = null;
    private TreeNode pathTree = new ExploreTreeNode(null);
    private TreeNode parentNode = new ExploreTreeNode(null);
    private Set<Long> visitedNodes = new HashSet<>();
    private Stack<Node> solutionPath = new Stack<>();
    private long MAX_VALUE = Integer.MAX_VALUE;
    private Set<EscapeNode> escapeNodes = new HashSet<>();


    /**
     * Explore the cavern, trying to find the orb in as few steps as possible.
     * Once you find the orb, you must return from the function in order to pick
     * it up. If you continue to move after finding the orb rather
     * than returning, it will not count.
     * If you return from this function while not standing on top of the orb,
     * it will count as a failure.
     * <p>
     * There is no limit to how many steps you can take, but you will receive
     * a score bonus multiplier for finding the orb in fewer steps.
     * <p>
     * At every step, you only know your current tile's ID and the ID of all
     * open neighbor tiles, as well as the distance to the orb at each of these tiles
     * (ignoring walls and obstacles).
     * <p>
     * To get information about the current state, use functions
     * getCurrentLocation(),
     * getNeighbours(), and
     * getDistanceToTarget()
     * in ExplorationState.
     * You know you are standing on the orb when getDistanceToTarget() is 0.
     * <p>
     * Use function moveTo(long id) in ExplorationState to move to a neighboring
     * tile by its ID. Doing this will change state to reflect your new position.
     * <p>
     * A suggested first implementation that will always find the orb, but likely won't
     * receive a large bonus multiplier, is a depth-first search.
     *
     * @param state the information available at the current state
     */
    public void explore(ExplorationState state) {
        root = pathTree;
        parentNode = root;
        pathTree.setInfo(new ExploreTreeWrap(state.getCurrentLocation(), null));
        addNeighbours(state);
        moveTo(state);
    }

    /**
     * Adds current states neighbours to trinary-tree
     *
     * @param state
     */
    public void addNeighbours(ExplorationState state){
        List<NodeStatus> neighbours = closestNeighbour(state);
        if(pathTree.getInfo().getNode().equals(root.getInfo().getNode())) {
            pathTree.add(neighbours.get(0), 0);
            return;
        }
        for(int i = 0; i < neighbours.size(); i++) {
            if(neighbours.get(i).getId() != pathTree.getInfo().getNode()){
                pathTree.add(neighbours.get(i), i);
            }
        }
    }

    /**
     * Moves through the maze using the tree, prioritises shortest distance and unvisited neighbours.
     * @param state
     */
    public void moveTo(ExplorationState state){
        do {
            if(pathTree.getlLink() != null && !visitedNodes.contains(pathTree.getlLink().getInfo().getNode())){
                state.moveTo(pathTree.getlLink().getInfo().getNode());
                visitedNodes.add(pathTree.getlLink().getInfo().getNode());
                parentNode = pathTree;
                pathTree = pathTree.getlLink();
                pathTree.getInfo().setVisited(true);
                addNeighbours(state);
            } else if(pathTree.getmLink() != null && !visitedNodes.contains(pathTree.getmLink().getInfo().getNode())){
                state.moveTo(pathTree.getmLink().getInfo().getNode());
                visitedNodes.add(pathTree.getmLink().getInfo().getNode());
                parentNode = pathTree;
                pathTree = pathTree.getmLink();
                pathTree.getInfo().setVisited(true);
                addNeighbours(state);
            } else if(pathTree.getrLink() != null && !visitedNodes.contains(pathTree.getrLink().getInfo().getNode())){
                state.moveTo(pathTree.getrLink().getInfo().getNode());
                visitedNodes.add(pathTree.getrLink().getInfo().getNode());
                parentNode = pathTree;
                pathTree = pathTree.getrLink();
                pathTree.getInfo().setVisited(true);
                addNeighbours(state);
            } else {
                state.moveTo(backTrack());
            }
        }while (state.getDistanceToTarget() != 0);
    }

    /**
     * Backtrack using the tree until it reaches an unvisted neigbour
     * @return
     */
    public Long backTrack(){
        pathTree = parentNode;
        parentNode = pathTree.getParentNode();
        return pathTree.getInfo().getNode();
    }

    /**
     * Orders the current neighbours into a list by distance.
     * @param state
     * @return List of NodeStatus
     */
    public List<NodeStatus> closestNeighbour(ExplorationState state) {
        List<NodeStatus> closest = state.getNeighbours().stream().collect(Collectors.toList());
        Collections.sort(closest, NodeStatus::compareTo);
        return closest;
    }

    /**
     * Escape from the cavern before the ceiling collapses, trying to collect as much
     * gold as possible along the way. Your solution must ALWAYS escape before time runs
     * out, and this should be prioritized above collecting gold.
     * <p>
     * You now have access to the entire underlying graph, which can be accessed through EscapeState.
     * getCurrentNode() and getExit() will return you Node objects of interest, and getVertices()
     * will return a collection of all nodes on the graph.
     * <p>
     * Note that time is measured entirely in the number of steps taken, and for each step
     * the time remaining is decremented by the weight of the edge taken. You can use
     * getTimeRemaining() to get the time still remaining, pickUpGold() to pick up any gold
     * on your current tile (this will fail if no such gold exists), and moveTo() to move
     * to a destination node adjacent to your current node.
     * <p>
     * You must return from this function while standing at the exit. Failing to do so before time
     * runs out or returning from the wrong location will be considered a failed run.
     * <p>
     * You will always have enough time to escape using the shortest path from the starting
     * position to the exit, although this will not collect much gold.
     *
     * @param state the information available at the current state
     */
    public void escape(EscapeState state) {
        Escape start = new Escape();
        start.run(state);
    }






    /**
     * Below was my first few attempts, drove me mad and caused a stack overflow.
     * Rarely worked and have really stuggled with recursion.
     *
     * I have spent many days trying to get the escape state working and have failed,
     * Will attempt it again using various classes but do not have much time before due date.
     */
//    public void escape(EscapeState state) {
//        System.out.println(state.getVertices().size());
//        escapeNodes = state.getVertices()
//                                        .stream()
//                                        .map(node -> new EscapeNode(node, MAX_VALUE, false, null))
//                                        .collect(Collectors.toSet());
//
//        Stack<EscapeState> escapePath;
//        escapeNodes.stream()
//                         .filter(node -> state.getCurrentNode()
//                         .equals(node.getNode()))
//                         .forEach(node -> { state.getCurrentNode();
//                             for (Node neighbour : state.getCurrentNode().getNeighbours()) {
//                                node.setDistance(node.getNode().getEdge(neighbour).length);
//                                System.out.println(node.getDistance());
//                            }
//        });
//
//        nodeMapper(state.getCurrentNode(), state.getExit());
//
//
//
//
//    }
//
//    public void nodeMapper(Node state, Node exit){
//        for (Node neighbour : state.getNeighbours()){
//            updateEscapeNode(neighbour, state.getEdge(neighbour).length);
//        }
//        state = closestNeighbour(state);
//        if(!state.equals(exit)){
//            nodeMapper(state, exit);
//        }
//        return;
//    }
//
//    public void updateEscapeNode(Node node, long length){
//        escapeNodes.stream().filter(curNode -> node.equals(curNode.getNode())).forEach(curNode -> {
//            curNode.setDistance(length);
//            curNode.setVisited(true);
//        });
//    }
//
//    public Node closestNeighbour(Node node){
//        long min = Integer.MAX_VALUE, cur = 0;
//        Node returnNode = node;
//        for(Node neighbour : node.getNeighbours()) {
//            for (EscapeNode curNode : escapeNodes) {
//                if (curNode.equals(neighbour)) {
//                    cur = curNode.getDistance();
//                    if (cur < min) {
//                        min = cur;
//                        returnNode = curNode.getNode();
//                    }
//                }
//            }
//        }
//        return returnNode;
//    }



  /*      if(state.equals(target)){
            return escapeSolution;
        }
        for (ExplorationState neighbour : state.getNeighbours()) {
            if(state.getEdge(neighbour).length < timeRemaining){
                moveTo();
            }
        }
        escapeSolution.clear();
        return escapeSolution;
    }

   public List<Node> escapePlan(EscapeState state){
        root = pathTree;
        parentNode = root;
        pathTree.setInfo(new TreeWrap(state.getCurrentNode().getId(), null, state.getCurrentNode().getEdge(state.getCurrentNode()).length(), state.getCurrentNode().getTile().getGold()));
        List<Node> nodeList = new ArrayList<>();
        state.getCurrentNode().getEdge(state.getCurrentNode());
        do {
            if(pathTree.getlLink() != null && !visitedNodes.contains(pathTree.getlLink().getInfo().getNode())){
                state.moveTo(pathTree.getlLink().getInfo().getNode());
                visitedNodes.add(pathTree.getlLink().getInfo().getNode());
                parentNode = pathTree;
                pathTree = pathTree.getlLink();
                pathTree.getInfo().setVisited(true);
                addNeighbours(state);
            } else if(pathTree.getmLink() != null && !visitedNodes.contains(pathTree.getmLink().getInfo().getNode())){
                state.moveTo(pathTree.getmLink().getInfo().getNode());
                visitedNodes.add(pathTree.getmLink().getInfo().getNode());
                parentNode = pathTree;
                pathTree = pathTree.getmLink();
                pathTree.getInfo().setVisited(true);
                addNeighbours(state);
            } else if(pathTree.getrLink() != null && !visitedNodes.contains(pathTree.getrLink().getInfo().getNode())){
                state.moveTo(pathTree.getrLink().getInfo().getNode());
                visitedNodes.add(pathTree.getrLink().getInfo().getNode());
                parentNode = pathTree;
                pathTree = pathTree.getrLink();
                pathTree.getInfo().setVisited(true);
                addNeighbours(state);
            } else {
                state.moveTo(backTrack());
            }
        }while (state.getDistanceToTarget() != 0);
        return nodeList;
    }*/
}
