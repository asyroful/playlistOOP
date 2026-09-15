import java.util.Scanner;

public class PlaylistArray {

    private Lagu[] playlist;
    private int jumlahLagu;
    private static final int KAPASITAS = 10;

    public PlaylistArray() {
        playlist = new Lagu[KAPASITAS];
        jumlahLagu = 0;
    }

    // Traversal: O(n) - menelusuri seluruh elemen array satu per satu
    public void tampilkanSemuaLagu() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
            return;
        }
        System.out.println("Daftar lagu saat ini:");
        for (int i = 0; i < jumlahLagu; i++) {
            System.out.printf("%d. %s - %s (%.2f menit)%n",
                    i + 1, playlist[i].getJudul(), playlist[i].getArtis(), playlist[i].getDurasi());
        }
    }

    // Insertion: O(1) - menambahkan elemen baru di akhir array yang belum penuh
    public void tambahLagu(Lagu laguBaru) {
        if (jumlahLagu >= KAPASITAS) {
            System.out.println("Playlist sudah penuh, tidak bisa menambah lagu.");
            return;
        }
        playlist[jumlahLagu] = laguBaru;
        jumlahLagu++;
        System.out.println("Lagu berhasil ditambahkan!");
    }

    // Deletion: O(n) - mencari index lagu lalu menggeser elemen setelahnya agar array tetap rapat
    public void hapusLagu(String judulCari) {
        int index = cariIndex(judulCari);
        if (index == -1) {
            System.out.println("Lagu dengan judul \"" + judulCari + "\" tidak ditemukan.");
            return;
        }
        for (int i = index; i < jumlahLagu - 1; i++) {
            playlist[i] = playlist[i + 1];
        }
        playlist[jumlahLagu - 1] = null;
        jumlahLagu--;
        System.out.println("Lagu \"" + judulCari + "\" berhasil dihapus.");
    }

    // Searching: O(n) - linear search, membandingkan judul satu per satu dari awal array
    public void cariLagu(String judulCari) {
        int index = cariIndex(judulCari);
        if (index == -1) {
            System.out.println("Lagu dengan judul \"" + judulCari + "\" tidak ditemukan.");
            return;
        }
        System.out.println("Lagu ditemukan!");
        playlist[index].tampilkanInfo();
    }

    private int cariIndex(String judulCari) {
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].getJudul().equalsIgnoreCase(judulCari)) {
                return i;
            }
        }
        return -1;
    }

    // Sorting: O(n^2) - bubble sort, membandingkan pasangan elemen bersebelahan berulang kali
    public void urutkanLaguBerdasarkanDurasi() {
        if (jumlahLagu < 2) {
            System.out.println("Tidak cukup lagu untuk diurutkan.");
            return;
        }

        System.out.println("Sebelum pengurutan:");
        tampilkanSemuaLagu();

        for (int i = 0; i < jumlahLagu - 1; i++) {
            for (int j = 0; j < jumlahLagu - 1 - i; j++) {
                if (playlist[j].getDurasi() > playlist[j + 1].getDurasi()) {
                    Lagu temp = playlist[j];
                    playlist[j] = playlist[j + 1];
                    playlist[j + 1] = temp;
                }
            }
        }

        System.out.println("\nSesudah pengurutan (durasi ascending):");
        tampilkanSemuaLagu();
    }

    public static void main(String[] args) {
        PlaylistArray playlistArray = new PlaylistArray();
        Scanner scanner = new Scanner(System.in);

        playlistArray.tambahLagu(new Lagu("Perfect", "Ed Sheeran", 4.23));
        playlistArray.tambahLagu(new Lagu("Shivers", "Ed Sheeran", 3.50));

        boolean lanjut = true;
        while (lanjut) {
            try {
                System.out.println("\n=== MENU PLAYLIST MUSIK ===");
                System.out.println("1. Tampilkan semua lagu");
                System.out.println("2. Tambah lagu baru");
                System.out.println("3. Hapus lagu berdasarkan judul");
                System.out.println("4. Cari lagu berdasarkan judul");
                System.out.println("5. Urutkan berdasarkan durasi");
                System.out.println("6. Keluar");
                System.out.print("Pilih menu: ");

                int pilihan = Integer.parseInt(scanner.nextLine().trim());

                switch (pilihan) {
                    case 1:
                        playlistArray.tampilkanSemuaLagu();
                        break;
                    case 2:
                        System.out.print("Masukkan judul lagu : ");
                        String judul = scanner.nextLine().trim();
                        System.out.print("Masukkan artis      : ");
                        String artis = scanner.nextLine().trim();
                        System.out.print("Masukkan durasi (menit): ");
                        double durasi = Double.parseDouble(scanner.nextLine().trim());
                        playlistArray.tambahLagu(new Lagu(judul, artis, durasi));
                        playlistArray.tampilkanSemuaLagu();
                        break;
                    case 3:
                        System.out.print("Masukkan judul lagu yang ingin dihapus: ");
                        String judulHapus = scanner.nextLine().trim();
                        playlistArray.hapusLagu(judulHapus);
                        break;
                    case 4:
                        System.out.print("Masukkan judul lagu yang ingin dicari: ");
                        String judulCari = scanner.nextLine().trim();
                        playlistArray.cariLagu(judulCari);
                        break;
                    case 5:
                        playlistArray.urutkanLaguBerdasarkanDurasi();
                        break;
                    case 6:
                        lanjut = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid, silakan pilih menu yang tersedia.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, harus berupa angka.");
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }

        System.out.println("Program selesai. Terima kasih!");
        scanner.close();
    }
}
