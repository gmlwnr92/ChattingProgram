package com.team.chatPro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MemberDao {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public Vector<Vector<Object>> getMemberList() {
		String selectMemberList = "SELECT * FROM MEMBER_TEST ORDER BY MNO";
		Vector<Vector<Object>> memberList = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(selectMemberList);
			rs = pstmt.executeQuery();
			memberList = new Vector<Vector<Object>>();

			while (rs.next()) {
				Vector<Object> member = new Vector<Object>();
				member.add(rs.getInt(1));
				member.add(rs.getString(2));
				member.add(rs.getString(3));
				member.add(rs.getString(4));
				member.add(rs.getString(5));
				member.add(rs.getString(6));
				member.add(rs.getString(7));
				member.add(rs.getString(8));
				member.add(rs.getString(9));
				member.add(rs.getString(10));
				member.add(rs.getInt(11));

				memberList.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDao - getMemberList()");

		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return memberList;
	}

	/*public int addMember(String id, String password, String name, String gender, String phone, String addr, String age, String job, String mail) {

		String insertMember = "INSERT INTO member_test(MNO,ID,PASSWORD,NAME,GENDER,PHONE,ADDR,AGE,JOB,MAIL)"
				+ " VALUES(SEQ_MEMBER_TEST_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = -1;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(insertMember);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, gender);
			pstmt.setString(5, phone);
			pstmt.setString(6, addr);
			pstmt.setString(7, age);
			pstmt.setString(8, job);
			pstmt.setString(9, mail);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDao - addMember()");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}*/

	public int addMember(Vector<Object> member) {

		String insertMember = "INSERT INTO member_test(MNO,ID,PASSWORD,NAME,GENDER,PHONE,ADDR,AGE,JOB,MAIL)"
				+ " VALUES(SEQ_MEMBER_TEST_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		int result = -1;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(insertMember);
			pstmt.setString(1, member.get(0).toString());
			pstmt.setString(2, member.get(1).toString());
			pstmt.setString(3, member.get(2).toString());
			pstmt.setString(4, member.get(3).toString());
			pstmt.setString(5, member.get(4).toString());
			pstmt.setString(6, member.get(5).toString());
			pstmt.setString(7, member.get(6).toString());
			pstmt.setString(8, member.get(7).toString());
			pstmt.setString(9, member.get(8).toString());
			

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDao - addMember(Vector)");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

	/*public int modifyMember(int MNO, String name, String gender, String phone, String addr, String age, String job, String mail) {

		String updateMember = "UPDATE member_test SET "
				+ "name=?, gender=?, PHONE=?, ADDR=?, AGE=?, JOB=?, MAIL=? WHERE MNO=?";
		int result = -1;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(updateMember);

			pstmt.setString(1, name);
			pstmt.setString(2, gender);
			pstmt.setString(3, phone);
			pstmt.setString(4, addr);
			pstmt.setString(5, age);
			pstmt.setString(6, job);
			pstmt.setString(7, mail);
			pstmt.setInt(8, MNO);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDao - modifyMember()");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;

	}*/

	public int modifyMember(Vector<Object> member) {

		String updateMember ="UPDATE member_test SET "
				+"id=?,password=?, NAME=?, GENDER=?, PHONE=?, ADDR=?, AGE=?, JOB=?, MAIL=? WHERE MNO=?";
		int result = -1;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(updateMember);

			pstmt.setString(1, member.get(1).toString());
			pstmt.setString(2, member.get(2).toString());
			pstmt.setString(3, member.get(3).toString());
			pstmt.setString(4, member.get(4).toString());
			pstmt.setString(5, member.get(5).toString());
			pstmt.setString(6, member.get(6).toString());
			pstmt.setString(7, member.get(7).toString());
			pstmt.setString(8, member.get(8).toString());
			pstmt.setString(9, member.get(9).toString());
			pstmt.setInt(10, (int) member.get(0));

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDao - modifyMember(Vector)");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;

	}

	public int deleteMember(int row) {
		String deleteMember = "DELETE FROM member_test WHERE MNO=?";
		int result = -1;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(deleteMember);

			pstmt.setInt(1, row);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDao - deleteMember()");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;

	}

	public int getCurrentId() {
		String selectId = "SELECT MAX(MNO) FROM member_test";
		// String selectId = "SELECT member_seq.CURRVAL FROM dual";
		int result = 0;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(selectId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDao - getCurrentId()");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

}
