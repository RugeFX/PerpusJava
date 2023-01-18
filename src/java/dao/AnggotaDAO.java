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
import models.Anggota;

/**
 *
 * @author Lenovo
 */
public class AnggotaDAO {
    private final Connection koneksi;
    private PreparedStatement preStmt;
    private ResultSet rs;
    
    private SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
    public AnggotaDAO(){ //konstruktor 
            koneksi = connection.ConnectDB.getConnection();
    }
    
    public List<Anggota> getAllAnggota() {
        ArrayList<Anggota> anggotaList = new ArrayList<>();
        String query = "SELECT * FROM anggota";
        try{
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Anggota ang = new Anggota();
                ang.setNik(rs.getString("nik"));
                ang.setPassword(rs.getString("password"));
                ang.setNamaanggota(rs.getString("namaanggota"));
                ang.setAlamat(rs.getString("alamat"));
                ang.setKota(rs.getString("kota"));
                ang.setNotelpon(rs.getString("notelpon"));
                ang.setTanggallahir(rs.getString("tanggallahir"));             
                anggotaList.add(ang);
            }
        }catch(SQLException ex){
            System.out.println("Error on anggotaDao : " + ex);
        }
        return anggotaList;
    }
    
    public void simpanAnggota(Anggota ang, String mode) throws SQLException {
        String sql = null;
        if (mode.equalsIgnoreCase("insert")) {
            sql = "INSERT INTO anggota (password, namanaggota, "
                    + "alamat, kota, notelpon, tanggallahir, "
                    + "nik) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        }else if(mode.equalsIgnoreCase("update")){
            sql = "UPDATE anggota SET password=?, namanaggota=?, alamat=?, kota=?,"
                    + "notelpon=?, tanggallahir=? where nik=?";
        }
        try{
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, ang.getPassword());
            preStmt.setString(2, ang.getNamaanggota());
            preStmt.setString(3, ang.getAlamat());
            preStmt.setString(4, ang.getKota());
            preStmt.setString(5, ang.getNotelpon());
            preStmt.setString(6, ang.getTanggallahir());
            preStmt.setString(7, ang.getNik());
            preStmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Ada Error : " + ex);
        }
    }
    
    public Anggota getDtAnggota(String nik){
        String search = "SELECT * from anggota where nik = ?";
        Anggota ang = new Anggota();
        try {
            preStmt = koneksi.prepareStatement(search);
            preStmt.setString(1, nik);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                ang.setNik(rs.getString("nik"));
                ang.setPassword(rs.getString("password"));
                ang.setNamaanggota(rs.getString("namaanggota"));
                ang.setAlamat(rs.getString("alamat"));
                ang.setKota(rs.getString("kota"));
                ang.setNotelpon(rs.getString("notelpon"));
                ang.setTanggallahir(rs.getString("tanggallahir"));   
            }
            
        } catch (SQLException ex) {
            System.out.println("Ada kesalahan : "+ex);
        }
        return ang;
    }
    
    public void hapus(String nik) {
        String sql = "delete from anggota where nik = ?";  
        try {
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, nik);
            preStmt.executeUpdate();
            
        } catch (SQLException ex) {
           System.out.println("Ada kesalahan : "+ex);
        }
    }
}
