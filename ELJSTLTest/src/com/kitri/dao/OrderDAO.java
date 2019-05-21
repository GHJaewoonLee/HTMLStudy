package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.OrderInfo;
import com.kitri.dto.OrderInfoDetail;
import com.kitri.dto.Product;


public class OrderDAO {

	public static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String DB_URL = "jdbc:oracle:thin:@192.168.14.14:1521:orcl";
	public static final String DB_USERNAME = "kitri";
	public static final String DB_PASSWORD = "kitri";
	
	
	public void insert(OrderInfo info) {
		Connection conn = null;
		
		try {
			// Load JDBC driver
			Class.forName(DB_DRIVER);

			// Connect to DB
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			conn.setAutoCommit(false);
			insertInfo(conn, info);
			insertInfoDetail(conn, info.getDetails());	// info.getDetails() returns List<OrderInfoDetail>
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertInfo(Connection conn, OrderInfo info) throws SQLException {
		PreparedStatement pstmt = null;
		
		String insertInfoSQL = "";
		insertInfoSQL += "insert into order_info (order_no, order_id) ";
		insertInfoSQL += "values (order_seq.nextVal, ?)";
		
		try {
			pstmt = conn.prepareStatement(insertInfoSQL);
			pstmt.setString(1, info.getCustomer().getId());
			pstmt.executeUpdate();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertInfoDetail(Connection conn, List<OrderInfoDetail> infoDetail) throws SQLException {
		PreparedStatement pstmt = null;
		
		String insertInfoDetailSQL = "";
		insertInfoDetailSQL += "insert into order_detail (order_no, order_prod_no, order_quantity) ";
		insertInfoDetailSQL += "values (order_seq.currVal, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(insertInfoDetailSQL);

			for(OrderInfoDetail detail : infoDetail) {
				String prod_no = detail.getProduct().getProductNo();
				pstmt.setString(1, prod_no);
				
				int quantity = detail.getOrder_quantity();
				pstmt.setInt(2, quantity);
				
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<OrderInfo> selectById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		
		String selectByIdSQL = "";
		selectByIdSQL += "SELECT info.order_no, order_time, prod_no, prod_name, prod_price, order_quantity ";
		selectByIdSQL += "FROM order_info info JOIN order_detail detail ON info.order_no = detail.order_no JOIN product p ON p.prod_no = detail.order_prod_no ";
		selectByIdSQL += "WHERE order_id = ? ";
		selectByIdSQL += "ORDER BY order_time DESC, prod_no";
		
		try {
			Class.forName(DB_DRIVER);

			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			pstmt = conn.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			OrderInfo orderInfo = null;
			OrderInfoDetail orderInfoDetail = null;
			List<OrderInfoDetail> details = null;
			int oldOrderNo = -1;
			
			while (rs.next()) {
				int orderNo = rs.getInt("order_no");
				if (oldOrderNo != orderNo) {
					orderInfo = new OrderInfo();
					orderInfo.setOrder_no(orderNo);
					orderInfo.setOrder_time(rs.getDate("order_time"));
					list.add(orderInfo);
					
					details = new ArrayList<OrderInfoDetail>();
					orderInfo.setDetails(details);
					
					oldOrderNo = orderNo;
				}
				
				orderInfoDetail = new OrderInfoDetail();
				
				Product product = new Product();
				product.setProdectNo(rs.getString("prod_no"));
				product.setProductName(rs.getString("prod_name"));
				product.setProductPrice(rs.getInt("prod_price"));
				
				orderInfoDetail.setProduct(product);
				orderInfoDetail.setOrder_no(orderNo);
				orderInfoDetail.setOrder_quantity(rs.getInt("order_quantity"));
				
				details.add(orderInfoDetail);
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
}
