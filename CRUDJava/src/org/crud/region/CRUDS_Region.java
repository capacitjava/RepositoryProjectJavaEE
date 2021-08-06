package org.crud.region;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//CRUD CREATE READ UPDATE DELETE
//INVOCAR EL PROCEDIMIENTO ALMACENADO: PROC

public class CRUDS_Region {
	
	static Connection connection = null;
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectDataBase()throws IOException, SQLException{
		try {
			Class.forName(driver).newInstance();
			System.out.println("CARGO DRIVER: ojdbc6.jar");
		} catch (Exception e) {
			System.out.println("EXCEPTION DRIVER:" + e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(URL,"System","Astrix12");
			System.out.println("CONEXIÓN EXITOSA: Oracle11g");
		} catch (Exception e) {
			System.out.println("EXCEPTION CONNECTION:" + e.getMessage());
		}
	}
	
	public static void addS_Region(int id, String name) throws IOException,SQLException{
		try {
			connectDataBase();
			//QUERIE INSERT
			//                                                  1,2
			String sql = "INSERT INTO S_REGION (ID,NAME) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("INSERTO:" + id + "," + name);
		} catch (Exception e) {
			System.out.println("EXCEPTION:" + e.getMessage());
		}
	}
	
	public static void updateS_Region(String name, int id) throws IOException,SQLException{
		try {
			connectDataBase();
			//QUERIE UPDATE
			//                                       1            2
			String sql = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("UPDATE:" + id + "," + name);
		} catch (Exception e) {
			System.out.println("EXCEPTION:" + e.getMessage());
		}
	}
	
	public static void deleteS_Region(int id) throws IOException,SQLException{
		try {
			connectDataBase();
			//QUERIE DELETE
			//                                            1
			String sql = "DELETE FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("DELETE:" + id);
		} catch (Exception e) {
			System.out.println("EXCEPTION:" + e.getMessage());
		}
	}
	
	public static void selectAllS_Region() throws IOException,SQLException{
		try {
			connectDataBase();
			//QUERIE DELETE
			String sql = "SELECT * FROM S_REGION";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
				while (rSet.next()) {
					System.out.println(rSet.getInt("id")+","+rSet.getString("name"));
				}
			ps.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("EXCEPTION:" + e.getMessage());
		}
	}
	
	public static void invocaProcedure(int id, String name) throws IOException,SQLException{
		try {
			connectDataBase();
			//Interface: exclusivamente para invocar procedimientos.
			CallableStatement cs = connection.prepareCall("CALL proc(?,?)");
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.execute();
			cs.close();
			connection.close();
			System.out.println("EXECUTE: PROC");
		} catch (Exception e) {
			System.out.println("EXCEPTION:" + e.getMessage());
		}
	}
	
	public static void main(String[] args) throws IOException, SQLException{
		//addS_Region(12, "REG-12");
		//updateS_Region("TUXTLA", 12);
		//deleteS_Region(12);
		//selectAllS_Region();
		invocaProcedure(13, "REG-PROC13");
	}
}