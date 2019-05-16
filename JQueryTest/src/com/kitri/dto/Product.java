package com.kitri.dto;


public class Product {

	private String productNo;
	private String productName;
	private int productPrice;
	private String productDetail;
	private ProductCategory productCategory;
	
	
	public Product() {
		
	}

	public Product(String productNo, String productName, int productPrice, String productDetail, ProductCategory productCategory) {
		this.productNo = productNo;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDetail = productDetail;
		this.productCategory = productCategory;
	}

	
	public String getProductNo() {
		return productNo;
	}

	public void setProdectNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productDetail=" + productDetail + ", productCategory=" + productCategory.toString() + "]";
	}
}
