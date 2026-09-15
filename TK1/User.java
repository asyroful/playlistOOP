public class User {
    protected String nama;

    public User(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void tampilkanAkses() {
        System.out.println(nama + " adalah pengguna umum sistem playlist.");
    }
}
