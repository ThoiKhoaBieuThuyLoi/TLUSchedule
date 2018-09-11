package vn.lcsoft.luongchung.models;


import java.io.Serializable;

/**
 * Created by LUONG CHUNG on 5/1/2017.
 */

public class ThuHoc implements Serializable {
    private int idThu;
    private String thu;
    private String tietBD;
    private String tietKT;

    public ThuHoc(int idThu, String thu, String tietBD, String tietKT) {
        this.idThu = idThu;
        this.thu = thu;
        this.tietBD = tietBD;
        this.tietKT = tietKT;
    }

    public ThuHoc() {
    }

    public int getIdThu() {
        return idThu;
    }

    public String getThu() {
        return thu;
    }

    public String getTietBD() {
        return tietBD;
    }

    public String getTietKT() {
        return tietKT;
    }

    public void setIdThu(int idThu) {
        this.idThu = idThu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public void setTietBD(String tietBD) {
        this.tietBD = tietBD;
    }

    public void setTietKT(String tietKT) {
        this.tietKT = tietKT;
    }
}
