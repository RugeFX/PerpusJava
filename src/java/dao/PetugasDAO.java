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
import models.Petugas;

/**
 *
 * @author Lenovo
 */
public class PetugasDAO {
    private final Connection koneksi;
    private PreparedStatement preStmt;
    private ResultSet rs;
    
    private SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
    public PetugasDAO(){ //konstruktor 
            koneksi = connection.ConnectDB.getConnection();
    }
    
    public List<Petugas> getAllPetugas() {
        ArrayList<Petugas> petugasList = new ArrayList<>();
        String query = "SELECT * FROM petugas";
        try{
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Petugas petugas = new Petugas();
                petugas.setIdpetugas(rs.getString("idpetugas"));
                petugas.setPassword(rs.getString("password"));
                petugas.setNamapetugas(rs.getString("namapetugas"));
                petugas.setAlamat(rs.getString("alamat"));
                petugas.setKota(rs.getString("kota"));
                petugas.setNotelpon(rs.getString("notelpon"));
                petugas.setTanggallahir(rs.getString("tanggallahir"));             
                petugasList.add(petugas);
            }
        }catch(SQLException ex){
            System.out.println("Error on anggotaDao : " + ex);
        }
        return petugasList;
    }
    
    public void insertPetugas(Petugas petugas) throws SQLException {
        String sql = "INSERT INTO petugas (password, namapetugas, "
                    + "alamat, kota, notelpon, tanggallahir, "
                    + "idpetugas) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try{
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, petugas.getPassword());
            preStmt.setString(2, petugas.getNamapetugas());
            preStmt.setString(3, petugas.getAlamat());
            preStmt.setString(4, petugas.getKota());
            preStmt.setString(5, petugas.getNotelpon());
            preStmt.setString(6, petugas.getTanggallahir());
            preStmt.setString(7, petugas.getIdpetugas());
            preStmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Ada Error : " + ex);
        }
    }
    
    public void updatePetugas(Petugas petugas) throws SQLException{
        String sql = "UPDATE petugas SET password=?, namapetugas=?, alamat=?, kota=?,"
                    + "notelpon=?, tanggallahir=? where idpetugas=?";
        try{
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, petugas.getPassword());
            preStmt.setString(2, petugas.getNamapetugas());
            preStmt.setString(3, petugas.getAlamat());
            preStmt.setString(4, petugas.getKota());
            preStmt.setString(5, petugas.getNotelpon());
            preStmt.setString(6, petugas.getTanggallahir());
            preStmt.setString(7, petugas.getIdpetugas());
            preStmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Ada Error : " + ex);
        }
    }
    
    public Petugas getDtPetugas(String id){
        String search = "SELECT * from petugas where idpetugas = ?";
        Petugas petugas = new Petugas();
        try {
            preStmt = koneksi.prepareStatement(search);
            preStmt.setString(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                petugas.setIdpetugas(rs.getString("idpetugas"));
                petugas.setPassword(rs.getString("password"));
                petugas.setNamapetugas(rs.getString("namapetugas"));
                petugas.setAlamat(rs.getString("alamat"));
                petugas.setKota(rs.getString("kota"));
                petugas.setNotelpon(rs.getString("notelpon"));
                petugas.setTanggallahir(rs.getString("tanggallahir"));   
            }
            
        } catch (SQLException ex) {
            System.out.println("Ada kesalahan : "+ex);
        }
        return petugas;
    }
    
    public void hapus(String id) {
        String sql = "delete from petugas where idpetugas = ?";  
        try {
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, id);
            preStmt.executeUpdate();
            
        } catch (SQLException ex) {
           System.out.println("Ada kesalahan : "+ex);
        }
    }
}