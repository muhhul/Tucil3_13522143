import java.util.*;

class Graph {
    private final Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String source, String destination) {
        adjacencyList.get(source).add(destination);
    }

    public List<String> getAdjacentVertices(String vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

    public void printPath(String start, String end) {
        List<String> visited = new ArrayList<>();
        visited.add(start);
        printPathUtil(start, end, visited);
    }

    private void printPathUtil(String current, String end, List<String> visited) {
        if (current.equals(end)) {
            int i =0;
            for (String path : visited){
                if(path.equals(end)){
                    System.out.println(path);
                }else{
                    System.out.print(path + " -> ");
                }
            }
            return;
        }

        for (String next : getAdjacentVertices(current)) {
            if (!visited.contains(next)) {
                visited.add(next);
                printPathUtil(next, end, visited);
                visited.remove(visited.size() - 1);
            }
        }
    }
}
