import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private Map<V, Boolean> marked;
    private Map<V, V> edgeTo;
    private final V source;

    public DepthFirstSearch(MyGraph<V> graph, V source) {
        marked = new HashMap<>();
        edgeTo = new HashMap<>();
        this.source = source;
        dfs(graph, source);
    }

    private void dfs(MyGraph<V> graph, V vertex) {
        marked.put(vertex, true);
        for (V adjacent : graph.getAdjacencyList(vertex)) {
            if (!marked.containsKey(adjacent)) {
                edgeTo.put(adjacent, vertex);
                dfs(graph, adjacent);
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