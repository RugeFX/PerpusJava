/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;

/**
 *
 * @author lenovo
 */
public class ConnectDB {
    static Connection conn;
    
    public static Connection getConnection(){
        if(conn == null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("perpus_pbo");
            data.setUser("root");
            data.setPassword("");
            try{
                conn = (Connection) data.getConnection();
                System.out.println("Connection succeeded");
            }catch(SQLException ex){
                System.out.println("Error ConnectDB : " + ex);
            }
        }
        return conn;
    }
}
