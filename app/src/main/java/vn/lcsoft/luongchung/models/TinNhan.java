package vn.lcsoft.luongchung.models;

public class TinNhan {
    private String id;
    private String name;
    private String noiDung;

    public TinNhan(String id, String name, String noiDung, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.noiDung = noiDung;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
