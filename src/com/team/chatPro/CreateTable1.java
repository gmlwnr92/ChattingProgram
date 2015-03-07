package com.team.chatPro;

import java.sql.*;

public class CreateTable1 { //테이블 생성 클래스. 단독디비연결
	public static void main(String[] args) { //메인
		//접속하기 위해 필요한 정보 스트링
		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String DB_USER = "scott";
		String DB_PASSWORD = "TIGER";
		try {			
			Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버로딩
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 커넥션, 상태, 결과 초기화
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 쿼리저장할 스트링
		String sqlCT;
		String sqlCQ;
		
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// Statement를 가져온다.
			stmt = conn.createStatement();
			// SQL문을 실행한다.
			//테이블 생성쿼리
			sqlCT = "CREATE TABLE member_test ( " +
					"MNO NUMBER CONSTRAINT PK_NAMECARD PRIMARY KEY, " +
					"ID VARCHAR2(25) NOT NULL, " +
					"PASSWORD VARCHAR2(20) NOT NULL, " +
					"NAME    VARCHAR2(10) NOT NULL, " +
					"GENDER VARCHAR2(6) NOT NULL, " +
					"PHONE  VARCHAR2(14) NOT NULL, " +
					"ADDR VARCHAR2(40) NOT NULL, " +
					"AGE VARCHAR2(10) NOT NULL, " +
					"JOB VARCHAR2(10), " +
					"MAIL VARCHAR2(30) NOT NULL, " +
					"CNO NUMBER(30))";
				stmt.executeUpdate(sqlCT); //쿼리문 상태에 넣기
				
				System.out.println("테이블 생성완료");
				//시퀸스 생성 쿼리
				sqlCQ = "create sequence SEQ_MEMBER_TEST_NO start with 1 INCREMENT by 1 maxvalue 100 minvalue 1 nocycle nocache";
				stmt.executeUpdate(sqlCQ); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Statement를 닫는다.
				stmt.close();
				// Connection를 닫는다.
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
}
