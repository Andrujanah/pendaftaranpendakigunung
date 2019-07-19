package com.tubes.pendaftaranpendakigunung.Model;

import com.google.gson.annotations.SerializedName;

public class Pendaki {
    @SerializedName("id_pendaki")
    private Integer id_pendaki;
    @SerializedName("nama_pendaki")
    private String nama_pendaki;
    @SerializedName("alamat_pendaki")
    private String alamat_pendaki;
    @SerializedName("no_telp")
    private String no_telp;
    @SerializedName("waktu_posted")
    private String waktu_posted;

    public Integer getId_pendaki() {
        return id_pendaki;
    }

    public void setId_pendaki(Integer id_pendaki) {
        this.id_pendaki = id_pendaki;
    }

    public String getNama_pendaki() {
        return nama_pendaki;
    }

    public void setNama_pendaki(String nama_pendaki) {
        this.nama_pendaki = nama_pendaki;
    }

    public String getAlamat_pendaki() {
        return alamat_pendaki;
    }

    public void setAlamat_pendaki(String alamat_pendaki) {
        this.alamat_pendaki = alamat_pendaki;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getWaktu_posted() {
        return waktu_posted;
    }

    public void setWaktu_posted(String waktu_posted) {
        this.waktu_posted = waktu_posted;
    }
}
