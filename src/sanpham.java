public class sanpham {
    private String masp;
    private String tensp;
    private String gia;
    private String maloai;
    private String nuocsx;

    public sanpham(String masp, String tensp, String gia, String maloai, String nuocsx) {
        this.masp = masp;
        this.tensp = tensp;
        this.gia = gia;
        this.maloai = maloai;
        this.nuocsx = nuocsx;
    }

    public sanpham() { /* compiled code */ }


    public String getMasp() {
        return masp;
    }

    public String getTensp() {
        return tensp;
    }

    public String getGia() {
        return gia;
    }

    public String getMaloai() {
        return maloai;
    }

    public String getNuocsx() {
        return nuocsx;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public void setNuocsx(String nuocsx) {
        this.nuocsx = nuocsx;
    }

    public void printInfo() {
        System.out.println("Ma san pham: " + this.getMasp());
        System.out.println("Ten SP: " + this.getTensp());
        System.out.println("Gia: " + this.getGia());
        System.out.println("ma loai: " + this.getMaloai());
        System.out.println("Nuoc sx: " + this.getNuocsx());
        System.out.println("==========================================");
    }
}

