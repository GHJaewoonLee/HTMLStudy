package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.Product;
import com.kitri.dto.ProductCategory;


public class ProductDAO {

	public static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String DB_URL = "jdbc:oracle:thin:@192.168.14.14:1521:orcl";
	public static final String DB_USERNAME = "kitri";
	public static final String DB_PASSWORD = "kitri";
	
	
	public List<Product> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Product> list = new ArrayList<Product>();
		
		try {
			// Load JDBC driver
			Class.forName(DB_DRIVER);

			// Connect to DB
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			String selectByAllSQL = "";
			selectByAllSQL += "SELECT category_no, category_name, prod_no, prod_name, prod_price, prod_detail ";
			selectByAllSQL += "FROM product p JOIN product_category pc ON p.prod_category = pc.category_no ";
			selectByAllSQL += "ORDER BY category_no, prod_name";
			pstmt = conn.prepareStatement(selectByAllSQL);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Product product = new Product();
				product.setProdectNo(rs.getString("prod_no"));
				product.setProductName(rs.getString("prod_name"));
				product.setProductPrice(rs.getInt("prod_price"));
				product.setProductDetail(rs.getString("prod_detail"));
				
				ProductCategory productCategory = new ProductCategory();
				productCategory.setCategoryNo(rs.getString("category_no"));
				productCategory.setCategoryName(rs.getString("category_name"));
				
				product.setProductCategory(productCategory);
				
				list.add(product);
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


	public Product SelectByNo(String no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Product product = null;
		
		try {
			// Load JDBC driver
			Class.forName(DB_DRIVER);

			// Connect to DB
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			String selectByNoSQL = "";
			selectByNoSQL += "SELECT category_no, category_name, prod_no, prod_name, prod_price, prod_detail ";
			selectByNoSQL += "FROM product p JOIN product_category pc ON p.prod_category = pc.category_no ";
			selectByNoSQL += "WHERE prod_no = ?";
			pstmt = conn.prepareStatement(selectByNoSQL);
			pstmt.setString(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				product = new Product();
				product.setProdectNo(rs.getString("prod_no"));
				product.setProductName(rs.getString("prod_name"));
				product.setProductPrice(rs.getInt("prod_price"));
				product.setProductDetail(rs.getString("prod_detail"));
				
				ProductCategory productCategory = new ProductCategory();
				productCategory.setCategoryNo(rs.getString("category_no"));
				productCategory.setCategoryName(rs.getString("category_name"));
				
				product.setProductCategory(productCategory);
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
		
		return product;
	}
}
