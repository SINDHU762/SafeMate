/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busSystem;
import java.sql.*;
/**
 *
 * @author ELCOT
 */
public class DbConnect {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/safemate","root","");
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }
}
