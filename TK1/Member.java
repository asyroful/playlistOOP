public class Member extends User {

    public Member(String nama) {
        super(nama);
    }

    @Override
    public void tampilkanAkses() {
        System.out.println("Member: " + nama + " memiliki akses untuk MELIHAT dan MENCARI lagu di playlist.");
    }

    public void lihatDaftarLagu(Lagu[] playlist) {
        System.out.println("===== Daftar Lagu di Playlist =====");
        boolean adaLagu = false;
        int nomor = 1;
        for (Lagu lagu : playlist) {
            if (lagu != null) {
                System.out.println("Lagu #" + nomor);
                lagu.tampilkanInfo();
                nomor++;
                adaLagu = true;
            }
        }
        if (!adaLagu) {
            System.out.println("Playlist masih kosong.");
        }
    }

    public void cariLagu(Lagu[] playlist, String judulCari) {
        for (Lagu lagu : playlist) {
            if (lagu != null && lagu.getJudul().equalsIgnoreCase(judulCari)) {
                System.out.println("Lagu ditemukan!");
                lagu.tampilkanInfo();
                return;
            }
        }
        System.out.println("Lagu dengan judul \"" + judulCari + "\" tidak ditemukan.");
    }

    public double hitungRataRataDurasi(Lagu[] playlist) {
        double totalDurasi = 0;
        int jumlahLagu = 0;
        for (Lagu lagu : playlist) {
            if (lagu != null) {
                totalDurasi += lagu.getDurasi();
                jumlahLagu++;
            }
        }
        if (jumlahLagu == 0) {
            return 0;
        }
        return totalDurasi / jumlahLagu;
    }
}
