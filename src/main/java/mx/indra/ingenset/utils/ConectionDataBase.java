package mx.indra.ingenset.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionDataBase {
	
/*	public static void main(String agrs[]){
		
		ConectionDataBase con = new ConectionDataBase();
		
		Connection conexion = null;
		conexion = con.getConection();
		System.out.println("conexion :: " + conexion);
	}
*/	
	public ConectionDataBase(){
		
	}
	
	public Connection getConection(){
		
      Connection conexion = null;
      
      try{
	
	      Class.forName("oracle.jdbc.OracleDriver"); 
	      String BaseDeDatos = "jdbc:oracle:thin:@129.152.42.181:1521:FENIXPRO";
	      conexion = DriverManager.getConnection(BaseDeDatos, "INGEN_USR","INGEN_USR");
	      
		}
		catch(SQLException sql) {
			sql.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return conexion;

	}

}
