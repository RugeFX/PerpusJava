/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
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
    
    public String getMd5String(String input){
         try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, hash);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception ex) {
            System.out.println("Gagal");
            return null;
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Boolean getLogin(String nik, String Password) throws SQLException{
        Anggota ang = new Anggota();
        String sqlSearch = "SELECT * FROM anggota WHERE nik=?";
            String pw;
            preStmt = koneksi.prepareStatement (sqlSearch);
            preStmt.setString(1, nik);
            rs = preStmt.executeQuery();
            if(rs.next()) {
                ang.setNik(rs.getString("nik"));
                ang.setPassword(rs.getString("password"));
                String pwMd5 = getMd5String(Password);
                if (!pwMd5.equals(ang.getPassword())) {
                    System.out.println("Masuk");
                    return false;
                }
                return true;
        }
        return false;
    }
    
    public List<Anggota> getAllAnggota() throws SQLException {
        ArrayList<Anggota> anggotaList = new ArrayList<>();
        String query = "SELECT * FROM anggota";
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
        return anggotaList;
    }
    
    public void insertAnggota(Anggota ang) throws SQLException {
        String pwMd5 = getMd5String(ang.getPassword());
        String sql = "INSERT INTO anggota (nik, password, namaanggota,"
                    + "alamat, kota, notelpon, tanggallahir)"
                    + " VALUES ( ?, ?, ?, ?, ?, ?, ?)";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, ang.getNik());
            preStmt.setString(2, pwMd5);
            preStmt.setString(3, ang.getNamaanggota());
            preStmt.setString(4, ang.getAlamat());
            preStmt.setString(5, ang.getKota());
            preStmt.setString(6, ang.getNotelpon());
            preStmt.setString(7, ang.getTanggallahir());           
            preStmt.executeUpdate();
    }
    
    public void updateAnggota(Anggota ang) throws SQLException{
        String sql = "UPDATE anggota SET password=?, namaanggota=?, alamat=?, kota=?,"
                    + "notelpon=?, tanggallahir=? WHERE nik=?";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, ang.getPassword());
            preStmt.setString(2, ang.getNamaanggota());
            preStmt.setString(3, ang.getAlamat());
            preStmt.setString(4, ang.getKota());
            preStmt.setString(5, ang.getNotelpon());
            preStmt.setString(6, ang.getTanggallahir());
            preStmt.setString(7, ang.getNik());
            preStmt.executeUpdate();
    }
    
    public Anggota getDtAnggota(String nik) throws SQLException {
        String search = "SELECT * from anggota where nik = ?";
        Anggota ang = new Anggota();
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
        return ang;
    }
    
    public void hapus(String nik) throws SQLException {
        String sql = "delete from anggota where nik = ?";  
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, nik);
            preStmt.executeUpdate();
    }
    
    public static void main(String[] args) {
        AnggotaDAO angdao = new AnggotaDAO();
        try {
            angdao.getAllAnggota();
        } catch (Exception e) {
        }
        
    }
}

