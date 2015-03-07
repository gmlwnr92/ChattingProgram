package com.team.chatPro;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

public class MemberDao1 { //연결된 디비에서 쿼리호출을값을 리턴하는 클래스
	//디비 연결한 정보 상태 결과 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public MemberDao1() { //기본생성자
	}
	
	public Vector<Vector<Object>> getLogMemberList1(String id){ //로그인한 멤버를 뽑는  
		String selectMembers1 =
				"SELECT name, gender, addr, age, job FROM member_test where cno= 1 and id != '"+id+"'";
		//쿼리
		Vector<Vector<Object>> memberList1 = null; //백터<백터<오브젝트>> 멤버리스트1 초기화
		try {
			//연결과정 생략함
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(selectMembers1);
			rs = pstmt.executeQuery();
			memberList1 = new Vector<Vector<Object>>(); //멤버리스트1 백터에 객체생성
				while(rs.next()){
				//rs.next(); // rs의 값 가져오기
				Vector<Object> member = new Vector<Object>(); //벡터<오브젝트 멤버 객체생성			
				member.add(rs.getString(1)); //멤버에 rs 1칼럼 값을 추가한다. 이하생략
				member.add(rs.getString(2));
				member.add(rs.getString(3));
				member.add(rs.getString(4));
				member.add(rs.getString(5));
				memberList1.add(member); //벡터<벡터<오브젝트 멤버리스트1에 벡터<오브젝트 멤버에 값을 추가한다
				}
			} catch (SQLException e) {
			e.printStackTrace();
		} finally { //에러든 뭐든 다 실행하고 마지막에 실행함 이하 생략
			DBManager.close(conn, pstmt, rs); // DBManager 클래스를 이용해 DB 작업과 관련된 객체를 닫는다.
		}
		return memberList1; //벡터<벡터<오브젝트의 멤버리스트1를 리턴한다
	}
	
	public Vector<Vector<Object>> getMemberList1(String id){ //랜덤한 멤버를 뽑는 겟멤버리스트1 
		Random rnd = new Random();
		
		int num = 0; //mno가 랜덤으로하기
		int aum = 0;		

		try {
			String sqlNum = "SELECT count(name) FROM member_test";
			conn = DBManager.getConnection();
			rs = conn.prepareStatement(sqlNum).executeQuery();
			rs.next();
			num = Integer.parseInt(rs.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		aum = rnd.nextInt(num);
		
		String selectMembers1 =
				"SELECT name, gender, addr, age, job FROM member_test "
				+ "where mno= "+aum+"";
		//쿼리
		Vector<Vector<Object>> memberList1 = null; //백터<백터<오브젝트>> 멤버리스트1 초기화
		try {
			//연결과정 생략함
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(selectMembers1);
			rs = pstmt.executeQuery();
			memberList1 = new Vector<Vector<Object>>(); //멤버리스트1 백터에 객체생성
				if(rs.next()){ // rs의 값 가져오기
				Vector<Object> member = new Vector<Object>(); //벡터<오브젝트 멤버 객체생성			
				member.add(rs.getString(1)); //멤버에 rs 1칼럼 값을 추가한다. 이하생략
				member.add(rs.getString(2));
				member.add(rs.getString(3));
				member.add(rs.getString(4));
				member.add(rs.getString(5));
				memberList1.add(member); //벡터<벡터<오브젝트 멤버리스트1에 벡터<오브젝트 멤버에 값을 추가한다
				}
			} catch (SQLException e) {
			e.printStackTrace();
		} finally { //에러든 뭐든 다 실행하고 마지막에 실행함 이하 생략
			DBManager.close(conn, pstmt, rs); // DBManager 클래스를 이용해 DB 작업과 관련된 객체를 닫는다.
		}
		return memberList1; //벡터<벡터<오브젝트의 멤버리스트1를 리턴한다
	}
	
	//내 정보 가져오기
	public Vector<Vector<Object>> getMyList1(String id) {
		String selectMembers1 =
					"SELECT name, gender, addr, age, job FROM member_test where id = '"+id+"'";
		Vector<Vector<Object>> myList1 = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(selectMembers1);
			rs = pstmt.executeQuery();
			myList1 = new Vector<Vector<Object>>();
				//한명만 나오게
					rs.next();
					Vector<Object> member = new Vector<Object>();		
					member.add(rs.getString(1));
					member.add(rs.getString(2));
					member.add(rs.getString(3));
					member.add(rs.getString(4));
					member.add(rs.getString(5));						
					myList1.add(member);			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// DBManager 클래스를 이용해 DB 작업과 관련된 객체를 닫는다.
				DBManager.close(conn, pstmt, rs);
			}
			return myList1;
		}
	
	public int addMember(Member1 member){ //회원추가메소드 왜 인트형인가? 그냥 보이드해도 되지않나?
		String insertMember = "INSERT INTO member_test VALUES("
				+ "SEQ_MEMBER_TEST_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
		//쿼리문
		int result = 0; //인트형 메소드라 결과값을 숫자로한다
		try {
			conn = DBManager.getConnection(); //디비연결			
			pstmt = conn.prepareStatement(insertMember); //회원가입, 조인의 인서트멤버 내용을 상태에 보낸다.
			pstmt.setString(1, member.getId()); //멤버1에 저장된 멤버의 겟아이디를 상태 1에 셋한다.
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getAddr());
			pstmt.setString(7, member.getAge());
			pstmt.setString(8, member.getJob());
			pstmt.setString(9, member.getMail());			
			result = pstmt.executeUpdate(); //결과에 세팅이 다 된 상태를 넣어 업데이트를 한다.

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt); //생략함
		}
		return result;
	}
	
	public void chatMember(String id) { //채팅중으로 상태 바꾸기
		String modifyMember = "UPDATE member_test SET cno = 2 WHERE id= '"+id+"'";		
		int result = 0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(modifyMember);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			DBManager.close(conn, pstmt);
		}		
	}
	
	public void connectMember(String id) { //접속중으로 상태 바꾸기
		String modifyMember = "UPDATE member_test SET cno = 1 WHERE id= '"+id+"'";		
		int result = 0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(modifyMember);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			DBManager.close(conn, pstmt);
		}		
	}
	
	public void disconnectMember(String id) { //접속해제으로 상태 바꾸기
		String modifyMember = "UPDATE member_test SET cno = 0 WHERE id= '"+id+"'";		
		int result = 0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(modifyMember);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			DBManager.close(conn, pstmt);
		}		
	}
	
	public void allDisconnectMember() { //접속해제으로 상태 바꾸기
		String modifyMember = "UPDATE member_test SET cno = 0 WHERE cno in('1', '2')";		
		int result = 0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(modifyMember);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			DBManager.close(conn, pstmt);
		}		
	}
	
	public boolean loginTest(String id, String pass){ //로그인테스트 메소드
		boolean flag = false; //불린을 실패상태로 초기화
		// 스트링 sql 초기화
		String sql = "";
        String getPass = "";
		try {
			conn = DBManager.getConnection(); //연결
			sql = "select password from member_test where id = ?";
			pstmt = conn.prepareStatement(sql); //문장을 상태에 넣음
			pstmt.setString(1, id); // 메소드에 들어오는 id값을 쿼리의 ?에 넣는다
			rs = pstmt.executeQuery(); //결과에 쿼리상태를 넣음

			if (rs.next()) { //결과에 들어간 내용에 대해
				getPass = rs.getString("password"); //쿼리에 나온 패스워드값 스트링에 넣음		
				if(getPass.equals(pass)){ //값이 정상출력및 트루면 트루값으로 바꾼다
					flag = true;
				}				
			}
		} catch (SQLException e) {

		} finally { //닫음.
			DBManager.close(conn, pstmt, rs);
		}
		return flag; //플래그값 리턴
	}
	
	public int confirmId(String id){ //중복확인 스트링으로 받고 인트로 리턴하는 메소드
		String sql=""; //초기화
		int x=-1; //-1이면 사용가능한 아이디임
		try{
			conn= DBManager.getConnection(); //연결
			sql="select * from MEMBER_test where id= ?"; //쿼리문
			pstmt = conn.prepareStatement(sql); //쿼리문 상태에 넣음
			pstmt.setString(1, id); //쿼리의 첫번째 ? 에 메소드로 들어온id넣음
			rs= pstmt.executeQuery();  // 상태를 결과에 반영. 결과 나옴
		 
			if(rs.next()){ //나온 결과가 잘 나왔으면
				x=1; //해당아이디 있음			
			}else{
				x=-1; //해당아이디 없음
			}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{ //닫음
				DBManager.close(conn, pstmt, rs);
			}       
			return x; //x리턴
	}
}
