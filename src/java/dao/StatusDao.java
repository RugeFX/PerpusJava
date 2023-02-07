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
import models.Status;

/**
 *
 * @author Naya
 */
public class StatusDao {
    private final Connection koneksi;
    private PreparedStatement preStmt;
    private ResultSet rs;
    
    public StatusDao(){ //konstruktor 
            koneksi = connection.ConnectDB.getConnection();
    }
    
    public List<Status> getAllStatus() throws SQLException {
        ArrayList<Status> statusList = new ArrayList<>();
        String query = "SELECT * FROM status";
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Status status = new Status();
                status.setIdstatus(rs.getString("idstatus"));
                status.setNamastatus(rs.getString("namastatus"));          
                statusList.add(status);
            }
        return statusList;
    }
    
    public void insertStatus(Status status) throws SQLException {
        String sql = "INSERT INTO status (namastatus, idstatus) VALUES (?, ?)";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, status.getNamastatus());
            preStmt.setString(2, status.getIdstatus());
            preStmt.executeUpdate();
    }
    
    public void updateStatus(Status status) throws SQLException{
        String sql = "UPDATE status SET namastatus=? where idstatus=?";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, status.getNamastatus());
            preStmt.setString(2, status.getIdstatus());
            preStmt.executeUpdate();
    }
    
    public Status getDtStatus(String id) throws SQLException{
        String search = "SELECT * from status where idstatus = ?";
        Status status = new Status();
            preStmt = koneksi.prepareStatement(search);
            preStmt.setString(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
                status.setIdstatus(rs.getString("idstatus"));
                status.setNamastatus(rs.getString("namastatus")); 
            }
        return status;
    }
    
    public void hapus(String id) throws SQLException {
        String sql = "delete from status where idstatus = ?";  
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, id);
            preStmt.executeUpdate();
    }
//        public static void main(String[] args) { 
//        StatusDao statusdao = new StatusDao(); 
//        System.out.println(statusdao);
//        Status status = new Status();
//        status.setIdstatus("2");
//        status.setNamastatus("Naya");
//            try {
//                statusdao.insertStatus(status);
//            } catch (Exception e) {
//                
//            }
//    } 
    
}