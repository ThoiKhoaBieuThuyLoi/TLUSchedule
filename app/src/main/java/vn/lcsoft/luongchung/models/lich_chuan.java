package vn.lcsoft.luongchung.models;

import java.util.Date;

/**
 * Created by luongchung on 7/20/17.
 */

public class lich_chuan {
    private int id;
    private String tenMonHoc;
    private String tenLopTinChi;
    private String diaDiem;
    private String giangVien;
    private Date ngay;
    private String thuHoc;
    private String tietBatDau;
    private String tietKetThuc;
    private String soTinChi;
    private String note;

    public lich_chuan(int id, String tenMonHoc, String tenLopTinChi, String diaDiem, String giangVien, Date ngay, String thuHoc, String tietBatDau, String tietKetThuc, String soTinChi, String note) {
        this.id = id;
        this.tenMonHoc = tenMonHoc;
        this.tenLopTinChi = tenLopTinChi;
        this.diaDiem = diaDiem;
        this.giangVien = giangVien;
        this.ngay = ngay;
        this.thuHoc = thuHoc;
        this.tietBatDau = tietBatDau;
        this.tietKetThuc = tietKetThuc;
        this.soTinChi = soTinChi;
        this.note = note;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public lich_chuan() {
    }
    public lich_chuan(int id, String tenMonHoc, String tenLopTinChi, String diaDiem,
                      String giangVien, Date ngay, String thuHoc, String tietBatDau, String tietKetThuc, String soTinChi) {
        this.id = id;
        this.tenMonHoc = tenMonHoc;
        this.tenLopTinChi = tenLopTinChi;
        this.diaDiem = diaDiem;
        this.giangVien = giangVien;
        this.ngay = ngay;
        this.thuHoc = thuHoc;
        this.tietBatDau = tietBatDau;
        this.tietKetThuc = tietKetThuc;
        this.soTinChi = soTinChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMonHoc() {

        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getTenLopTinChi() {
        return tenLopTinChi;
    }

    public void setTenLopTinChi(String tenLopTinChi) {
        this.tenLopTinChi = tenLopTinChi;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(String giangVien) {
        this.giangVien = giangVien;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getThuHoc() {
        return thuHoc;
    }

    public void setThuHoc(String thuHoc) {
        this.thuHoc = thuHoc;
    }

    public String getTietBatDau() {
        return tietBatDau;
    }

    public void setTietBatDau(String tietBatDau) {
        this.tietBatDau = tietBatDau;
    }

    public String getTietKetThuc() {
        return tietKetThuc;
    }

    public void setTietKetThuc(String tietKetThuc) {
        this.tietKetThuc = tietKetThuc;
    }

    public String getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(String soTinChi) {
        this.soTinChi = soTinChi;
    }

    @Override
    public String toString() {
        return "lich_chuan{" +
                "id=" + id +
                ", tenMonHoc='" + tenMonHoc + '\'' +
                ", tenLopTinChi='" + tenLopTinChi + '\'' +
                ", diaDiem='" + diaDiem + '\'' +
                ", giangVien='" + giangVien + '\'' +
                ", ngay=" + ngay +
                ", thuHoc='" + thuHoc + '\'' +
                ", tietBatDau='" + tietBatDau + '\'' +
                ", tietKetThuc='" + tietKetThuc + '\'' +
                ", soTinChi='" + soTinChi + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
