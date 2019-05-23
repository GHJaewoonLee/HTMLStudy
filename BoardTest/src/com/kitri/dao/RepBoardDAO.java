package com.kitri.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kitri.dto.RepBoard;


public class RepBoardDAO {

	public static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String DB_URL = "jdbc:oracle:thin:@192.168.14.14:1521:orcl";
	public static final String DB_USERNAME = "kitri";
	public static final String DB_PASSWORD = "kitri";
	
	
	public void insert(RepBoard repBoard) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DB_DRIVER);

			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			StringBuffer insertSQL = new StringBuffer();
			insertSQL.append("insert into repboard (BOARD_SEQ, PARENT_SEQ, BOARD_SUBJECT, BOARD_WRITER, BOARD_CONTENTS, BOARD_DATE, BOARD_PASSWORD, BOARD_VIEWCOUNT) ");
			insertSQL.append("values (BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, systimestamp, ?, 0)");
			pstmt = conn.prepareStatement(insertSQL.toString());
			
			pstmt.setInt(1, repBoard.getParent_seq());
			pstmt.setString(2, repBoard.getBoard_subject());
			pstmt.setString(3, repBoard.getBoard_writer());
			pstmt.setString(4, repBoard.getBoard_contents());
			pstmt.setString(5, repBoard.getBoard_password());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	public static void main(String[] args) {
		RepBoardDAO boardDAO = new RepBoardDAO();
		RepBoard board = new RepBoard();
		board.setBoard_subject("Test subject");
		board.setBoard_writer("HongGilDong");
		board.setBoard_contents("Once time upon ago...");
		board.setBoard_password("1234");
		board.setParent_seq(9);
		boardDAO.insert(board);
	}*/
}
