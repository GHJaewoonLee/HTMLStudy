package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.Customer;
import com.kitri.exception.NotFoundException;


public class CustomerDAO {

	public static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String DB_URL = "jdbc:oracle:thin:@192.168.14.14:1521:orcl";
	public static final String DB_USERNAME = "kitri";
	public static final String DB_PASSWORD = "kitri";
	
	public Customer selectByID(String id) throws com.kitri.exception.NotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Customer customer = null;
		
		try {
			// Load JDBC driver
			Class.forName(DB_DRIVER);

			// Connect to DB
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			// SQL statement
			String selectByIdSQL = "select * from customer where id = ?";
			pstmt = conn.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				customer = new Customer();
				
				customer.setId(id);
				customer.setName(rs.getString("name"));
				customer.setPass(rs.getString("pass"));
				customer.setAddress(rs.getString("address"));
			} else {
				throw new NotFoundException("No specific customer.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
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
				throw new NotFoundException(e.getMessage());
			}
		}
		
		return customer;
	}
	
	public List<Customer> selectByName(String name) throws com.kitri.exception.NotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Customer> list = new ArrayList<Customer>();
		
		try {
			// Load JDBC driver
			Class.forName(DB_DRIVER);

			// Connect to DB
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			// SQL statement
			String selectByNameSQL = "select * from customer where name = ?";
			pstmt = conn.prepareStatement(selectByNameSQL);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				
				customer.setId(rs.getString("id"));
				customer.setName(rs.getString("name"));
				customer.setPass(rs.getString("pass"));
				customer.setAddress(rs.getString("address"));
				
				list.add(customer);	
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
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
				throw new NotFoundException(e.getMessage());
			}
		}
		
		return list;
	}
	
	public List<Customer> selectAll() throws com.kitri.exception.NotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Customer> list = new ArrayList<Customer>();
		
		try {
			// Load JDBC driver
			Class.forName(DB_DRIVER);

			// Connect to DB
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			// SQL statement
			String selectAllSQL = "select * from customer";
			pstmt = conn.prepareStatement(selectAllSQL);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				
				customer.setId(rs.getString("id"));
				customer.setName(rs.getString("name"));
				customer.setPass(rs.getString("pass"));
				customer.setAddress(rs.getString("address"));
				
				list.add(customer);	
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
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
				throw new NotFoundException(e.getMessage());
			}
		}
		
		return list;
	}
	
	public void insert(Customer customer) throws com.kitri.exception.NotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			// Load JDBC driver
			Class.forName(DB_DRIVER);

			// Connect to DB
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			// SQL statement
			String insertCustomerSQL = "insert into customer (id, name, pass, address) values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(insertCustomerSQL);
			
			int idx = 1;
			pstmt.setString(idx++, customer.getId());
			pstmt.setString(idx++, customer.getName());
			pstmt.setString(idx++, customer.getPass());
			pstmt.setString(idx++, customer.getAddress());
			
			result = pstmt.executeUpdate();
			
			if (result == 0) {
				throw new NotFoundException("Insert failed.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
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
				throw new NotFoundException(e.getMessage());
			}
		}
	}
}
