public class loai {
    private String maloai;
    private String tenloai;

    public loai(String maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public loai() {

    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }


    public void printInfo() {
        System.out.println("Ma loai: " + this.getMaloai());
        System.out.println("Ten loai: " + this.getTenloai());
        System.out.println("==========================================");
    }
}
