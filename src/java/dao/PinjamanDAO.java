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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public List<Pinjaman> getAllPinjaman() throws SQLException {
        ArrayList<Pinjaman> pinjamanList = new ArrayList<>();
        String query = "SELECT * FROM viewlaporanpinjaman";
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Pinjaman pnjm = new Pinjaman();
                pnjm.setIdpinjaman(rs.getString("idpinjaman"));
                pnjm.setNamaaanggota(rs.getString("namaanggota"));
                pnjm.setJudulbuku(rs.getString("judulbuku"));
                pnjm.setTanggalpinjam(rs.getString("tanggalpinjam"));
                pnjm.setTanggalkembali(rs.getString("tanggalkembali"));
                pnjm.setTanggalpengembalian(rs.getString("tanggalpengembalian"));
                if (rs.getString("denda") == null) {
                    pnjm.setDenda("0");
                }else{
                    pnjm.setDenda(rs.getString("denda"));
                }
                pnjm.setKeterangan(rs.getString("keterangaan"));             
                pinjamanList.add(pnjm);
            }
        return pinjamanList;
    }
    
    public void insertPinjaman(Pinjaman pnjm) throws SQLException {
        String sql = "INSERT INTO pinjaman (idanggota, kodebuku, "
                    + "tanggalpinjam, tanggalkembali, tanggalpengembalian, denda, idstatus, "
                    + "idpinjaman) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, pnjm.getIdanggota());
            preStmt.setString(2, pnjm.getKodebuku());
            preStmt.setString(3, pnjm.getTanggalpinjam());
            preStmt.setString(4, pnjm.getTanggalkembali());
            if(pnjm.getTanggalpengembalian().isEmpty() || pnjm.getTanggalpengembalian() == null){
                preStmt.setString(5, null);
            }else{
                preStmt.setString(5, pnjm.getTanggalpengembalian());
            }
            if(pnjm.getDenda().isEmpty() || pnjm.getDenda() == null){
                preStmt.setString(6, null);
            }else{
               preStmt.setString(6, pnjm.getDenda()); 
            }
            preStmt.setString(7, pnjm.getIdstatus());
            preStmt.setString(8, pnjm.getIdpinjaman());
            preStmt.executeUpdate();
    }
    
    public void updatePinjaman(Pinjaman pnjm) throws SQLException{
        String sql = "UPDATE pinjaman SET idanggota=?, kodebuku=?, tanggalpinjam=?, tanggalkembali=?,"
                    + "tanggalpengembalian=?, denda=?, idstatus=? where idpinjaman=?";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, pnjm.getIdanggota());
            preStmt.setString(2, pnjm.getKodebuku());
            preStmt.setString(3, pnjm.getTanggalpinjam());
            preStmt.setString(4, pnjm.getTanggalkembali());
            preStmt.setString(5, pnjm.getDenda());
            preStmt.setString(6, pnjm.getIdstatus());
            preStmt.setString(7, pnjm.getIdpinjaman());
            preStmt.executeUpdate();
    }
    
    
    public Pinjaman getDtPinjaman(String id) throws SQLException{
        String search = "SELECT * from pinjaman where idpinjaman = ?";
        Pinjaman pnjm = new Pinjaman();
            preStmt = koneksi.prepareStatement(search);
            preStmt.setString(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                pnjm.setIdpinjaman(rs.getString("idpinjaman"));
                pnjm.setIdanggota(rs.getString("idanggota"));
//                pnjm.setNamaaanggota(rs.getString("namaanggota"));
                pnjm.setKodebuku(rs.getString("kodebuku"));
//                pnjm.setJudulbuku(rs.getString("judulbuku"));
                pnjm.setTanggalpinjam(rs.getString("tanggalpinjam"));
                pnjm.setTanggalkembali(rs.getString("tanggalkembali"));
                pnjm.setTanggalpengembalian(rs.getString("tanggalpengembalian"));
                if (rs.getString("denda") == null ) {
                    pnjm.setDenda("0");
                }else{
                    pnjm.setDenda(rs.getString("denda"));
                }
                pnjm.setIdstatus(rs.getString("idstatus"));
            }
        return pnjm;
    }
    
    public void hapus(String id) throws SQLException {
        String sql = "delete from pinjaman where idpinjaman = ?";  
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, id);
            preStmt.executeUpdate();
    }
    
    public static void main(String[] args) {
        PinjamanDAO pd = new PinjamanDAO();
        try{
            System.out.println("Data : " + pd.getDtPinjaman("1"));
        } catch (SQLException ex) {
            Logger.getLogger(PinjamanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
    
