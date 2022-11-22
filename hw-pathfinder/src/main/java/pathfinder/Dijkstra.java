package pathfinder;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.List;
import pathfinder.datastructures.Path;
import graph.*;

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

            List<T> children = graph.listChildren(mindDest);
            for (T child: children) {
                if (!finished.contains(child)) {
                    Double label = graph.getLabel(mindDest, child);
                    Path<T> newPath = minPath.extend(child, label);
                    active.add(newPath);
                }
            }
            finished.add(mindDest);
        }
        return null;
    }
}
