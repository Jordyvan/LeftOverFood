package id.ac.umn.leftoverfood;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Restoran implements Serializable {
    private String nama;
    private LatLng lokasi;
    private List<Menu> menus = new ArrayList<Menu>();

    public Restoran(String nm){
        nama = nm;
        lokasi = new LatLng(0.0, 0.0);
    }
}
