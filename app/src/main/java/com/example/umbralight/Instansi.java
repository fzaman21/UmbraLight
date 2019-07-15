package com.example.umbralight;

public class Instansi {
    private String kabupaten;
    private String nama;
    private String alamat;
    private String latitude;
    private String longitude;

    public Instansi(String kabupaten, String nama, String alamat, String latitude, String longitude) {
        this.kabupaten = kabupaten;
        this.nama = nama;
        this.alamat = alamat;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getKabupaten() {
        return kabupaten;
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

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }
    public void setNama(String nama) {
        this.kabupaten = nama;
    }
    public void setAlamat(String alamat) {
        this.kabupaten = alamat;
    }
    public void setLatitude(String latitude) {
        this.kabupaten = latitude;
    }
    public void setLongitude(String longitude) {
        this.kabupaten = longitude;
    }
}
