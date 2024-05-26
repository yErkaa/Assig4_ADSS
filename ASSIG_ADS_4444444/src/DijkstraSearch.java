import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<V, Double> distTo;
    private Map<V, V> edgeTo;
    private PriorityQueue<V> pq;
    private Map<V, Vertex<V>> vertexMap;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        vertexMap = graph.getVertices();
        pq = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));

        // Initialize distTo map
        for (V vertex : vertexMap.keySet()) {
            distTo.put(vertex, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);

        pq.add(source);

        while (!pq.isEmpty()) {
            V vertex = pq.poll();
            for (Map.Entry<Vertex<V>, Double> entry : vertexMap.get(vertex).getAdjacentVertices().entrySet()) {
                V adjacentVertex = entry.getKey().getData();
                relax(vertex, adjacentVertex, entry.getValue());
            }
        }
    }

    private void relax(V u, V v, double weight) {
        double newDist = distTo.get(u) + weight;
        if (newDist < distTo.get(v)) {
            distTo.put(v, newDist);
            edgeTo.put(v, u);
            pq.add(v);
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return distTo.get(vertex) < Double.POSITIVE_INFINITY;
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