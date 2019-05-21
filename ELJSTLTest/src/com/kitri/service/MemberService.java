package com.kitri.service;

import com.kitri.dao.CustomerDAO;
import com.kitri.dto.Customer;
import com.kitri.exception.NotFoundException;

public class MemberService {
	
	public String login(String id, String pass) {
		Customer customer = null;
		
		try {
			customer = new CustomerDAO().selectByID(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		if (pass.equals(customer.getPass())) {
//			return "Login Success";
			return "1";
		} else {
//			return "Login Fail";
			return "-1";
		}
	}
}
