public class Admin extends User {

    public Admin(String nama) {
        super(nama);
    }

    @Override
    public void tampilkanAkses() {
        System.out.println("Admin: " + nama + " memiliki akses untuk MENAMBAH lagu ke playlist.");
    }

    public boolean tambahLagu(Lagu[] playlist, Lagu laguBaru) {
        for (int i = 0; i < playlist.length; i++) {
            if (playlist[i] == null) {
                playlist[i] = laguBaru;
                System.out.println("Lagu \"" + laguBaru.getJudul() + "\" berhasil ditambahkan oleh Admin " + nama + ".");
                return true;
            }
        }
        System.out.println("Gagal menambahkan lagu, playlist sudah penuh!");
        return false;
    }
}
