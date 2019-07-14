package com.example.umbralight;

public class Instansi {
    private String jenis;
    private String nama;
    private String alamat;
    private String latitude;
    private String longitude;

    public Instansi() {

    }

    public Instansi(String jenis, String nama, String alamat, String latitude, String longitude) {
        this.jenis = jenis;
        this.nama = nama;
        this.alamat = alamat;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getJenis() {
        return jenis;
    }
    public String getNama() {
        return nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public void setNama(String nama) {
        this.jenis = nama;
    }
    public void setAlamat(String alamat) {
        this.jenis = alamat;
    }
    public void setLatitude(String latitude) {
        this.jenis = latitude;
    }
    public void setLongitude(String longitude) {
        this.jenis = longitude;
    }
}
