package id.ac.umn.leftoverfood;

import java.io.Serializable;

public class Menu implements Serializable {
    private String nama;
    private String kategori;
    private String deskripsi;
    private int harga;
    private int kuantitas;

    public Menu(String nm, String kt, String ds, int hrg, int kwn){
        nama = nm;
        kategori = kt;
        deskripsi = ds;
        hrg = harga;
        kwn = kuantitas;
    }

    public void setNama(String nm){ nama = nm; }
    public void setKategori(String kt){ kategori = kt; }
    public void setDeskripsi(String ds){ deskripsi = ds; }
    public void setHarga(int hrg){ hrg = harga; }
    public void setKuantitas(int kwn){ kwn = kuantitas; }

    public String getNama(){ return nama; }
    public String getKategori(){ return kategori; }
    public String getDeskripsi(){ return deskripsi; }
    public int getHarga(){ return harga; }
    public int getKuantitas(){ return kuantitas; }
}
