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
    
    public List<Penerbit> getAllAnggota() {
        ArrayList<Penerbit> penerbitList = new ArrayList<>();
        String query = "SELECT * FROM penerbit";
        try{
            preStmt = koneksi.prepareStatement(query);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                Penerbit penerbit = new Penerbit();
                penerbit.setIdpenerbit(rs.getString("idpenerbit"));
                penerbit.setNamapenerbit(rs.getString("namapenerbit"));            
                penerbitList.add(penerbit);
            }
        }catch(SQLException ex){
            System.out.println("Error on anggotaDao : " + ex);
        }
        return penerbitList;
    }
    
    public void simpanPenerbit(Penerbit penerbit, String mode) throws SQLException {
        String sql = null;
        if (mode.equalsIgnoreCase("insert")) {
            sql = "INSERT INTO penerbit (namapenerbit, idpenerbit VALUES (?, ?)";
        }else if(mode.equalsIgnoreCase("update")){
            sql = "UPDATE penerbit SET namapenerbit=? where idpenerbit=?";
        }
        try{
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, penerbit.getNamapenerbit());
            preStmt.setString(2, penerbit.getIdpenerbit());
            preStmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Ada Error : " + ex);
        }
    }
    
    public Penerbit getDtPenerbit(String id){
        String search = "SELECT * from penerbit where idpenerbit = ?";
        Penerbit penerbit = new Penerbit();
        try {
            preStmt = koneksi.prepareStatement(search);
            preStmt.setString(1, id);
            rs = preStmt.executeQuery();
            if (rs.next()) {
               penerbit.setIdpenerbit(rs.getString("idpenerbit"));
               penerbit.setNamapenerbit(rs.getString("namapenerbit"));  
            }
            
        } catch (SQLException ex) {
            System.out.println("Ada kesalahan : "+ex);
        }
        return penerbit;
    }
    
    public void hapus(String id) {
        String sql = "delete from penerbit where idpenerbit = ?";  
        try {
            preStmt = koneksi.prepareStatement(sql);
            preStmt.setString(1, id);
            preStmt.executeUpdate();
            
        } catch (SQLException ex) {
           System.out.println("Ada kesalahan : "+ex);
        }
    }
}
