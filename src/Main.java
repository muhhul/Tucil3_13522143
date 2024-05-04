import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        String namaFile = "data\\words_alpha.txt";

        try {
            long startTime = System.currentTimeMillis();
            Map<String, Integer> dataMap = bacaDanPindahkanKeMap(namaFile);
            dataMap = ambilKataYangPanjangSama(dataMap, "block");
            Map<String, Boolean> visited = new HashMap<>();
            // GBFS(dataMap, "block", "queen", visited);
            // UCS(dataMap, "block", "queen");
            // ABINTANG(dataMap, "forest", "yellow");

            long endTime = System.currentTimeMillis(); 
            long executionTime = endTime - startTime;

            System.out.println("Waktu Eksekusi Program: " + executionTime + " ms");

        } catch (IOException e) {
            System.err.println("Gagal membaca file: " + e.getMessage());
        }

    }

    public static Map<String, Integer> bacaDanPindahkanKeMap(String namaFile) throws IOException {
        Map<String, Integer> dataMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String isiBaris = line.trim();
                dataMap.put(isiBaris, 0);
            }
        }

        return dataMap;
    }

    public static Map<String, Integer> ambilKataYangSama(Map<String, Integer> kataMap, String kataCek,
            String kataFinal, int panjangKataCek, int mode, int depth) {
        Map<String, Integer> kataYangSama = new HashMap<>();

        for (Map.Entry<String, Integer> entry : kataMap.entrySet()) {
            String kata = entry.getKey();
            int panjangKata = entry.getKey().length();

            // Memeriksa apakah panjang kata sama dan kata tidak sama dengan kataCek
            if (panjangKata == panjangKataCek && !kata.equals(kataCek)) {
                int hurufSama = 0;
                // Memeriksa jumlah huruf yang sama pada posisi yang sama
                for (int i = 0; i < panjangKataCek; i++) {
                    if (kata.charAt(i) == kataCek.charAt(i)) {
                        hurufSama++;
                    }
                }
                // Jika jumlah huruf yang sama lebih dari 1, tambahkan kata ke dalam map
                // kataYangSama
                if (mode == 1) {
                    if (hurufSama == (panjangKata - 1)) {
                        if (kata.equals(kataFinal)) {
                            Map<String, Integer> kataYangSama1 = new HashMap<>();
                            kataYangSama1.put(kata, 0);
                            return kataYangSama1;
                        }
                        kataYangSama.put(kata, depth + 1);
                    }
                } else if (mode == 2) {
                    if (hurufSama == (panjangKata - 1)) {
                        if (kata.equals(kataFinal)) {
                            Map<String, Integer> kataYangSama1 = new HashMap<>();
                            kataYangSama1.put(kata, 0);
                            return kataYangSama1;
                        }
                        hurufSama = 0;
                        for (int i = 0; i < panjangKataCek; i++) {
                            if (kata.charAt(i) == kataFinal.charAt(i)) {
                                hurufSama++;
                            }
                        }
                        kataYangSama.put(kata, panjangKata - hurufSama);
                    }
                } else {
                    if (hurufSama == (panjangKata - 1)) {
                        if (kata.equals(kataFinal)) {
                            Map<String, Integer> kataYangSama1 = new HashMap<>();
                            kataYangSama1.put(kata, 0);
                            System.out.println("halooo");
                            return kataYangSama1;
                        }
                        hurufSama = 0;
                        for (int i = 0; i < panjangKataCek; i++) {
                            if (kata.charAt(i) == kataFinal.charAt(i)) {
                                hurufSama++;
                            }
                        }
                        kataYangSama.put(kata, panjangKata - hurufSama + depth);
                    }
                }
            }
        }

        return kataYangSama;
    }

    public static Map<String, Integer> ambilKataYangPanjangSama(Map<String, Integer> kataMap, String kataCek) {
        Map<String, Integer> kataYangSama = new HashMap<>();

        for (Map.Entry<String, Integer> entry : kataMap.entrySet()) {
            String kata = entry.getKey();
            int panjangKata = entry.getKey().length();

            if (panjangKata == kataCek.length()) {
                kataYangSama.put(kata, 0);
            }
        }
        return kataYangSama;
    }

    public static int nilaiTerkecilMap(Map<String, Integer> dataMap) {
        int nilaiTerkecil = Integer.MAX_VALUE;

        for (int nilai : dataMap.values()) {
            if (nilai < nilaiTerkecil) {
                nilaiTerkecil = nilai;
            }
        }

        return nilaiTerkecil;
    }

    public static String cariMapBerdasarkanNilai(Map<String, Integer> dataMap, int nilaiCari) {
        for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            if (entry.getValue() == nilaiCari) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void GBFS(Map<String, Integer> dataMap, String awal, String akhir, Map<String, Boolean> visited) {
        System.out.println(awal);
        Map<String, Integer> dataMap2 = ambilKataYangSama(dataMap, awal, akhir, awal.length(), 2, 0);
        int cost = nilaiTerkecilMap(dataMap2);
        if (cost == 0) {
            System.out.println(akhir);
        } else {
            if (!visited.containsKey(cariMapBerdasarkanNilai(dataMap2, cost))) {
                visited.put(cariMapBerdasarkanNilai(dataMap2, cost), true);
                GBFS(dataMap, cariMapBerdasarkanNilai(dataMap2, cost), akhir, visited);
            } else {
                while (visited.containsKey(cariMapBerdasarkanNilai(dataMap2, cost))) {
                    dataMap2.remove(cariMapBerdasarkanNilai(dataMap2, cost));
                    cost = nilaiTerkecilMap(dataMap2);
                }
                if (cariMapBerdasarkanNilai(dataMap2, cost) == null) {
                    System.out.println("Tidak dapat");
                } else {
                    visited.put(cariMapBerdasarkanNilai(dataMap2, cost), true);
                    GBFS(dataMap, cariMapBerdasarkanNilai(dataMap2, cost), akhir, visited);
                }
            }
        }
    }

    public static void UCS(Map<String, Integer> dataMap, String awal, String akhir) {
        PriorityQueue<Map.Entry<String, Integer>> mapPriorityQueue = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue());
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>(awal, 0);
        mapPriorityQueue.add(entry);
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(awal, true);
        int i =0;

        while (!mapPriorityQueue.isEmpty()) {
            i++;
            Map.Entry<String, Integer> temp = mapPriorityQueue.poll();
            String currentQueue = temp.getKey();
            Map<String, Integer> dataMap2 = ambilKataYangSama(dataMap, currentQueue, akhir, awal.length(), 1,
                    temp.getValue());
            for (Map.Entry<String, Integer> input : dataMap2.entrySet()) {
                if (input.getKey().equals(akhir)) {
                    mapPriorityQueue.clear();
                    System.out.println(input.getValue());
                    System.out.println("ketemu");
                    System.out.println(i);
                    break;
                }
                if (!visited.containsKey(input.getKey())) {
                    visited.put(input.getKey(), true);
                    mapPriorityQueue.add(input);
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
        int i=0;
        while (!mapPriorityQueue.isEmpty()) {
            i++;
            Map.Entry<String, Integer> temp = mapPriorityQueue.poll();
            String currentQueue = temp.getKey();
            Map<String, Integer> dataMap2 = ambilKataYangSama(dataMap, currentQueue, akhir, awal.length(), 3,
                    visited.get(currentQueue));
            for (Map.Entry<String, Integer> input : dataMap2.entrySet()) {
                if (input.getKey().equals(akhir)) {
                    mapPriorityQueue.clear();
                    System.out.println(input.getValue());
                    System.out.println("ketemu");
                    System.out.println(i);
                    break;
                }
                if (!visited.containsKey(input.getKey())) {
                    visited.put(input.getKey(), visited.get(currentQueue) + 1);
                    mapPriorityQueue.add(input);
                }
            }
        }
    }

}
