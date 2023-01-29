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
import java.util.ArrayList;
import java.util.List;
import models.Genre;

/**
 *
 * @author Lenovo
 */
public class GenreDAO {
    private final Connection koneksi;
    private PreparedStatement preStmt;
    private ResultSet rs;
    
    public GenreDAO(){ //konstruktor 
            koneksi = connection.ConnectDB.getConnection();
    }
    
    public List<Genre> getAllGenre() throws SQLException {
        ArrayList<Genre> genreList = new ArrayList<>();
        String query = "SELECT * FROM genre";
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setIdgenre(rs.getString("idgenre"));
                genre.setNamagenre(rs.getString("namagenre"));          
                genreList.add(genre);
            }
        return genreList;
    }
    
    public void insertGenre(Genre genre) throws SQLException {
        String sql = "INSERT INTO genre (namagenre, idgenre) VALUES (?, ?)";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, genre.getNamagenre());
            preStmt.setString(2, genre.getIdgenre());
            preStmt.executeUpdate();
    }
    
    public void updateGenre(Genre genre) throws SQLException{
        String sql = "UPDATE genre SET namagenre=? where idgenre=?";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, genre.getNamagenre());
            preStmt.setString(2, genre.getIdgenre());
            preStmt.executeUpdate();
    }
    
    public Genre getDtGenre(String id)throws SQLException{
        String search = "SELECT * from genre where idgenre = ?";
        Genre genre = new Genre();
            preStmt = koneksi.prepareStatement(search);
            preStmt.setString(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                genre.setIdgenre(rs.getString("idgenre"));
                genre.setNamagenre(rs.getString("namagenre")); 
            }
        return genre;
    }
    
    public void hapus(String id) throws SQLException {
        String sql = "delete from genre where idgenre = ?";  
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, id);
            preStmt.executeUpdate();
    }
}
