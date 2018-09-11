package vn.lcsoft.luongchung.models;


import java.io.Serializable;

/**
 * Created by LUONG CHUNG on 5/1/2017.
 */

public class PhongHoc implements Serializable {
    private int idThu;
    private String tenPhongHoc;

    public PhongHoc(int idThu, String tenPhongHoc) {
        this.idThu = idThu;
        this.tenPhongHoc = tenPhongHoc;
    }

    public PhongHoc() {
    }

    public int getIdThu() {
        return idThu;
    }

    public void setIdThu(int idThu) {
        this.idThu = idThu;
    }

    public String getTenPhongHoc() {
        return tenPhongHoc;
    }

    public void setTenPhongHoc(String tenPhongHoc) {
        this.tenPhongHoc = tenPhongHoc;
    }
}
