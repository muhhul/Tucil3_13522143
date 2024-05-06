import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String namaFile = "data\\words_alpha.txt";

        try {
            // Membaca file lalu memidahkannya ke dataMap
            Map<String, Integer> dataMap = Util.getDataFromTxt(namaFile);

            while (true) {
                // Meminta masukkan pengguna
                Scanner scanner = new Scanner(System.in);
                System.out.println("-------------------");
                System.out.println("Huruf harus kecil semua");
                System.out.print("Masukkan kata awal: ");
                String start = scanner.nextLine();
                System.out.print("Masukkan kata akhir: ");
                String end = scanner.nextLine();

                // Memvalidasi masukkan pengguna
                if (dataMap.containsKey(start) && dataMap.containsKey(end) && start.length() == end.length()) {
                    // Mensortir data berdasarkan panjang kata yang dinputkan pengguna
                    Map<String, Integer> newDataMap = Util.getDataWithLength(dataMap, start);

                    // Meminta masukkan pengguna
                    System.out.println("Jenis algoritma yang dapat dipilih: ");
                    System.out.println("1. UCS ");
                    System.out.println("2. Greedy BFS ");
                    System.out.println("3. A* ");
                    System.out.print("Masukkan algoritma yang ingin dipilih: ");
                    int algoritma = scanner.nextInt();

                    // Memvalidasi masukkan pengguna
                    while (true) {
                        if (algoritma > 0 && algoritma < 4) {
                            break;
                        } else {
                            System.out.println("Masukkan anda salah silahkan masukkan ulang ");
                            System.out.print("Masukkan algoritma yang ingin dipilih: ");
                            algoritma = scanner.nextInt();
                        }
                    }

                    // Menghitung waktu dan memanggil fungsi algoritma sesuai pilihan pengguna
                    long startTime = System.currentTimeMillis();
                    if (algoritma == 1) {
                        Algoritma.UCS(newDataMap, start, end);
                    } else if (algoritma == 2) {
                        Algoritma.GBFS(newDataMap, start, end);
                    } else {
                        Algoritma.ABINTANG(newDataMap, start, end);
                    }
                    long endTime = System.currentTimeMillis();
                    long executionTime = endTime - startTime;
                    System.out.println("Waktu Eksekusi Program: " + executionTime + " ms");

                } else {
                    System.out.println("Maaf masukkan anda tidak ada dalam kamus atau panjang kata tidak sama");
                }
            }

        } catch (IOException e) {
            System.err.println("Gagal membaca file: " + e.getMessage());
        }

    }
}
