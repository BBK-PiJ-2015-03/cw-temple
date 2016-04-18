package student.EscapeAlgorithm;

import game.EscapeState;
import game.Node;
import game.Tile;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by J2FX on 18/04/2016.
 */
public class Escape {
    private Vertex startingVertex;
    private Vertex currentVertex;
    private Set<Vertex> visited = new HashSet<>();
    private Set<Vertex> unvisited = new HashSet<>();
    private Set<Vertex> all = new HashSet<>();
    private Stack<Node> route = new Stack<>();

    public void run(EscapeState state){
        all.addAll(state.getVertices().stream().map(Vertex::new).collect(Collectors.toList()));
        all.stream().forEach(v -> unvisited.add(v));
        startingVertex = findVertex(state.getCurrentNode());
        startingVertex.setCost(0);
        currentVertex = startingVertex;
        Vertex endVertex = findVertex(state.getExit());
        while (!visited.contains(endVertex)) {
            for (Node n: currentVertex.getNode().getNeighbours()) {
                Vertex v = findVertex(n);
                if (!visited.contains(v) && unvisited.contains(v)) {
                    long tentativeCost = currentVertex.getCost() + currentVertex.getNode().getEdge(n).length();
                    if (v.getCost() > tentativeCost) {
                        v.setCost(tentativeCost);
                    }
                }
            }
            visited.add(currentVertex);
            unvisited.remove(currentVertex);
            Vertex temp = unvisited.stream().sorted(Vertex::compareTo).findFirst().get();
            if (temp.getCost() == Long.MAX_VALUE) {
                break;
            } else {
                currentVertex = temp;
            }
        }
        System.out.println("end vertex cost: " + endVertex.getCost());
        calculateRoute(state);
        moveToExit(state);
    }

    private void calculateRoute(EscapeState state){
        Vertex current = findVertex(state.getExit());
        route.push(state.getExit());
        while (current.getCost() > 0) {
            Set<Vertex> neighbours = new HashSet<>();
            for (Node n : current.getNode().getNeighbours()) {
                Vertex v = findVertex(n);
                neighbours.add(v);
            }
            Vertex cheapest = cheapestVertex(neighbours);
            route.push(cheapest.getNode());
            current = cheapest;
        }
        System.out.println("Finished Calculating Route Method");
    }



    private void moveToExit(EscapeState state){
        route.pop();
        do{
            state.moveTo(route.pop());
            Tile tile = state.getCurrentNode().getTile();
            if(tile.getGold() > 0) {
                state.pickUpGold();
            }
        }while(!route.isEmpty());
    }

    public Vertex findVertex(Node toFind){
        for (Vertex v: all) {
            if(v.getNode().equals(toFind)){
                return v;
            }
        }
        return null; // Should not get to here - but required a return.
    }

    public Vertex cheapestVertex(Set<Vertex> vertices){
        Vertex result = vertices.stream().sorted(Vertex::compareTo).findFirst().get();
        return result;
    }
}
