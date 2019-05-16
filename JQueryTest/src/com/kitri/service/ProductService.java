package com.kitri.service;

import java.util.List;

import com.kitri.dao.ProductDAO;
import com.kitri.dto.Product;


public class ProductService {

	private ProductDAO productDAO;
	
	public ProductService() {
		productDAO = new ProductDAO();
	}
	
	public List<Product> findAll() {
		return productDAO.selectAll();
	}

	public Product findByNo(String no) {
		return productDAO.SelectByNo(no);
	}
}
