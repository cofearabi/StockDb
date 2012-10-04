//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;

//import java.sql.*;

public class StockDbC {

	/**
	 * @param args
	 */
	StockDbC(String filename, String jikoku ,String kakaku,String nichi) {
		
		
		if(kakaku.equals("---")){
			kakaku="-1";
		}
		
		System.out.println("Inserting values in Mysql database table!");
		  Connection con = null;
		  String url = "jdbc:mysql://localhost:3306/";
		  String db = "stock_db";
		  String driver = "com.mysql.jdbc.Driver";
		  try{
		  Class.forName(driver);
		  con = DriverManager.getConnection(url+db,"root","root");
		  try{
		  Statement st = con.createStatement();
		  int val = st.executeUpdate("INSERT stock VALUES("+
		  Integer.parseInt(filename)+","+
		  "'"+ nichi + "'"+","+ "'"+jikoku+"'"+","+
				  Integer.parseInt(kakaku)+")");
		  System.out.println("return is "+  val + ":1 row affected");
		  }
		  catch (SQLException s){
		  System.out.println("SQL statement is not executed! " + s.toString());
		  }
		  }
		  catch (Exception e){
		  e.printStackTrace();
		  }
		
		
		
	}
	
	 
	 
	 

}





	
	
	
	
	

