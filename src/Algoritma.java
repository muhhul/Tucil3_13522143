import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Algoritma {
    public static void UCS(Map<String, Integer> dataMap, String awal, String akhir) {
        PriorityQueue<Map.Entry<String, Integer>> mapPriorityQueue = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue());
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>(awal, 0);
        mapPriorityQueue.add(entry);
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(awal, true);
        Graph graph = new Graph();
        graph.addVertex(awal);

        while (!mapPriorityQueue.isEmpty()) {
            Map.Entry<String, Integer> temp = mapPriorityQueue.poll();
            String currentQueue = temp.getKey();
            Map<String, Integer> dataMap2 = Util.getDataForQueue(dataMap, currentQueue, akhir, awal.length(), 1,
                    temp.getValue());
            for (Map.Entry<String, Integer> input : dataMap2.entrySet()) {
                if (input.getKey().equals(akhir)) {
                    mapPriorityQueue.clear();
                    graph.addVertex(input.getKey());
                    graph.addEdge(currentQueue, input.getKey());
                    graph.printPath(awal, input.getKey());
                    break;
                }
                if (!visited.containsKey(input.getKey())) {
                    visited.put(input.getKey(), true);
                    mapPriorityQueue.add(input);
                    graph.addVertex(input.getKey());
                    graph.addEdge(currentQueue, input.getKey());
                }
            }
        }
    }

    public static void GBFS(Map<String, Integer> dataMap, String awal, String akhir) {
        PriorityQueue<Map.Entry<String, Integer>> mapPriorityQueue = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue());
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>(awal, 0);
        mapPriorityQueue.add(entry);
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(awal, true);
        Graph graph = new Graph();
        graph.addVertex(awal);

        while (!mapPriorityQueue.isEmpty()) {
            Map.Entry<String, Integer> temp = mapPriorityQueue.poll();
            mapPriorityQueue.clear();
            String currentQueue = temp.getKey();
            Map<String, Integer> dataMap2 = Util.getDataForQueue(dataMap, currentQueue, akhir, awal.length(), 2,
                    temp.getValue());
            for (Map.Entry<String, Integer> input : dataMap2.entrySet()) {
                if (input.getKey().equals(akhir)) {
                    mapPriorityQueue.clear();
                    graph.addVertex(input.getKey());
                    graph.addEdge(currentQueue, input.getKey());
                    graph.printPath(awal, input.getKey());
                    break;
                }
                if (!visited.containsKey(input.getKey())) {
                    visited.put(input.getKey(), true);
                    mapPriorityQueue.add(input);
                    graph.addVertex(input.getKey());
                    graph.addEdge(currentQueue, input.getKey());
                }
            }
        }
    }

    public static void ABINTANG(Map<String, Integer> dataMap, String awal, String akhir) {
        PriorityQueue<Map.Entry<String, Integer>> mapPriorityQueue = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue());
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>(awal, 0);
        mapPriorityQueue.add(entry);
        Map<String, Integer> visited = new HashMap<>();
        visited.put(awal, 0);
        Graph graph = new Graph();
        graph.addVertex(awal);

        while (!mapPriorityQueue.isEmpty()) {

            Map.Entry<String, Integer> temp = mapPriorityQueue.poll();
            String currentQueue = temp.getKey();
            Map<String, Integer> dataMap2 = Util.getDataForQueue(dataMap, currentQueue, akhir, awal.length(), 3,
                    visited.get(currentQueue));
            for (Map.Entry<String, Integer> input : dataMap2.entrySet()) {
                if (input.getKey().equals(akhir)) {
                    mapPriorityQueue.clear();
                    graph.addVertex(input.getKey());
                    graph.addEdge(currentQueue, input.getKey());
                    graph.printPath(awal, input.getKey());
                    break;
                }
                if (!visited.containsKey(input.getKey())) {
                    visited.put(input.getKey(), visited.get(currentQueue) + 1);
                    mapPriorityQueue.add(input);
                    graph.addVertex(input.getKey());
                    graph.addEdge(currentQueue, input.getKey());
                }
            }
        }
    }

}
