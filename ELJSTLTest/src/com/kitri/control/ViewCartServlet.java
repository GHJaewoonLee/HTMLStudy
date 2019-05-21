package com.kitri.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.dto.Product;
import com.kitri.service.ProductService;


@WebServlet("/viewCart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService service;
	
	@Override
	public void init() throws ServletException {
		service = new ProductService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		Map<Product, Integer> map = (Map<Product, Integer>) session.getAttribute("cart");
		Map<Product, Integer> requestMap = new HashMap<Product, Integer>();
		
		if (map != null) {
			Set<Product> set = map.keySet();
			
			for (Product product : set) {
				String no = product.getProductNo();
				try {
					Product p = service.findByNo(no);
					int quantity = map.get(p);
					
					requestMap.put(p, quantity);
				} catch (Exception e) {
					
				}
			}
			
			request.setAttribute("requestCart", requestMap);

			String path = "/viewCartResult.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}