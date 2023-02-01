/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author lenovo
 */
public class PinjamanAttributes {
    private Anggota anggota;
    private Status status;
    private Buku buku;

    public PinjamanAttributes(Anggota anggota, Status status, Buku buku) {
        this.anggota = anggota;
        this.status = status;
        this.buku = buku;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    @Override
    public String toString() {
        return "PinjamanAttributes{" + "anggota=" + anggota + ", status=" + status + ", buku=" + buku + '}';
    }
    
    
}
