import java.util.*;

public class MyGraph<V> {
    private Map<V, List<V>> adjacencyList;
    private boolean directed;

    public MyGraph(boolean directed) {
        this.adjacencyList = new HashMap<>();
        this.directed = directed;
    }

    public void addEdge(V source, V destination) {
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(destination, new ArrayList<>());
        adjacencyList.get(source).add(destination);
        if (!directed) {
            adjacencyList.get(destination).add(source);
        }
    }

    public List<V> getAdjacencyList(V vertex) {
        return adjacencyList.get(vertex);
    }

    public Set<V> getVertices() {
        return adjacencyList.keySet();
    }
}