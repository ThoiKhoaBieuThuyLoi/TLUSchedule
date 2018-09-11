package vn.lcsoft.luongchung.models;

public class Phanhoi {
    private String keyFireBase;
    private String name;
    private String noiDung;

    public Phanhoi(String keyFireBase, String name, String noiDung) {
        this.keyFireBase = keyFireBase;
        this.name = name;
        this.noiDung = noiDung;
    }

    public Phanhoi() {
    }

    public String getKeyFireBase() {
        return keyFireBase;
    }

    public void setKeyFireBase(String keyFireBase) {
        this.keyFireBase = keyFireBase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
