import java.util.Scanner;

class Pasien {
    int ID;
    String NamaDepan;
    String NamaBelakang;
    int Usia;
    String GolonganDarah;
    char JenisKelamin;
    Pasien Next;

    
}

class AntrianTerhubung {
    Pasien Head;
    Pasien Last;

    boolean cari(int item) {
        if (Head == null) {
            return false;
        }

        Pasien p = Head;
        while (p.Next != null && p.ID != item) {
            p = p.Next;
        }

        return p.ID == item;
    }

    Pasien input() {
        Pasien p = new Pasien();
        Scanner scanner = new Scanner(System.in);
        int flag;

        System.out.println("\nSilakan masukkan data pasien");
        System.out.print("Nama depan: ");
        p.NamaDepan = scanner.nextLine();
        System.out.print("Nama belakang: ");
        p.NamaBelakang = scanner.nextLine();

        do {
            System.out.print("Golongan darah: ");
            p.GolonganDarah = scanner.nextLine();
            String[] golonganDarahValid = {"A+", "a+", "A-", "a-", "B+", "b+", "B-", "b-", "O+", "o+", "O-", "o-", "AB+", "ab+", "AB-", "ab-"};
            flag = 0;
            for (String golDarah : golonganDarahValid) {
                if (p.GolonganDarah.equals(golDarah)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                System.out.println("Golongan darah tidak valid. Silakan coba lagi.");
            }
        } while (flag == 0);

        System.out.print("Jenis kelamin (l/p): ");
        p.JenisKelamin = scanner.nextLine().charAt(0);
        System.out.print("Usia: ");
        p.Usia = scanner.nextInt();
        System.out.print("Nomor telepon: ");
        p.ID = scanner.nextInt();

        if (cari(p.ID)) {
            p.ID = 0;
            System.out.println("\nData tidak valid. Operasi dibatalkan.");
        }

        return p;
    }

    void output(Pasien p) {
        System.out.println("\n------------------------");
        System.out.println("Data pasien:");
        System.out.println("Nama Depan: " + p.NamaDepan);
        System.out.println("Nama Belakang: " + p.NamaBelakang);
        System.out.println("Jenis Kelamin: " + p.JenisKelamin);
        System.out.println("Usia: " + p.Usia);
        System.out.println("Golongan Darah: " + p.GolonganDarah);
        System.out.println("Nomor Telepon: " + p.ID);
        System.out.println("------------------------");
    }

    void tambahkanDiAwal() {
        Pasien p = input();
        if (p.ID == 0) {
            return;
        }

        if (Head == null) {
            Head = p;
            Last = p;
            p.Next = null;
        } else {
            p.Next = Head;
            Head = p;
        }

        clearScreen();
        System.out.println("\n\tPasien ditambahkan:");
        output(p);
    }

    void tambahkanDiAkhir() {
        Pasien p = input();
        if (p.ID == 0) {
            return;
        }

        if (Head == null) {
            Head = p;
            Last = p;
            p.Next = null;
        } else {
            p.Next = null;
            Last.Next = p;
            Last = p;
        }

        clearScreen();
        System.out.println("\nSistem Manajemen Rumah Sakit");
        System.out.println("------------------------");
        System.out.println("|--- PASIEN DITAMBAHKAN ---|");
        output(p);
    }

    void ambilPasienKeluar() {
        clearScreen();
        if (Head == null) {
            System.out.println("\nTidak ada pasien untuk dioperasikan");
        } else {
            Pasien p = Head;
            Head = p.Next;
            System.out.println("\nPasien untuk dioperasikan:");
            output(p);
        }
    }

    void daftarPasien() {
        clearScreen();
        if (Head == null) {
            System.out.println("\nTidak ada pasien");
            return;
        }

        Pasien p = Head;
        while (p != null) {
            output(p);
            p = p.Next;
        }
    }

    void menuDepartemen() {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        while (choice != 5) {
            clearScreen();
            System.out.println("\nSistem Manajemen Rumah Sakit");
            System.out.println("------------------------");
            System.out.println("|-- " + Head.NamaDepan + " --|");
            System.out.println("[1] Tambahkan pasien normal");
            System.out.println("[2] Tambahkan pasien kritis");
            System.out.println("[3] Bawa pasien ke Dokter");
            System.out.println("[4] Tampilkan daftar");
            System.out.println("[5] Ganti departemen atau keluar");
            System.out.print("\nSilakan masukkan pilihan Anda: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    tambahkanDiAkhir();
                    System.out.println("\nTekan sembarang tombol");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 2:
                    tambahkanDiAwal();
                    System.out.println("\nTekan sembarang tombol");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 3:
                    ambilPasienKeluar();
                    System.out.println("\nTekan sembarang tombol");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 4:
                    daftarPasien();
                    System.out.println("\nTekan sembarang tombol");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
            }
        }
    }

    void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

public class RumahSakit {
    public static void main(String[] args) {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        AntrianTerhubung[] departments = new AntrianTerhubung[4];

        for (int i = 0; i < 4; i++) {
            departments[i] = new AntrianTerhubung();
        }

        while (choice != 5) {
            departments[0].Head.NamaDepan = "KLINIK UMUM\n";
            departments[1].Head.NamaDepan = "KLINIK JANTUNG\n";
            departments[2].Head.NamaDepan = "KLINIK PARU-PARU\n";
            departments[3].Head.NamaDepan = "BEDAH PLASTIK\n";

            clearScreen();
            System.out.println("\nSistem Manajemen Rumah Sakit");
            System.out.println("------------------------");
            System.out.println("|--- MENU UTAMA ---|");
            for (int i = 0; i < 4; i++) {
                System.out.print(" " + (i + 1) + ": " + departments[i].Head.NamaDepan);
            }
            System.out.print(" 5: Keluar");
            System.out.print("\n\nSilakan masukkan pilihan Anda: ");
            choice = scanner.nextInt();

            if (choice >= 1 && choice <= 4) {
                departments[choice - 1].menuDepartemen();
            }
        }

        if (choice == 5) {
            System.out.println("\n\t\tTerima kasih! \n");
            System.exit(0);
        }
    }

    static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
