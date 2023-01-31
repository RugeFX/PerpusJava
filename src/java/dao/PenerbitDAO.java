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
import models.Penerbit;

/**
 *
 * @author Lenovo
 */
public class PenerbitDAO {
    private final Connection koneksi;
    private PreparedStatement preStmt;
    private ResultSet rs;
    
    private SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
    public PenerbitDAO(){ //konstruktor 
            koneksi = connection.ConnectDB.getConnection();
    }
    
    public List<Penerbit> getAllPenerbit() throws SQLException {
        ArrayList<Penerbit> penerbitList = new ArrayList<>();
        String query = "SELECT * FROM penerbit";
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Penerbit penerbit = new Penerbit();
                penerbit.setIdpenerbit(rs.getString("idpenerbit"));
                penerbit.setNamapenerbit(rs.getString("namapenerbit"));            
                penerbitList.add(penerbit);
            }
        return penerbitList;
    }
    
    public void insertPenerbit(Penerbit penerbit) throws SQLException {
        String sql = "INSERT INTO penerbit (namapenerbit, idpenerbit) VALUES (?, ?)";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, penerbit.getNamapenerbit());
            preStmt.setString(2, penerbit.getIdpenerbit());
            preStmt.executeUpdate();
    }
    
    public void updatePenerbit(Penerbit penerbit) throws SQLException{
        String sql = "UPDATE penerbit SET namapenerbit=? where idpenerbit=?";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, penerbit.getNamapenerbit());
            preStmt.setString(2, penerbit.getIdpenerbit());
            preStmt.executeUpdate();
    }
    
    public Penerbit getDtPenerbit(String id) throws SQLException{
        String search = "SELECT * from penerbit where idpenerbit = ?";
        Penerbit penerbit = new Penerbit();
            preStmt = koneksi.prepareStatement(search);
            preStmt.setString(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
               penerbit.setIdpenerbit(rs.getString("idpenerbit"));
               penerbit.setNamapenerbit(rs.getString("namapenerbit"));  
            }
        return penerbit;
    }
    
    public void hapus(String id) throws SQLException {
        String sql = "delete from penerbit where idpenerbit = ?";  
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, id);
            preStmt.executeUpdate();
    }
}
