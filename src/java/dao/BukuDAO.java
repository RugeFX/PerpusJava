/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import models.Buku;
import connection.ConnectDB;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class BukuDAO {
    private final Connection koneksi;
    private PreparedStatement preStmt;
    private ResultSet rs;
    
    private SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
    public BukuDAO(){ //konstruktor 
            koneksi = connection.ConnectDB.getConnection();
    }
    
    public Buku getBukuById(String id){
        Buku buku = new Buku();
        try{
            Statement stmt = koneksi.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Buku WHERE kodebuku = " + id);
            while (rs.next()) {
                buku.setKodebuku(rs.getString("kodebuku"));
                buku.setJudulbuku(rs.getString("judulbuku"));
                buku.setPengarang(rs.getString("pengarang"));
                buku.setTahunterbit(rs.getString("tahunterbit"));
                buku.setIdkategori(rs.getString("idkategori"));
                buku.setIdpenerbit(rs.getString("idpenerbit"));
                buku.setIdgenre(rs.getString("genre"));
                buku.setStokbuku(rs.getInt("stokbuku"));
            }
        }catch(SQLException ex){
            System.out.println("Error on BukuDAO : " + ex);
        }
        return buku;
    }
    
    public List<Buku> getAllBuku() {
        ArrayList<Buku> bukuList = new ArrayList<>();
        String query = "SELECT * FROM buku";
        try{
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Buku buku = new Buku();
                buku.setKodebuku(rs.getString("kodebuku"));
                buku.setJudulbuku(rs.getString("judulbuku"));
                buku.setPengarang(rs.getString("pengarang"));
                buku.setTahunterbit(rs.getString("tahunterbit"));
                buku.setIdkategori(rs.getString("idkategori"));
                buku.setIdpenerbit(rs.getString("idpenerbit"));
                buku.setIdgenre(rs.getString("idgenre"));
                buku.setUrlebook(rs.getString("urlebook"));
                buku.setUrlgambar(rs.getString("urlgambar"));
                buku.setIdgenre(rs.getString("genre"));
                buku.setStokbuku(rs.getInt("stokbuku"));
                bukuList.add(buku);
            }
        }catch(SQLException ex){
            System.out.println("Error on BukuDAO : " + ex);
        }
        return bukuList;
    }
    
    public void simpanBuku(Buku buku, String mode) throws SQLException {
        String sql = null;
        if (mode.equalsIgnoreCase("insert")) {
            sql = "INSERT INTO buku (judulbuku, pengarang, "
                    + "tahunterbit, idkategori, idpenerbit, idgenre, "
                    + "urlebook, urlgambar, stokbuku, kodebuku) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }else if(mode.equalsIgnoreCase("update")){
            sql = "UPDATE buku SET judulbuku=?, pengarang=?, tahunterbit=?, idkategori=?,"
                    + "idpenerbit=?, idgenre=?, urlebook=?, urlgambar=?, stokbuku=? where kodebuku=?";
        }
        try{
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, buku.getJudulbuku());
            preStmt.setString(2, buku.getPengarang());
            preStmt.setString(3, buku.getTahunterbit());
            preStmt.setString(4, buku.getIdkategori());
            preStmt.setString(5, buku.getIdpenerbit());
            preStmt.setString(6, buku.getIdgenre());
            if (buku.getUrlebook().equals("")) {
                preStmt.setString(7, null);
            }else{
                preStmt.setString(7, buku.getUrlebook());
            }
            if (buku.getUrlgambar().equals("")) {
                preStmt.setString(8, null);
            }else{
                preStmt.setString(8, buku.getUrlgambar());
            }
            preStmt.setInt(9, buku.getStokbuku());
            preStmt.setString(10, buku.getKodebuku());
            preStmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Ada Error : " + ex);
        }
    }
    
    public Buku getDtBuku(String kodeBuku){
        String search = "SELECT * from buku where kodebuku = ?";
        Buku buku = new Buku();
        try {
            preStmt = koneksi.prepareStatement(search);
            preStmt.setString(1, kodeBuku);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                buku.setKodebuku(rs.getString("kodebuku"));
                buku.setJudulbuku(rs.getString("judulbuku"));
                buku.setPengarang(rs.getString("pengarang"));
                buku.setTahunterbit(rs.getString("tahunterbit"));
                buku.setIdkategori(rs.getString("idkategori"));
                buku.setIdpenerbit(rs.getString("idpenerbit"));
                buku.setIdgenre(rs.getString("idgenre"));
                buku.setUrlebook(rs.getString("urlebook"));
                buku.setUrlgambar(rs.getString("urlgambar"));
                buku.setStokbuku(rs.getInt("stokbuku"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Ada kesalahan : "+ex);
        }
        return buku;
    }
    
    public void hapus(String kodebuku) {
        String sql = "delete from buku where kodebuku = ?";  
        try {
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, kodebuku);
            preStmt.executeUpdate();
            
        } catch (SQLException ex) {
           System.out.println("Ada kesalahan : "+ex);
        }
    }
    
    public void insertBuku(Buku buku) throws SQLException {
        PreparedStatement pstmt = koneksi.prepareStatement("INSERT INTO Buku (kodebuku, judulbuku, pengarang, tahunterbit, idkategori, idpenerbit, genre, stokbuku) VALUES (? ,?, ?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, buku.getKodebuku());
        pstmt.setString(2, buku.getJudulbuku());
        pstmt.setString(3, buku.getPengarang());
        pstmt.setString(4, buku.getTahunterbit());
        pstmt.setString(5, buku.getIdkategori());
        pstmt.setString(6, buku.getIdpenerbit());
        pstmt.setString(7, buku.getIdgenre());
        pstmt.setInt(8, buku.getStokbuku());
        pstmt.executeUpdate();
    }
    
//    public List<Buku> getBukuTerlaris(){
//        ArrayList<Buku> bukuList = new ArrayList<>();
//        String query = "SELECT * FROM buku";
//        try{
//            preStmt = koneksi.prepareStatement(query);
//            rs = preStmt.executeQuery();
//            while (rs.next()) {
//                Buku buku = new Buku();
//                buku.setKodebuku(rs.getString("kodebuku"));
//                buku.setJudulbuku(rs.getString("judulbuku"));
//                buku.setPengarang(rs.getString("pengarang"));
//                buku.setTahunterbit(rs.getString("tahunterbit"));
//                buku.setIdkategori(rs.getString("idkategori"));
//                buku.setIdpenerbit(rs.getString("idpenerbit"));
//                buku.setIdgenre(rs.getString("idgenre"));
//                buku.setUrlebook(rs.getString("urlebook"));
//                buku.setUrlgambar(rs.getString("urlgambar"));
//                buku.setStokbuku(rs.getInt("stokbuku"));
//                bukuList.add(buku);
//            }
//        }catch(SQLException ex){
//            System.out.println("Error on BukuDAO : " + ex);
//        }
//        return bukuList;
//    }
    
    public static void main(String[] args) {
        BukuDAO bd = new BukuDAO();
        List<Buku> bukuList = new ArrayList<>(); 
        bukuList = bd.getAllBuku();
        System.out.println("List : " + bukuList);
    }
}
