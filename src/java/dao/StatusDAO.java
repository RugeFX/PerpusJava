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
 * @author zackf
 */
public class StatusDAO {
    private final Connection koneksi;
    private PreparedStatement preStmt;
    private ResultSet rs;
    
    private SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
    public StatusDAO(){ //konstruktor 
            koneksi = connection.ConnectDB.getConnection();
    }
    
    public List<Status> getAllStatus() throws SQLException {
        ArrayList<Status> statusList = new ArrayList<>();
        String query = "SELECT * FROM status";
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Status sta = new Status();
                sta.setIdstatus(rs.getInt("idstatus"));
                sta.setNamastatus(rs.getString("namastatus"));      
                statusList.add(sta);
            }
        return statusList;
    }
    
    public void updateStatus(Status sta) throws SQLException{
        String sql = "UPDATE status SET namastatus=? WHERE idstatus=?";
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setInt(1, sta.getIdstatus());
            preStmt.setString(2, sta.getNamastatus());
            preStmt.executeUpdate();
    }
    
    public void hapus(String id) throws SQLException {
        String sql = "delete from status where idstatus = ?";  
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, id);
            preStmt.executeUpdate();
    }
}
