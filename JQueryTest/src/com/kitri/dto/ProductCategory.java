package com.kitri.dto;


public class ProductCategory {

	private String categoryNo;
	private String categoryName;
	
	
	
	public ProductCategory() {
		
	}

	public ProductCategory(String categoryNo, String categoryName) {
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
	}

	
	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "ProductCategory [categoryNo=" + categoryNo + ", categoryName=" + categoryName + "]";
	}
}
