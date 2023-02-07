/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author lenovo
 */
public class PinjamanAttributes {
    private List<Anggota> anggota;
    private List<Status> status;
    private List<Buku> buku;

    public PinjamanAttributes(List<Anggota> anggota, List<Status> status, List<Buku> buku) {
        this.anggota = anggota;
        this.status = status;
        this.buku = buku;
    }

    @Override
    public String toString() {
        return "PinjamanAttributes{" + "anggota=" + anggota + ", status=" + status + ", buku=" + buku + '}';
    }

    public List<Anggota> getAnggota() {
        return anggota;
    }

    public void setAnggota(List<Anggota> anggota) {
        this.anggota = anggota;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

    public List<Buku> getBuku() {
        return buku;
    }

    public void setBuku(List<Buku> buku) {
        this.buku = buku;
    }
}
