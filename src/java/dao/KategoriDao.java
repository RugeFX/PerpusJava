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
    
    public List<Kategori> getAllKategori() {
        ArrayList<Kategori> kategoriList = new ArrayList<>();
        String query = "SELECT * FROM kategori";
        try{
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Kategori kategori = new Kategori();
                kategori.setIdkategori(rs.getString("idkategori"));
                kategori.setNamakategori(rs.getString("namakategori"));          
                kategoriList.add(kategori);
            }
        }catch(SQLException ex){
            System.out.println("Error on anggotaDao : " + ex);
        }
        return kategoriList;
    }
    
    public void insertKategori(Kategori kategori) throws SQLException {
        String sql = "INSERT INTO kategori (namakategori, idkategori) VALUES (?, ?)";
        try{
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, kategori.getNamakategori());
            preStmt.setString(2, kategori.getIdkategori());
            preStmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Ada Error : " + ex);
        }
    }
    
    public void updateKategori(Kategori kategori) throws SQLException{
        String sql = "UPDATE kategori SET namakategori=? where idkategori=?";
        try{
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, kategori.getNamakategori());
            preStmt.setString(2, kategori.getIdkategori());
            preStmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Ada Error : " + ex);
        }
    }
    
    public Kategori getDtAnggota(String id){
        String search = "SELECT * from kategori where idkategori = ?";
        Kategori kategori = new Kategori();
        try {
            preStmt = koneksi.prepareStatement(search);
            preStmt.setString(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                kategori.setIdkategori(rs.getString("idkategori"));
                kategori.setNamakategori(rs.getString("namakategori")); 
            }
            
        } catch (SQLException ex) {
            System.out.println("Ada kesalahan : "+ex);
        }
        return kategori;
    }
    
    public void hapus(String id) {
        String sql = "delete from kategori where idkategori = ?";  
        try {
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, id);
            preStmt.executeUpdate();
            
        } catch (SQLException ex) {
           System.out.println("Ada kesalahan : "+ex);
        }
    }
}
