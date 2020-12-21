
package Conex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Conexion {
    Connection con;
    public Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://35.185.90.173:3306/DreamHome?user=estudiante","estudiante","XTGS41zA");
        } catch (Exception e) {
            System.err.println("Error:" +e);
        }
    }
    public static void main(String[] args) {
        Conexion cn=new Conexion();
        Statement st;
        ResultSet rs;
        try {
            st=cn.con.createStatement();
            rs=st.executeQuery("select * from PropertyForRent where rent > 350");
            while (rs.next()) {                
                System.out.println(rs.getString("propertyNo")+"    " +rs.getString("street")+"   " +rs.getString("rooms"));
            }
            cn.con.close();
        } catch (Exception e) {
        }
        
    }
}