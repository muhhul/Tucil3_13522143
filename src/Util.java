import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Util {

    // Membaca data dari txt lalu dipindahkan ke dataMap
     public static Map<String, Integer> getDataFromTxt(String namaFile) throws IOException {
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

    // Mengembalikan data yang sudah di seleksi berdasarkan syarat
    public static Map<String, Integer> getDataForQueue(Map<String, Integer> kataMap, String kataCek,
            String kataFinal, int panjangKataCek, int mode, int depth) {
        Map<String, Integer> kataYangSama = new HashMap<>();
        
        for (Map.Entry<String, Integer> entry : kataMap.entrySet()) {
            String kata = entry.getKey();
            int panjangKata = entry.getKey().length();
            
            // Memvalidasi apakah kata sekarang panjangnya sama dengan kata yang akan di cek
            if (panjangKata == panjangKataCek && !kata.equals(kataCek)) {
                int hurufSama = 0;
                for (int i = 0; i < panjangKataCek; i++) {
                    if (kata.charAt(i) == kataCek.charAt(i)) {
                        hurufSama++;
                    }
                }

                // Memvalidasi untuk algoritma UCS
                if (mode == 1) {
                    if (hurufSama == (panjangKata - 1)) {
                        if (kata.equals(kataFinal)) {
                            Map<String, Integer> kataYangSama1 = new HashMap<>();
                            kataYangSama1.put(kata, 0);
                            return kataYangSama1;
                        }
                        kataYangSama.put(kata, depth + 1);
                    }
                } 
                
                // Memvalidasi untuk algoritma Greedy BFS
                else if (mode == 2) {
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
                } 
                
                // Memvalidasi untuk algoritma A*
                else {
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
                        kataYangSama.put(kata, panjangKata - hurufSama + depth);
                    }
                }
            }
        }
        return kataYangSama;
    }

    // Mengembalikan data yang sudah di seleksi berdasarkan panjang kata
    public static Map<String, Integer> getDataWithLength(Map<String, Integer> kataMap, String kataCek) {
        Map<String, Integer> kataYangSama = new HashMap<>();

        for (Map.Entry<String, Integer> entry : kataMap.entrySet()) {
            String kata = entry.getKey();
            int panjangKata = entry.getKey().length();

            // Memvalidasi panjang kata
            if (panjangKata == kataCek.length()) {
                kataYangSama.put(kata, 0);
            }
        }
        return kataYangSama;
    }
}
