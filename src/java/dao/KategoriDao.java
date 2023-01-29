/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import models.Kategori;

/**
 *
 * @author Lenovo
 */
public class KategoriDao {
    private final Connection koneksi;
    private PreparedStatement preStmt;
    private ResultSet rs;
    
    public KategoriDao(){ //konstruktor 
            koneksi = connection.ConnectDB.getConnection();
    }
    
    public List<Kategori> getAllKategori() throws SQLException {
        ArrayList<Kategori> kategoriList = new ArrayList<>();
        String query = "SELECT * FROM kategori";
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Kategori kategori = new Kategori();
                kategori.setIdkategori(rs.getString("idkategori"));
                kategori.setNamakategori(rs.getString("namakategori"));          
                kategoriList.add(kategori);
            }
        return kategoriList;
    }
    
    public void insertKategori(Kategori kategori) throws SQLException {
        String sql = "INSERT INTO kategori (namakategori, idkategori) VALUES (?, ?)";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, kategori.getNamakategori());
            preStmt.setString(2, kategori.getIdkategori());
            preStmt.executeUpdate();
    }
    
    public void updateKategori(Kategori kategori) throws SQLException{
        String sql = "UPDATE kategori SET namakategori=? where idkategori=?";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, kategori.getNamakategori());
            preStmt.setString(2, kategori.getIdkategori());
            preStmt.executeUpdate();
    }
    
    public Kategori getDtKategori(String id) throws SQLException{
        String search = "SELECT * from kategori where idkategori = ?";
        Kategori kategori = new Kategori();
            preStmt = koneksi.prepareStatement(search);
            preStmt.setString(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                kategori.setIdkategori(rs.getString("idkategori"));
                kategori.setNamakategori(rs.getString("namakategori")); 
            }
        return kategori;
    }
    
    public void hapus(String id) throws SQLException {
        String sql = "delete from kategori where idkategori = ?";  
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, id);
            preStmt.executeUpdate();
    }
}
