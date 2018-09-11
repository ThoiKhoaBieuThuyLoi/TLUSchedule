package vn.lcsoft.luongchung.models;

/**
 * Created by LUONG CHUNG on 7/7/2017.
 */

public class LichHomNay {
    private String thoiGian;
    private String tenMonHoc;
    private String diaDiem;

    public LichHomNay(String thoiGian, String tenMonHoc, String diaDiem) {
        this.thoiGian = thoiGian;
        this.tenMonHoc = tenMonHoc;
        this.diaDiem = diaDiem;
    }

    public LichHomNay() {
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }
}
