import java.util.*;

class Graph {
    private final Map<String, List<String>> adjacencyList;

    // Construktor graph
    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Menambahkan node ke simpul
    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Menambahkan hubungan antar node
    public void addEdge(String source, String destination) {
        adjacencyList.get(source).add(destination);
    }

    // Mendapatkan tetangga dari suatu node
    public List<String> getAdjacentVertices(String vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

    // Mencetak path dari awal ke akhir
    public void printPath(String start, String end) {
        List<String> visited = new ArrayList<>();
        visited.add(start);
        System.out.println("Path: ");
        printPathUtil(start, end, visited);
    }

    // Fungsi rekursif untuk mendapatkan path
    private void printPathUtil(String current, String end, List<String> visited) {
        if (current.equals(end)) {
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
