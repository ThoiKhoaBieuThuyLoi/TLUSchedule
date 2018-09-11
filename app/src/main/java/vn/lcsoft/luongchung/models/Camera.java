package vn.lcsoft.luongchung.models;

import android.graphics.Bitmap;

/**
 * Created by luongchung on 8/31/17.
 */

public class Camera {
    private Bitmap bitcammera;
    public Bitmap getBitcammera() {
        return bitcammera;
    }
    public void setBitcammera(Bitmap bitcammera) {
        this.bitcammera = bitcammera;
    }
    public Camera() {}
    public Camera(Bitmap bitcammera) {
        this.bitcammera = bitcammera;
    }
}
