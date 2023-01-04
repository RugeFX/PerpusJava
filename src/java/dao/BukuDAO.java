/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import models.Buku;
import connection.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class BukuDAO {
    public Connection conn = ConnectDB.getConnection();
    
    public List<Buku> getAllBuku() {
        List<Buku> bukuList = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Buku");
            while (rs.next()) {
                Buku buku = new Buku();
                buku.setKodebuku(rs.getString("kodebuku"));
                buku.setJudulbuku(rs.getString("judulbuku"));
                buku.setPengarang(rs.getString("pengarang"));
                buku.setTahunterbit(rs.getString("tahunterbit"));
                buku.setIdkategori(rs.getString("idkategori"));
                buku.setIdpenerbit(rs.getString("idpenerbit"));
                buku.setStokbuku(rs.getInt("stokbuku"));
                bukuList.add(buku);
            }
        }catch(SQLException ex){
            System.out.println("Error on BukuDAO : " + ex);
        }
        
        return bukuList;
    }
    
    public void insertBuku(Buku buku) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Buku (kodebuku, judulbuku, pengarang, tahunterbit, idkategori, idpenerbit, stokbuku) VALUES (?, ?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, buku.getKodebuku());
        pstmt.setString(2, buku.getJudulbuku());
        pstmt.setString(3, buku.getPengarang());
        pstmt.setString(4, buku.getTahunterbit());
        pstmt.setString(5, buku.getIdkategori());
        pstmt.setString(6, buku.getIdpenerbit());
        pstmt.setInt(7, buku.getStokbuku());
        pstmt.executeUpdate();
    }
    
    public static void main(String[] args) {
        BukuDAO bd = new BukuDAO();
        List<Buku> bukuList = new ArrayList<>(); 
        bukuList = bd.getAllBuku();
        System.out.println("List : " + bukuList);
    }
}
