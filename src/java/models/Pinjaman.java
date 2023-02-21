/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author lenovo
 */
public class Pinjaman {
    private String idpinjaman;
    private String idanggota;
    private String namaaanggota;
    private String kodebuku;
    private String judulbuku;
    private String tanggalpinjam;
    private String tanggalkembali;
    private String tanggalpengembalian;
    private String denda;
    private String idstatus;
    private String keterangan;

    public String getNamaaanggota() {
        return namaaanggota;
    }

    public void setNamaaanggota(String namaaanggota) {
        this.namaaanggota = namaaanggota;
    }

    public String getJudulbuku() {
        return judulbuku;
    }

    public void setJudulbuku(String judulbuku) {
        this.judulbuku = judulbuku;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdpinjaman() {
        return idpinjaman;
    }

    public void setIdpinjaman(String idpinjaman) {
        this.idpinjaman = idpinjaman;
    }

    public String getIdanggota() {
        return idanggota;
    }

    public void setIdanggota(String idanggota) {
        this.idanggota = idanggota;
    }

    public String getKodebuku() {
        return kodebuku;
    }

    public void setKodebuku(String kodebuku) {
        this.kodebuku = kodebuku;
    }

    public String getTanggalpinjam() {
        return tanggalpinjam;
    }

    public void setTanggalpinjam(String tanggalpinjam) {
        this.tanggalpinjam = tanggalpinjam;
    }

    public String getTanggalkembali() {
        return tanggalkembali;
    }

    public void setTanggalkembali(String tanggalkembali) {
        this.tanggalkembali = tanggalkembali;
    }
    
     public String getTanggalpengembalian() {
        return tanggalpengembalian;
    }

    public void setTanggalpengembalian(String tanggalpengembalian) {
        this.tanggalpengembalian = tanggalpengembalian;
    }

    public String getDenda() {
        return denda;
    }

    public void setDenda(String denda) {
        this.denda = denda;
    }

    public String getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(String idstatus) {
        this.idstatus = idstatus;
    }
    
    
}
