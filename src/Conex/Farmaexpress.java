
package Conex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Farmaexpress {


	//JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://35.185.90.173:3306";                 

	//Database credentials
	static final String USER = "estudiante";
 	static final String PASS = "XTGS41zA";
   
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
   
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			System.out.println("Creating database...");
			stmt = conn.createStatement();
      
			String sql = "CREATE DATABASE FARMAEXPRESS";
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");


			//Create tables 
			createTables();


		}catch(SQLException se){
			//Handle errors for JDBC
    		se.printStackTrace();
    	}catch(Exception e){
    		//Handle errors for Class.forName
    		e.printStackTrace();
    	}finally{
    		//finally block used to close resources
			try{
				if(stmt!=null)
				stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
      
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

   		System.out.println("Goodbye!");

	}//end main

	//Table declaration 
	public void createTables() throws SQLException {
		
		String createUser ="create table USUARIO " + "(cedula varchar(10) NOT NULL, " +
      			"nombre varchar(50) NOT NULL, " + "apellido varchar(50) NOT NULL, " +
      			"email varchar(100) NOT NULL, " + "telefono_celular varchar(50) NOT NULL, " + 
      			"telefono_1 varchar(50) NOT NULL, " + "telefono_2 varchar(50) , "
      			"PRIMARY KEY (cedula))" ;

      	String createAddress ="create table DIRECCION" + "(id_direccion int NOT NULL, " +
      			"latitud numeric(10,4) NOT NULL, " + "longitud numeric(10,4) NOT NULL, " +
      			"calle_principal varchar(50) NOT NULL, " + "calle_principal varchar(50) NOT NULL, " +
      			"cedula varchar(10) NOT NULL, " + "id_provincia int NOT NULL, "
      			"PRIMARY KEY (id_direccion), " +
      			"FOREIGN KEY (cedula) REFERENCES USUARIO (cedula), " +
      			"FOREIGN KEY (id_provincia) REFERENCES PROVINCIA (id_provincia))";

    	String createProvince ="create table PROVINCIA " + "(id_provincia varchar(32) NOT NULL, " +
      			"nombre varchar(50) NOT NULL, " + "canton varchar(50) NOT NULL, " +
      			"ciudad varchar(50) NOT NULL, " +
      			"PRIMARY KEY (id_provincia))";

      	String createRecord ="create table HISTORIAL " + "(id_historial int NOT NULL, " +
      			"fecha_consulta date NOT NULL, " + "serie varchar(20) NOT NULL, " +
      			"id_detalle_historial integer NOT NULL, " +
      			"PRIMARY KEY (id_historial), " +
      			"FOREIGN KEY (serie) REFERENCES MEDICINE (serie), " +
      			"FOREIGN KEY (id_detalle_historial) REFERENCES SUPPLIERS (id_detalle_historial))";

      	String createMedicine ="create table MEDICINE " + "(id_provincia varchar(32) NOT NULL, " +
      			"nombre varchar(50) NOT NULL, " + "canton varchar(50) NOT NULL, " +
      			"ciudad varchar(50) NOT NULL, " +
      			"PRIMARY KEY (id_provincia))";

      	String createHistory_Detail ="create table HISTORY_DETAIL " + "(serie varchar(20) NOT NULL, " +
      			"nombre_generico varchar(50) NOT NULL, " +
      			"nombre_comercial varchar(50) NOT NULL, " +
      			"composicion varchar(50) NOT NULL, " +
      			"precio numeric(6,2) NOT NULL, " +
      			"id_presentacion integer NOT NULL, " +
      			"id_lote varchar(12) NOT NULL, " +
      			"id_restriccion integer NOT NULL, " +
      			"id_categoria integer NOT NULL, " +
      			"PRIMARY KEY (serie), " +
      			"FOREIGN KEY (id_presentacion) REFERENCES MEDICINE (id_presentacion), " +
      			"FOREIGN KEY (id_lote) REFERENCES MEDICINE (id_lote), " +
      			"FOREIGN KEY (id_restriccion) REFERENCES MEDICINE (id_restriccion), " +
      			"FOREIGN KEY (id_categoria) REFERENCES MEDICINE (id_categoria))";

      	String createLot ="create table LOTE " + "(id_lote varchar(12) NOT NULL, " +
      			"fecha_caducidad date NOT NULL, " + 
      			"PRIMARY KEY (id_lote))";

      	String createPresentation ="create table PRESENTACION " + "(id_presentacion integer NOT NULL, " +
      			"presentacion varchar(50) NOT NULL, " + 
      			"PRIMARY KEY (id_presentacion))";

      	String createRestriction ="create table RESTRICCION " + "(id_restriccion integer NOT NULL, " +
      			"resticcion varchar(50) NOT NULL, " + 
      			"PRIMARY KEY (id_restriccion))";

      	String createCategory ="create table CATEGORIA " + "(id_categoria integer NOT NULL, " +
      			"categoria varchar(50) NOT NULL, " + 
      			"PRIMARY KEY (id_categoria))";

		String createAvailability ="create table DISPONIBILIDAD " + "(id_disponibilidad integer NOT NULL, " +
      			"id_farmacia integer NOT NULL, " + "serie varchar(20) NOT NULL, " +
      			"stock integer NOT NULL, " +
      			"PRIMARY KEY (id_disponibilidad), " +
      			"FOREIGN KEY (id_farmacia) REFERENCES FARMACIA (id_farmacia), " +
      			"FOREIGN KEY (serie) REFERENCES MEDICINE (serie))";

      	String createPharmacy ="create table FARMACIA " + "(id_farmacia integer NOT NULL, " +
      			"nombre varchar(50) NOT NULL, " + "id_direccion integer NOT NULL, " +
      			"PRIMARY KEY (id_farmacia)), " +
      			"FOREIGN KEY (id_direccion) REFERENCES DIRECCION (id_direccion))";
    	
    	try (Statement stmt = con.createStatement()) {
			stmt.executeUpdate(createUser);
			stmt.executeUpdate(createAddress);
			stmt.executeUpdate(createProvince);
			stmt.executeUpdate(createRecord);
			stmt.executeUpdate(createMedicine);
			stmt.executeUpdate(createHistory_Detail);
			stmt.executeUpdate(createLot);
			stmt.executeUpdate(createPresentation);
			stmt.executeUpdate(createRestriction);
			stmt.executeUpdate(createCategory);
			stmt.executeUpdate(createAvailability);
			stmt.executeUpdate(createPharmacy);
    	} catch (SQLException e) {
      		JDBCTutorialUtilities.printSQLException(e);
    	}



  	}


}//end JDBCExample


//     Connection con;
//     public Conexion(){
//         try {
//             Class.forName("com.mysql.jdbc.Driver");
//             con=DriverManager.getConnection("jdbc:mysql://35.185.90.173:3306/DreamHome?user=estudiante","estudiante","XTGS41zA");
//         } catch (Exception e) {
//             System.err.println("Error:" +e);
//         }
//     }
//     public static void main(String[] args) {
//         Conexion cn=new Conexion();
//         Statement st;
//         ResultSet rs;
//         try {
//             st=cn.con.createStatement();
//             rs=st.executeQuery("select * from PropertyForRent where rent > 350");
//             while (rs.next()) {                
//                 System.out.println(rs.getString("propertyNo")+"    " +rs.getString("street")+"   " +rs.getString("rooms"));
//             }
//             cn.con.close();
//         } catch (Exception e) {
//         }
        
//     }
// }