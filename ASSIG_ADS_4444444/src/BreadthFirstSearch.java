import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<V, Boolean> marked;
    private Map<V, V> edgeTo;
    private final V source;

    public BreadthFirstSearch(MyGraph<V> graph, V source) {
        marked = new HashMap<>();
        edgeTo = new HashMap<>();
        this.source = source;
        bfs(graph, source);
    }

    private void bfs(MyGraph<V> graph, V source) {
        Queue<V> queue = new LinkedList<>();
        queue.add(source);
        marked.put(source, true);

        while (!queue.isEmpty()) {
            V vertex = queue.poll();
            for (V adjacent : graph.getAdjacencyList(vertex)) {
                if (!marked.containsKey(adjacent)) {
                    queue.add(adjacent);
                    marked.put(adjacent, true);
                    edgeTo.put(adjacent, vertex);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return marked.containsKey(vertex);
    }

    @Override
    public List<V> pathTo(V vertex) {
        if (!hasPathTo(vertex)) return null;
        List<V> path = new LinkedList<>();
        for (V x = vertex; x != null; x = edgeTo.get(x)) {
            path.add(x);
        }
        Collections.reverse(path);
        return path;
    }
}