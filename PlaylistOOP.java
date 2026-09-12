import java.util.Scanner;

public class PlaylistOOP {

    private static Lagu[] playlist = new Lagu[10];
    private static Scanner scanner = new Scanner(System.in);

    private static boolean sebagaiAdmin = true;
    private static Admin admin = new Admin("Admin");
    private static Member member = new Member("Member");

    public static void main(String[] args) {
        admin.tambahLagu(playlist, new Lagu("Bohemian Rhapsody", "Queen", 5.55));
        admin.tambahLagu(playlist, new Lagu("Shape of You", "Ed Sheeran", 3.53));
        admin.tambahLagu(playlist, new Lagu("Blinding Lights", "The Weeknd", 3.20));

        boolean lanjut = true;
        while (lanjut) {
            try {
                tampilkanMenu();
                int pilihan = bacaPilihanMenu();
                lanjut = prosesPilihan(pilihan);
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
                System.out.println("Silakan coba lagi.\n");
            }
        }

        System.out.println("Program selesai. Terima kasih!");
        scanner.close();
    }

    private static void tampilkanMenu() {
        String roleAktif = sebagaiAdmin ? "Admin" : "Member";
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          MENU PLAYLIST OOP            ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ Login sebagai : " + roleAktif);
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 1. Tampilkan hak akses user aktif");
        System.out.println("║ 2. Tambah lagu (khusus Admin)");
        System.out.println("║ 3. Lihat daftar lagu");
        System.out.println("║ 4. Cari lagu berdasarkan judul (khusus Member)");
        System.out.println("║ 5. Hitung rata-rata durasi lagu (khusus Member)");
        System.out.println("║ 6. Ganti user (Admin <-> Member)");
        System.out.println("║ 0. Keluar");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("Pilih menu: ");
    }

    private static int bacaPilihanMenu() {
        try {
            int pilihan = Integer.parseInt(scanner.nextLine().trim());
            return pilihan;
        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka!");
            return -1;
        }
    }

    private static boolean prosesPilihan(int pilihan) {
        switch (pilihan) {
            case 1:
                cetakHeaderHasil();
                if (sebagaiAdmin) {
                    admin.tampilkanAkses();
                } else {
                    member.tampilkanAkses();
                }
                cetakFooterHasil();
                break;
            case 2:
                menuTambahLagu();
                break;
            case 3:
                cetakHeaderHasil();
                member.lihatDaftarLagu(playlist);
                cetakFooterHasil();
                break;
            case 4:
                menuCariLagu();
                break;
            case 5:
                cetakHeaderHasil();
                double rataRata = member.hitungRataRataDurasi(playlist);
                System.out.printf("Rata-rata durasi lagu dalam playlist: %.2f menit%n", rataRata);
                cetakFooterHasil();
                break;
            case 6:
                cetakHeaderHasil();
                gantiUser();
                cetakFooterHasil();
                break;
            case 0:
                return false;
            default:
                cetakHeaderHasil();
                System.out.println("Pilihan tidak valid, silakan pilih menu yang tersedia.");
                cetakFooterHasil();
        }
        return true;
    }

    private static void cetakHeaderHasil() {
        System.out.println();
        System.out.println("---------------- HASIL ----------------");
    }

    private static void cetakFooterHasil() {
        System.out.println("----------------------------------------");
    }

    private static void menuTambahLagu() {
        if (!sebagaiAdmin) {
            cetakHeaderHasil();
            System.out.println("Akses ditolak! Hanya Admin yang dapat menambah lagu. Gunakan menu 6 untuk ganti user.");
            cetakFooterHasil();
            return;
        }

        System.out.print("Masukkan judul lagu: ");
        String judul = scanner.nextLine().trim();

        System.out.print("Masukkan nama artis: ");
        String artis = scanner.nextLine().trim();

        double durasi;
        try {
            System.out.print("Masukkan durasi lagu (menit, contoh 3.45): ");
            durasi = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            cetakHeaderHasil();
            System.out.println("Durasi tidak valid, harus berupa angka. Lagu batal ditambahkan.");
            cetakFooterHasil();
            return;
        }

        cetakHeaderHasil();
        admin.tambahLagu(playlist, new Lagu(judul, artis, durasi));
        cetakFooterHasil();
    }

    private static void menuCariLagu() {
        if (sebagaiAdmin) {
            cetakHeaderHasil();
            System.out.println("Akses ditolak! Hanya Member yang dapat mencari lagu. Gunakan menu 6 untuk ganti user.");
            cetakFooterHasil();
            return;
        }

        System.out.print("Masukkan judul lagu yang ingin dicari: ");
        String judulCari = scanner.nextLine().trim();

        cetakHeaderHasil();
        member.cariLagu(playlist, judulCari);
        cetakFooterHasil();
    }

    private static void gantiUser() {
        sebagaiAdmin = !sebagaiAdmin;
        String roleBaru = sebagaiAdmin ? "Admin" : "Member";
        System.out.println("Berhasil beralih ke role: " + roleBaru);
    }
}
