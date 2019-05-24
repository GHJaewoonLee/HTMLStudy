package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

	public List<RepBoard> selectByRows(int startRow, int endRow) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<RepBoard> list = new ArrayList<RepBoard>();
		
		try {
			Class.forName(DB_DRIVER);

			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			StringBuffer selectByRowsSQL = new StringBuffer();
			selectByRowsSQL.append("SELECT * ");
			selectByRowsSQL.append("FROM (SELECT rownum r, repboard.* ");
			selectByRowsSQL.append("	  FROM repboard ");
			selectByRowsSQL.append("	  START WITH parent_seq = 0 ");
			selectByRowsSQL.append("	  CONNECT BY PRIOR board_seq = parent_seq ");
			selectByRowsSQL.append("	  ORDER SIBLINGS BY board_seq DESC) ");
			selectByRowsSQL.append("WHERE r between ? and ?");
			pstmt = conn.prepareStatement(selectByRowsSQL.toString());
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RepBoard board = new RepBoard();
				board.setBoard_seq(rs.getInt("board_seq"));
				board.setParent_seq(rs.getInt("parent_seq"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_writer(rs.getString("board_writer"));
				board.setBoard_contents(rs.getString("board_contents"));
				board.setBoard_date(rs.getDate("board_date"));
				board.setBoard_password(rs.getString("board_password"));
				board.setBoard_viewcount(rs.getInt("board_viewcount"));
				
				list.add(board);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
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
		
		return list;
	}

	public int selectTotalCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int totalCount = 0;
		
		try {
			Class.forName(DB_DRIVER);

			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			StringBuffer selectTotalCountSQL = new StringBuffer();
			selectTotalCountSQL.append("SELECT count(*) ");
			selectTotalCountSQL.append("FROM repboard");
			pstmt = conn.prepareStatement(selectTotalCountSQL.toString());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			totalCount = -1;
			e.printStackTrace();
		} catch (SQLException e) {
			totalCount = -1;
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
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
		
		return totalCount;
	}

	public RepBoard selectBySequence(int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		RepBoard board = null;
		
		try {
			Class.forName(DB_DRIVER);

			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			StringBuffer selectBySequenceSQL = new StringBuffer();
			selectBySequenceSQL.append("SELECT board_subject, board_writer, board_contents, board_date, board_viewcount ");
			selectBySequenceSQL.append("FROM repboard ");
			selectBySequenceSQL.append("WHERE board_seq = ?");
			pstmt = conn.prepareStatement(selectBySequenceSQL.toString());
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new RepBoard();
				board.setBoard_seq(seq);
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_writer(rs.getString("board_writer"));
				board.setBoard_contents(rs.getString("board_contents"));
				board.setBoard_date(rs.getDate("board_date"));
				board.setBoard_viewcount(rs.getInt("board_viewcount"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
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
		
		return board;
	}
	
	
//	public static void main(String[] args) {
//		RepBoardDAO boardDAO = new RepBoardDAO();
//		RepBoard board = new RepBoard();
//		board.setBoard_subject("Test subject");
//		board.setBoard_writer("HongGilDong");
//		board.setBoard_contents("Once time upon ago...");
//		board.setBoard_password("1234");
//		board.setParent_seq(9);
//		boardDAO.insert(board);
//		
//		List<RepBoard> list = boardDAO.selectByRows(1, 10);
//		for (RepBoard repBoard : list) {
//			System.out.println(list.indexOf(repBoard));
//			System.out.println("Contents : " + repBoard.getBoard_contents());
//			System.out.println("Password : " + repBoard.getBoard_password());
//			System.out.println("Current sequence : " + repBoard.getBoard_seq());
//			System.out.println("Subject : " + repBoard.getBoard_subject());
//			System.out.println("View count : " + repBoard.getBoard_viewcount());
//			System.out.println("Writer : " + repBoard.getBoard_writer());
//			System.out.println("Parent sequence : " + repBoard.getParent_seq());
//			System.out.println("Date : " + repBoard.getBoard_date());
//			System.out.println("===========================================");
//		}
//	}
}
