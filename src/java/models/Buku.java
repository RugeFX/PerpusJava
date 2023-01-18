/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author lenovo
 */
public class Buku {
    private String kodebuku;
    private String judulbuku;
    private String pengarang;
    private String tahunterbit;
    private String idkategori;
    private String idpenerbit;
    private String idgenre;
    private String urlebook;
    private String urlgambar;

    public String getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(String idgenre) {
        this.idgenre = idgenre;
    }

    public String getUrlebook() {
        return urlebook;
    }

    public void setUrlebook(String urlebook) {
        this.urlebook = urlebook;
    }

    public String getUrlgambar() {
        return urlgambar;
    }

    public void setUrlgambar(String urlgambar) {
        this.urlgambar = urlgambar;
    }
    private int stokbuku;

    public String getKodebuku() {
        return kodebuku;
    }

    public void setKodebuku(String kodebuku) {
        this.kodebuku = kodebuku;
    }

    public String getJudulbuku() {
        return judulbuku;
    }

    public void setJudulbuku(String judulbuku) {
        this.judulbuku = judulbuku;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getTahunterbit() {
        return tahunterbit;
    }

    public void setTahunterbit(String tahunterbit) {
        this.tahunterbit = tahunterbit;
    }

    public String getIdkategori() {
        return idkategori;
    }

    public void setIdkategori(String idkategori) {
        this.idkategori = idkategori;
    }

    public String getIdpenerbit() {
        return idpenerbit;
    }

    public void setIdpenerbit(String idpenerbit) {
        this.idpenerbit = idpenerbit;
    }
    
    public int getStokbuku() {
        return stokbuku;
    }

    public void setStokbuku(int stokbuku) {
        this.stokbuku = stokbuku;
    }
    
    
}
