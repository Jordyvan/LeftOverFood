package id.ac.umn.leftoverfood;

import java.io.Serializable;

public class Menu implements Serializable {
    private String nama;
    private String deskripsi;
    private int harga;
    private int kuantitas;
    private String FotoUrl;
    private String IDResto;

    public Menu(String nm, String ds, int hrg, int kwn,String url, String id){
        nama = nm;
        deskripsi = ds;
        harga = hrg;
        kuantitas = kwn ;
        FotoUrl = url;
        IDResto = id;
    }

    public void setNama(String nm){ nama = nm; }
    public void setDeskripsi(String ds){ deskripsi = ds; }
    public void setHarga(int hrg){ hrg = harga; }
    public void setKuantitas(int kwn){ kwn = kuantitas; }
    public void setFotoUrl(String url){ FotoUrl = url; }
    public void setIDResto(String id){ IDResto = id; }

    public String getNama(){ return nama; }
    public String getUrl(){ return FotoUrl; }
    public String getDeskripsi(){ return deskripsi; }
    public String getIDResto(){ return IDResto; }
    public int getHarga(){ return harga; }
    public int getKuantitas(){ return kuantitas; }
}
