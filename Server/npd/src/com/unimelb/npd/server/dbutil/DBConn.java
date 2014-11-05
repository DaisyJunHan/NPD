package com.unimelb.npd.server.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private void getConn(){		
		try {
			Class.forName(Config.CLASS_NAME);
			String url = Config.DATABASE_URL+"://"+Config.SERVER_IP+":"+Config.SERVER_PORT+"/"+Config.DB_SID;
			String user = Config.USERNAME;
			String pwd = Config.PASSWORD;
			conn = DriverManager.getConnection(url,user,pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int execOther(final String strSQL,final Object[] params){
		this.getConn();		
		try {
			pstmt = conn.prepareStatement(strSQL);
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);	
			}			
			System.out.println("SQL:>"+strSQL);
			int i = pstmt.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} 
		
	}
	public ResultSet execQuery(final String strSQL,final Object[] params){
		this.getConn();		
		try {
			pstmt = conn.prepareStatement(strSQL);
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);	
			}
			System.out.println("SQL:>"+strSQL);
			rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	public void closeConn(){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
