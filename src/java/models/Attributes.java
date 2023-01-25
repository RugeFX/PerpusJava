/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Attributes {
    private List<Genre> genre;
    private List<Penerbit> penerbit;
    private List<Kategori> kategori;

    public Attributes(List<Genre> genre, List<Penerbit> penerbit, List<Kategori> kategori) {
        this.genre = genre;
        this.penerbit = penerbit;
        this.kategori = kategori;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public List<Penerbit> getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(List<Penerbit> penerbit) {
        this.penerbit = penerbit;
    }

    public List<Kategori> getKategori() {
        return kategori;
    }

    public void setKategori(List<Kategori> kategori) {
        this.kategori = kategori;
    }

}
