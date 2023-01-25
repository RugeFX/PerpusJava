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
import models.Pinjaman;

/**
 *
 * @author Lenovo
 */
public class PinjamanDAO {
    private final Connection koneksi;
    private PreparedStatement preStmt;
    private ResultSet rs;
    
    private SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
    public PinjamanDAO(){ //konstruktor 
            koneksi = connection.ConnectDB.getConnection();
    }
    
    public List<Pinjaman> getAllPinjaman() {
        ArrayList<Pinjaman> pinjamanList = new ArrayList<>();
        String query = "SELECT * FROM viewlaporanpinjaman";
        try{
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Pinjaman pnjm = new Pinjaman();
                pnjm.setIdanggota(rs.getString("idpinjaman"));
                pnjm.setNamaaanggota(rs.getString("namaanggota"));
                pnjm.setJudulbuku(rs.getString("judulbuku"));
                pnjm.setTanggalpinjam(rs.getString("tanggalpinjam"));
                pnjm.setTanggalkembali(rs.getString("tanggalkembali"));
                if (rs.getString("denda").equals("") || rs.getString("denda") == null ) {
                    pnjm.setDenda("0");
                }else{
                    pnjm.setDenda(rs.getString("denda"));
                }
                pnjm.setKeterangan(rs.getString("keterangaan"));             
                pinjamanList.add(pnjm);
            }
        }catch(SQLException ex){
            System.out.println("Error on anggotaDao : " + ex);
        }
        return pinjamanList;
    }
    
    public void insertPinjaman(Pinjaman pnjm) throws SQLException {
        String sql = "INSERT INTO pinjman (idanggota, kodebuku, "
                    + "tanggalpinjam, tanggalkembali, denda, idstatus, "
                    + "idpinjaman) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try{
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, pnjm.getIdanggota());
            preStmt.setString(2, pnjm.getKodebuku());
            preStmt.setString(3, pnjm.getTanggalpinjam());
            preStmt.setString(4, pnjm.getTanggalkembali());
            preStmt.setString(5, pnjm.getDenda());
            preStmt.setString(6, pnjm.getIdstatus());
            preStmt.setString(7, pnjm.getIdpinjaman());
            preStmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Ada Error : " + ex);
        }
    }
    
    public void updatePinjaman(Pinjaman pnjm) throws SQLException{
        String sql = "UPDATE petugas SET idanggota=?, kodebuku=?, tanggalpinjam=?, tanggalkembali=?,"
                    + "denda=?, idstatus=? where idpinjaman=?";
        try{
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, pnjm.getIdanggota());
            preStmt.setString(2, pnjm.getKodebuku());
            preStmt.setString(3, pnjm.getTanggalpinjam());
            preStmt.setString(4, pnjm.getTanggalkembali());
            preStmt.setString(5, pnjm.getDenda());
            preStmt.setString(6, pnjm.getIdstatus());
            preStmt.setString(7, pnjm.getIdpinjaman());
            preStmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Ada Error : " + ex);
        }
    }
    
    public List<Pinjaman> getBukuTerlaris(){
        ArrayList<Pinjaman> pinjamanList = new ArrayList<>();
        String query = "SELECT *, COUNT(judulbuku) as Total FROM viewlaporanpinjaman "
                + "GROUP BY judulbuku LIMIT 3";
        try{
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Pinjaman pnjm = new Pinjaman();
                pnjm.setIdanggota(rs.getString("idpinjaman"));
                pnjm.setNamaaanggota(rs.getString("namaanggota"));
                pnjm.setJudulbuku(rs.getString("judulbuku"));
                pnjm.setTanggalpinjam(rs.getString("tanggalpinjam"));
                pnjm.setTanggalkembali(rs.getString("tanggalkembali"));
                if (rs.getString("denda").equals("") || rs.getString("denda") == null ) {
                    pnjm.setDenda("0");
                }else{
                    pnjm.setDenda(rs.getString("denda"));
                }
                pnjm.setKeterangan(rs.getString("keterangaan"));             
                pinjamanList.add(pnjm);
            }
        }catch(SQLException ex){
            System.out.println("Error on anggotaDao : " + ex);
        }
        return pinjamanList;
    }
    
    public Pinjaman getDtPinjaman(String id){
        String search = "SELECT * from viewlaporanpinjaman where idpinjaman = ?";
        Pinjaman pnjm = new Pinjaman();
        try {
            preStmt = koneksi.prepareStatement(search);
            preStmt.setString(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                pnjm.setIdanggota(rs.getString("idpinjaman"));
                pnjm.setNamaaanggota(rs.getString("namaanggota"));
                pnjm.setJudulbuku(rs.getString("judulbuku"));
                pnjm.setTanggalpinjam(rs.getString("tanggalpinjam"));
                pnjm.setTanggalkembali(rs.getString("tanggalkembali"));
                if (rs.getString("denda").equals("") || rs.getString("denda") == null ) {
                    pnjm.setDenda("0");
                }else{
                    pnjm.setDenda(rs.getString("denda"));
                }
                pnjm.setKeterangan(rs.getString("keterangaan"));   
            }
            
        } catch (SQLException ex) {
            System.out.println("Ada kesalahan : "+ex);
        }
        return pnjm;
    }
    
    public void hapus(String id) {
        String sql = "delete from pinjaman where idpinjaman = ?";  
        try {
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, id);
            preStmt.executeUpdate();
            
        } catch (SQLException ex) {
           System.out.println("Ada kesalahan : "+ex);
        }
    }
}
