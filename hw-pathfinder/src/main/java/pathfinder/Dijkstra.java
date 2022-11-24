package pathfinder;
import java.util.*;

import pathfinder.datastructures.Path;
import graph.*;

public class Dijkstra {

    //This class does not represent an ADT

    /**
     * @spec.requires start and dest must be nodes in g
     * @spec.requires all edges in g are non negative
     * @param g the Graph with node type T and edge type Double, to search
     *          The edges of the graph must be the non-negative weights which represent
     *          the cost of traversing this edge
     * @param start The starting node in the graph
     * @param dest The destination node for which to find a path to
     * @param <T> The type of the node
     * @return returns a Path which represents least costly path from start to dest.
     *         returns null if no path exists between start and dest
     */
    //T is the node's type
    public static <T> Path<T> findPath(Graph<T, Double> g, T start, T dest){


        // Dijkstra's algorithm assumes a graph with non-negative edge weights.
        Queue<Path<T>> active = new PriorityQueue<>(Comparator.comparingDouble(Path::getCost));
        Path<T> currPath = new Path<>(start);

        // Each element is a path from start to a given node.
        // A path's “priority” in the queue is the total cost of that path.
        // Nodes for which no path is known yet are not in the queue.
        Set<T> finished = new HashSet<>();
        //finished.add(start);


        // Initially we only know of the path from start to itself, which has
        // a cost of zero because it contains no edges.
        active.add(currPath);

        while (!active.isEmpty()) {


            // minPath is the lowest-cost path in active and,
            // if minDest isn't already 'finished,' is the
            // minimum-cost path to the node minDest
            Path<T> minPath = active.remove();
            T minDest = minPath.getEnd();

            if (minDest.equals(dest)) {
                return minPath;
            }

            if (!finished.contains(minDest)){

                for (T child : g.listChildren(minDest)){
                    // For all children of minDest
                    for (Double edgeLabel : g.getLabel(minDest, child)) {
                        // If we don't know the minimum-cost path from start to child,
                        // examine the path we've just found
                        if (!finished.contains(child)){
                            Path<T> newPath = minPath.extend(child, edgeLabel);
                            //add newPath to active
                            active.add(newPath);
                        }



                    }
                }
                //add minDest to finished
                finished.add(minDest);
            }



        }
        // If the loop terminates, then no path exists from start to dest.
        // The implementation will indicate this to the client by returning null

        return null;
    }
}