package com.kitri.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.dto.Customer;
import com.kitri.dto.OrderInfo;
import com.kitri.dto.OrderInfoDetail;
import com.kitri.dto.Product;
import com.kitri.service.OrderService;


@WebServlet("/addOrder")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderService service;
	
	public AddOrderServlet() {
		service = new OrderService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginInfo");
		
		Customer customer = new Customer();
		customer.setId(id);
		
		OrderInfo info = new OrderInfo();
		info.setCustomer(customer);
		
		List<OrderInfoDetail> details = new ArrayList<OrderInfoDetail>();
		@SuppressWarnings("unchecked")
		Map<Product, Integer> map = (Map<Product, Integer>) session.getAttribute("cart");
		for (Product product : map.keySet()) {
			int quantity = (Integer) map.get(product);
			OrderInfoDetail infoDetail = new OrderInfoDetail();
			
			infoDetail.setProduct(product);
			infoDetail.setOrder_quantity(quantity);
			
			details.add(infoDetail);
		}
		
		info.setDetails(details);
		service.addOrder(info);
		
		session.removeAttribute("cart");
		
		String path = "/addOrderResult.jsp";
		request.setAttribute("result", "1");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}