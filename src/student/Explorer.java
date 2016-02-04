package student;

import game.EscapeState;
import game.ExplorationState;
import game.NodeStatus;

import java.util.*;
import java.util.stream.Collectors;

public class Explorer {
    private Set<NodeStatus> visitedPositions = new HashSet<>();
    private Set<NodeStatus> revisitedPositions = new HashSet<>();
    private Set<NodeStatus> deadEnd = new HashSet<>();
    private Stack<Long> pathTaken = new Stack<>();
    private Long lastPopped = null;
    private int repeatCount = 0;

    /**
     * Checks if a neighbour has been visited already.
     *
     * @param neighbour to check
     * @return boolean true if neighbour has been visited, false if neighbour has not been visited.
     */
    public boolean visitedCheck(NodeStatus neighbour){
        return visitedPositions.contains(neighbour);
    }

    /**
     * Checks if a neighbour has been visited twice.
     *
     * @param neighbour to check
     * @return boolean true if neighbour has been visited twice, false if neighbour has not been visited twice.
     */
    public boolean visitedTwiceCheck(NodeStatus neighbour){
        return revisitedPositions.contains(neighbour);
    }

    /**
     * Checks if a neighbour has been marked as a dead end.
     *
     * @param neighbour to check
     * @return boolean true if neighbour is dead end, false if neighbour has not a dead end.
     */
    public boolean deadEndCheck(NodeStatus neighbour){
        return deadEnd.contains(neighbour);
    }

    /**
     * Explore the cavern, trying to find the
     * orb in as few steps as possible. Once you find the
     * orb, you must return from the function in order to pick
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
     * In order to get information about the current state, use functions
     * getCurrentLocation(), getNeighbours(), and getDistanceToTarget() in ExplorationState.
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
       do{
           exploreSolution(state);
       }while(state.getDistanceToTarget() != 0);
    }

    /**
     * Absolute mental solution, but it works
     *
     * Every for loops iterates through the current neighbours in order of closest to furthest

     * *** First Iteration ***
     * Checks if the neighbour is unvisited and is not a dead end (which shouldn't be if its unvisited but you never know).
     * if true it will add the neighbour to a HashSet of visitedPositions and move the explorer to that neighbour,
     * if false it will move on to the next neighbour and repeat.
     * if all neighbours have false conditions it will go to the second iteration.
     *
     * *** Second Iteration ***
     * Checks if a neighbour has not been visited twice and is not a dead end.
     * if true it will add to the repeatCount and also add the neighbour to a HashSet of revisitedPositions then move the explorer to that neighbour,
     * if false it will move on to the next neighbour and repeat.
     * if all neighbours have false conditions it will go to the third iteration.
     *
     * *** Third Iteration ***
     * Checks if a neighbour has been visited twice and is not a dead end, if true it will then check for the following 3 conditions;
     * If there is only one neighbour and if it has been visited twice, if true it will add the neighbour to a HashSet of deadEnd,
     * If there are two neighbours and both have been visited twice, if true it will add the neighbour to a HashSet of deadEnd,
     * If there are three neighbours and all have been visited twice, if true it will add the neighbour to a HashSet of deadEnd,
     * if true it will add to the repeatCount and also add the neighbour to a HashSet of revisitedPositions then move the explorer to that neighbour,
     * if false it will move on to the next neighbour and repeat.
     * if all neighbours have false conditions it will go to the fourth iteration.
     *
     * WILL CONTINUE THIS AFTER SOME SLEEP... 4.31AM !
     *
     * @param state of the explorer
     */
    public void exploreSolution(ExplorationState state) {
        for(NodeStatus neighbour : closestNeighbour(state)) {
            if (unvisitedNode(neighbour)) {
                visitedPositions.add(neighbour);
                pathTaken.push(neighbour.getId());
                state.moveTo(neighbour.getId());
                return;
            }
        }
        for(NodeStatus neighbour : closestNeighbour(state)) {
            if (visitedOnce(neighbour)) {
                //repeatCount++;
                revisitedPositions.add(neighbour);
                pathTaken.push(neighbour.getId());
                state.moveTo(neighbour.getId());
                return;
            }
        }
        for(NodeStatus neighbour : closestNeighbour(state)) {
            if (visitedTwice(neighbour)) {
                if(oneNeighbourVisitedTwice(state, neighbour)){
                    deadEnd.add(neighbour);
                }
                if(twoNeighboursVisitedTwice(state, neighbour)){
                    deadEnd.add(neighbour);
                }
                if(threeNeighboursVisitedTwice(state, neighbour)){
                    deadEnd.add(neighbour);
                }
                //repeatCount++;
                pathTaken.push(neighbour.getId());
                state.moveTo(neighbour.getId());
                return;
            }
        }

        backTrack(state);

       /* for(NodeStatus neighbour : closestNeighbour(state)) {
            boolean flag = false;
            if(oneNeighbourAndDeadEnd(state, neighbour)){
                backTrack(state);
                System.out.println("1");
            } else if(twoNeighbourAndDeadEnd(state, neighbour)){
                backTrack(state);
                System.out.println("2");
            } else if(threeNeighbourAndDeadEnd(state, neighbour)) {
                backTrack(state);
                System.out.println("3");
            }
        }*/


//        for(NodeStatus neighbour : closestNeighbour(state)) {
//            if(oneNeighbourAndDeadEnd(state, neighbour)){
//                for(int i = 0; i < repeatCount/2; i++){
//                    state.moveTo(closestNeighbour(state).get(0).getId());
//                    System.out.println("1");
//                }
//            }
//            if(twoNeighbourAndDeadEnd(state, neighbour)){
//                for(int i = 0; i < repeatCount/2; i++){
//                    state.moveTo(closestNeighbour(state).get(1).getId());
//                    System.out.println("2");
//                }
//            }
//            if(threeNeighbourAndDeadEnd(state, neighbour)) {
//                for (int i = 0; i < repeatCount/2; i++) {
//                    state.moveTo(closestNeighbour(state).get(2).getId());
//                    System.out.println("3");
//                }
//            }
//        }
    }

    public boolean unvisitedNeighbours(ExplorationState state){
        for(NodeStatus neighbour : closestNeighbour(state)) {
            if (unvisitedNode(neighbour)) {
                return true;
            }
        }
        return false;
    }

    public void backTrack(ExplorationState state){
        pathTaken.pop();
       // System.out.println("backtrack");
        do {
            /*if (earlierVisitInStack(state) != state.getCurrentLocation()) {
                pathTaken.push(earlierVisitInStack(state));
                state.moveTo(earlierVisitInStack(state));
                System.out.println("backtrack");
            } else {*/
                System.out.println();
                lastPopped = pathTaken.pop();
                state.moveTo(lastPopped);
          //  }
        }while (!unvisitedNeighbours(state));
            pathTaken.push(lastPopped);
      /*  closestNeighbour(state).stream().filter(neighbour -> closestNeighbour(state).size() == 1 && deadEndCheck(neighbour)).forEach(neighbour -> {
            state.moveTo(pathTaken.pop());
        });*/
    }

    public boolean unvisitedNode(NodeStatus neighbour){
        return (!visitedCheck(neighbour) && !deadEndCheck(neighbour));
    }

    public boolean visitedOnce(NodeStatus neighbour){
        return (!visitedTwiceCheck(neighbour) && !deadEndCheck(neighbour));
    }

    public boolean visitedTwice(NodeStatus neighbour){
        return (visitedTwiceCheck(neighbour) && !deadEndCheck(neighbour));
    }

    public boolean oneNeighbourVisitedTwice(ExplorationState state, NodeStatus neighbour){
        return (closestNeighbour(state).size() == 1 && visitedTwiceCheck(neighbour));
    }

    public boolean twoNeighboursVisitedTwice(ExplorationState state, NodeStatus neighbour) {
        return (closestNeighbour(state).size() == 2 && visitedTwiceCheck(neighbour)
                && visitedTwiceCheck(closestNeighbour(state).get(1)));
    }

    public boolean threeNeighboursVisitedTwice(ExplorationState state, NodeStatus neighbour) {
        return (closestNeighbour(state).size() == 2 && visitedTwiceCheck(neighbour)
                && visitedTwiceCheck(closestNeighbour(state).get(1)));
    }

    public boolean oneNeighbourAndDeadEnd(ExplorationState state, NodeStatus neighbour) {
        return (closestNeighbour(state).size() == 1 && deadEndCheck(neighbour));
    }

    public boolean twoNeighbourAndDeadEnd(ExplorationState state, NodeStatus neighbour) {
        return (closestNeighbour(state).size() == 2 && deadEndCheck(neighbour)
                && deadEndCheck(closestNeighbour(state).get(1)));
    }

    public boolean threeNeighbourAndDeadEnd(ExplorationState state, NodeStatus neighbour) {
        return (closestNeighbour(state).size() == 3 && deadEndCheck(neighbour)
                && deadEndCheck(closestNeighbour(state).get(1))
                && deadEndCheck(closestNeighbour(state).get(2)));
    }


    /**
     * Arranges closes neighbours in a List of NodeStatus, sorts this list by comparing neighbours distance to
     * target and arranges it in acceding order.
     *
     * @param state the explorer is currently at
     * @return a sorted list of closest neighbours.
     */
    public List<NodeStatus> closestNeighbour(ExplorationState state) {
        List<NodeStatus> closest = state.getNeighbours().stream().collect(Collectors.toList());
        Collections.sort(closest, NodeStatus::compareTo);
        return closest;
    }

    public List<Long> stackToList(){
        return pathTaken.stream().collect(Collectors.toList());
    }

    public Long earlierVisitInStack(ExplorationState state){
        for(int i = 0; i < stackToList().size()-1; i++){
            for (NodeStatus neighbour : state.getNeighbours()) {
                if(stackToList().get(i) == neighbour.getId()){
                    while (pathTaken.peek() != neighbour.getId()){
                        pathTaken.pop();
                    }
                    return neighbour.getId();
                }
            }
        }
        return state.getCurrentLocation();
    }

    /**
     * Clears all lists that track states, visited, revisited and dead end.
     * Also sets the repeat count back down to 0.
     */
    public void clearAllStateTracking(){
        visitedPositions.clear();
        revisitedPositions.clear();
        deadEnd.clear();
        repeatCount = 0;
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
        clearAllStateTracking();
        int initialTime = state.getTimeRemaining();
        System.out.println("");
        System.out.println("YUP");
        System.out.println("");

    }
}
