import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Hinh {
    public abstract void out();

    public abstract void TinhToan();

}

abstract class Hinh2d extends Hinh {
    public void out() {
        System.out.print("Hinh 2D\t");
    }

    public abstract void TinhToan();

    public float ChuVi, DienTich;
}

abstract class Hinh3d extends Hinh {
    public void out() {
        System.out.print("Hinh 3D\t");
    }

    public abstract void TinhToan();

    public float DienTichToanPhan, TheTich;
}

class E_lip extends Hinh2d {
    E_lip() {
        TrucDai = Main.rand.nextFloat() * (Main.MAX - Main.MIN) + Main.MIN;
        TrucNgan = Main.rand.nextFloat() * (Main.MAX - Main.MIN) + Main.MIN;
    }

    @Override
    public void out() {
        super.out();
        TinhToan();
        System.out.println("Hinh e_lip \t Truc dai:" + TrucDai + "\t Truc Ngan: " + TrucNgan + "\tDien tich: "
                + DienTich + "\tChu vi: " + ChuVi);

    }

    public void TinhToan() {
        DienTich = (float) Math.PI * TrucDai * TrucNgan * 0.25f;
        ChuVi = (float) ((float) Math.PI * (3 * ((TrucDai + TrucNgan) / 2)
                - Math.sqrt(((3 * TrucDai + TrucNgan) / 2) * (TrucDai / 2 + 1.5 * TrucNgan))));
    }

    protected float TrucDai, TrucNgan;
}

class Tron extends E_lip {
    Tron() {
        TrucDai = TrucNgan = Main.rand.nextFloat() * (Main.MAX - Main.MIN) + Main.MIN;
    }

    public void out() {
        System.out.print("Hinh 2D\t");
        TinhToan();
        System.out.println("Hinh tron \t Duong kinh:" + TrucDai +
                "\t\t\t\tDien tich: " + DienTich + "\tChu vi: " + ChuVi);
    }

    public void TinhToan() {
        DienTich = (float) Math.PI * TrucDai * TrucNgan;
        ChuVi = (float) Math.PI * TrucDai;
    }
}

class ChuNhat extends Hinh2d {
    ChuNhat() {
        ChieuDai = Main.rand.nextFloat() * (Main.MAX - Main.MIN) + Main.MIN;
        ChieuRong = Main.rand.nextFloat() * (Main.MAX - Main.MIN) + Main.MIN;
    }

    @Override
    public void out() {
        super.out();
        TinhToan();
        System.out.println("Hinh chu nhat \t Chieu dai:" + ChieuDai + "\t Chieu rong: " + ChieuRong + "\t"
                + "Dien tich: " + DienTich + "\tChu vi: " + ChuVi);

    }

    public void TinhToan() {
        DienTich = ChieuDai * ChieuRong;
        ChuVi = 2 * (ChieuDai + ChieuRong);
    }

    protected float ChieuDai, ChieuRong;
}

class Vuong extends ChuNhat {

    Vuong() {
        ChieuDai = ChieuRong = Main.rand.nextFloat() * (Main.MAX - Main.MIN) + Main.MIN;
    }

    @Override
    public void out() {
        System.out.print("Hinh 2D\t");
        TinhToan();
        System.out.println("Hinh vuong \t Chieu dai Canh:" + ChieuDai + "\t\t\t"
                + "Dien tich: " + DienTich + "\tChu vi: " + ChuVi);

    }
}

class Cau extends Hinh3d {
    Cau() {
        BanKinh = Main.rand.nextFloat() * (Main.MAX - Main.MIN) + Main.MIN;
    }

    @Override
    public void out() {
        super.out();
        TinhToan();
        System.out.println("Khoi cau \t Ban kinh:" + BanKinh +
                "\t\t\t\tDien tich: " + DienTichToanPhan + "\tThe tich: " + TheTich);

    }

    public void TinhToan() {
        DienTichToanPhan = 4 * (float) Math.PI * BanKinh * BanKinh;
        TheTich = (4 / 3) * (float) Math.PI * BanKinh * BanKinh * BanKinh;
    }

    private float BanKinh;
}

class LapPhuong extends Hinh3d {
    LapPhuong() {
        Canh = Main.rand.nextFloat() * (Main.MAX - Main.MIN) + Main.MIN;
    }

    @Override
    public void out() {
        super.out();
        TinhToan();
        System.out.println("Khoi lap phuong  Canh:" + Canh +
                "\t\t\t\t\tDien tich: " + DienTichToanPhan + "\tThe tich: " + TheTich);
    }

    public void TinhToan() {
        DienTichToanPhan = 8 * Canh * Canh;
        TheTich = (float) Math.pow(Canh, 3);
    }

    private float Canh;
}

class Tru extends Hinh3d {
    Tru() {
        ChieuCao = Main.rand.nextFloat() * (Main.MAX - Main.MIN) + Main.MIN;
        BanKinhDay = Main.rand.nextFloat() * (Main.MAX - Main.MIN) + Main.MIN;
    }

    @Override
    public void out() {
        super.out();
        TinhToan();
        System.out.println("Khoi tru \t Chieu cao:" + ChieuCao + "\t Ban kinh day:" + BanKinhDay +
                "\tDien tich: " + DienTichToanPhan + "\tThe tich: " + TheTich);
    }

    public void TinhToan() {
        TheTich = ChieuCao * (float) Math.PI * BanKinhDay * BanKinhDay;
        DienTichToanPhan = 2 * (float) Math.PI * BanKinhDay * ChieuCao;
    }

    private float ChieuCao, BanKinhDay;
}

class Main {

    static float MIN = 0.01f;
    static float MAX = 10;
    static Random rand = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 0;

        while (true) {
            System.out.print("Nhap so luong hinh n: ");
            if (scanner.hasNextInt()) {
                N = scanner.nextInt();
                if (N > 0)
                    break;
                else
                    System.out.println("Vui long nhap so nguyen duong.");
            } else {
                System.out.println("Nhap so nguyen duong!!!");
                scanner.next();
            }
        }
        scanner.close();

        List<Hinh> hinhList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int randomShape = rand.nextInt(3);
            switch (randomShape) {
                case 0 -> hinhList.add(new E_lip());
                case 1 -> hinhList.add(new Tron());
                case 2 -> hinhList.add(new Cau());
                case 3 -> hinhList.add(new ChuNhat());
                case 4 -> hinhList.add(new Vuong());
                case 5 -> hinhList.add(new LapPhuong());
                case 6 -> hinhList.add(new Tru());
            }
        }

        for (Hinh h : hinhList) {
            h.out();
        }
    }
}