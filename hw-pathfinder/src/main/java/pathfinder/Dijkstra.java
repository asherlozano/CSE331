package pathfinder;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {
    public static <T> Path<T> dijkstra(Graph<T, Double> graph, T start, T dest) {
        PriorityQueue<Path<T>> active = new PriorityQueue<>(Comparator.comparing(Path<T> ::getCost));
        Path<T> path = new Path<>(start);
        Set<T> finished = new HashSet<>();

        active.add(path);
        while(!active.isEmpty()) {
            Path<T> minPath = active.remove();
            T mindDest = minPath.getEnd();
            if (mindDest.equals(dest)) {
                return minPath;
            }

            if (finished.contains(mindDest)) {
                continue;
            }

            HashSet<Graph.Edge<T, Double>> children = graph.listChildren(mindDest);
            for (Graph.Edge<T, Double> child: children) {
                if (!finished.contains(child)) {
                    Path<T> newPath = minPath.extend(child.getChild(), child.getLabel());
                    active.add(newPath);
                }
            }
            finished.add(mindDest);
        }
        return null;
    }
}
