package com.team.chatPro;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBManager {

	//디비연결추가
	//디비 연결을 위한 주소 ,드라이버, 아디, 비번을 스트링에 담음
		private static String CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
		private static String URL = "jdbc:oracle:thin:@192.168.99.66:1521:XE";
		private static String DB_ID = "scott";
		private static String DB_PASS = "TIGER";	
		private static Connection CONN; // 커넥션이라는 메소드 디비연결할때 씀
		//192.168.99.66  1234  1234
		
		public static Connection getConnection() {
			
			
			try {				
				/* 오라클 DBMS에 접속하기 위해 동적으로 OracleDriver를 메모리에 로딩하고
				 * OriverDriver 객체는 DriverManager의 static 멤버 변수에 저장된다.
				 * 즉 Class.forName()는 DriverManager 객체를 생성하고
				 * 지정한 드라이버를 DriverManager 객체에 등록해 주는 메소드이다. 
				 **/
				Class.forName(CLASS_NAME);
				
				/* DriverManager를 이용해 DB Connection 객체를 얻는다.
				 * DriverManager 객체는 Java 응용프로그램을 JDBC 드라이버와 
				 * 연결하고 실제 DBMS에 접속하여 활성화된 Connection 객체를 리턴 한다.
				 **/
				CONN = DriverManager.getConnection(URL, DB_ID, DB_PASS);
				
				
			} catch (ClassNotFoundException e) {
				
				System.out.println("Driver 로딩 실패!");
				System.out.println(e.getMessage());		
				return null;
				
			} catch (SQLException e) {
				
				System.out.println("SQLException 발생!");
				System.out.println(e.getMessage());
				e.printStackTrace();			
				return null;
			} /*catch (UnknownHostException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "DB가 닫혀있습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);

				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			// DB에 연결된 Connection 객체를 리턴 한다.
			return CONN;
}
	
		public static void close(Connection conn, PreparedStatement pstmt) {// DB 작업에 사용된 자원을 해제하는 메소드		
			try { //닫는 메소드 순서는 객체만든 순서 반대로 여긴 접속만 쿼리없음			
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();			
			} catch(SQLException e) { e.printStackTrace(); }
		}
		
		public static void close(Connection conn, 
				PreparedStatement pstmt, ResultSet rs) {// DB 작업에 사용된 자원, 쿼리를 해제하는 메소드	
			try {
				if(rs != null) rs.close(); //결과
				if(pstmt != null) pstmt.close(); //쿼리
				if(conn != null) conn.close();	//연결		
			} catch(SQLException e) { e.printStackTrace(); }		
		}	

}
