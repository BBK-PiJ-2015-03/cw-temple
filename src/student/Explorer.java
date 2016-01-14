package student;

import game.EscapeState;
import game.ExplorationState;
import game.NodeStatus;

import java.util.*;

public class Explorer {
    Set<NodeStatus> visitedPositions = new HashSet<>();
    Set<NodeStatus> revisitedPositions = new HashSet<>();
    int repeatCount = 0;



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
        /*
        Scenarios
          1: X Unvisited Neighbours - Go to neighbour closest to orb (only neighbour)
          2: 1 Neighbour - already visited, Go to neighbour
          3: 2 Neighbours - already visited, Go to the one visited earlier
          4: 3 Neighbours - already visited, Go to the one visited earlier
          5:
         */
       for(NodeStatus n : closestNeighbour(state)){
           System.out.println(n.getDistanceToTarget());
           state.moveTo(n.getId());
       }
        System.out.println("");

        for(NodeStatus n : closestNeighbour(state)) {
            System.out.println(n.getDistanceToTarget());
        }
    }

    public void solution(ExplorationState state) {
        List<NodeStatus> n = closestNeighbour(state);
            /*if (!visitedCheck(n)) {
                visitedPositions.add(n);
                state.moveTo(n.getId());
                return;
            }
            if (!visitedTwiceCheck(n)) {
                repeatCount++;
                revisitedPositions.add(n);
                state.moveTo(n.getId());
                return;
            }*/
        /*for (NodeStatus n : state.getNeighbours()) {
            if (visitedTwiceCheck(n) && visitedCheck(n)) {
                state.moveTo(revisitedPositions.get(revisitedPositions.size()-repeatCount).getId());
                System.out.println("VISITING LIST: " + repeatCount);
                return;
            }
        }*/

    }

    public List<NodeStatus> closestNeighbour(ExplorationState state) {
        int min = 9999;
        List<NodeStatus> closest = new ArrayList<>();
        for (NodeStatus n : state.getNeighbours()) {
            closest.add(n);
        }
        Collections.sort(closest, (id1, id2) -> id1.compareTo(id2));
        return closest;
    }

    public boolean visitedCheck(NodeStatus n){
        if(visitedPositions.contains(n)){
            return true;
        }
        return false;
    }

    public boolean visitedTwiceCheck(NodeStatus n){
        if(revisitedPositions.contains(n)){
            return true;
        }
        return false;
    }

   /* public boolean solution2(ExplorationState state){
        for (NodeStatus neighbour : state.getNeighbours()){
            System.out.println(neighbourToExplorationState(neighbour).getNeighbours().isEmpty());
              //  System.out.println(nneighbour.getId() + " " + nneighbour.getDistanceToTarget());
           // }
        }
        return true;
    }*/

   /* public boolean solution(ExplorationState state){
        prevPosition = state;
        boolean deadEnd = false;
        for (NodeStatus neighbour : state.getNeighbours()) {
            if(!visitedPositions.contains(neighbour)) {
                System.out.println(neighbour.getId() + " " + neighbour.getDistanceToTarget());
                visitedPositions.add(neighbour);
                if( == 1 && ((ExplorationState)neighbour).getNeighbours().contains(prevPosition)){
                    deadEnd = true;
                    return false;
                }
            }
        }
        if(state.getDistanceToTarget() == 0){
            System.out.println("Success");
            return true;
        }

        return false;
    } */



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
        //TODO: Escape from the cavern before time runs out
    }
}
