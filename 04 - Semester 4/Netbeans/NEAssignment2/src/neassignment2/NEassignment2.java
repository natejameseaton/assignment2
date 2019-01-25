/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neassignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0711874
 */
public class NEassignment2 {

    public static void main(String[] args) {
        // Declare string name, user input name
        String name = "";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the manufacturer name:");
        name = keyboard.nextLine();
        
        String sqla = "SELECT MANUFACTURER_ID FROM MANUFACTURER WHERE NAME = ?";
        String sqlb = "SELECT PRODUCT_ID, DESCRIPTION FROM PRODUCT WHERE MANUFACTURER_ID = ? ORDER BY DESCRIPTION ASC";
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            String jdbc = "jdbc:derby://localhost:1527/sample";
            
            Connection conn = DriverManager.getConnection(jdbc, "app", "app");
            
            PreparedStatement prepstat = conn.prepareStatement(sqla);
            prepstat.setString(1, name);
            ResultSet rs = prepstat.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("MANUFACTURER_ID");
                PreparedStatement prepstat2 = conn.prepareStatement(sqlb);
                prepstat2.setInt(1, id);
                ResultSet rs2 = prepstat2.executeQuery();
                while (rs2.next()) {
                    System.out.printf("%s: %s\n", rs2.getString("PRODUCT_ID"), rs2.getString("DESCRIPTION"));
                            
                }
            
            }
               
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NEassignment2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NEassignment2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
