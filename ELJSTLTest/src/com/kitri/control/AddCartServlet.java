package com.kitri.control;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.dto.Product;


@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String quantity = request.getParameter("quantity");

		HttpSession session = request.getSession();

		request.setAttribute("no", no);
		request.setAttribute("quantity", quantity);
		
		// push 하기 전에 Key의 hashCode() && equals() 값을 판단  (같으면 새로운 값으로 덮어씀)
		@SuppressWarnings("unchecked")
		Map<Product, Integer> map = (Map<Product, Integer>) session.getAttribute("cart");
		if (map == null) {
			map = new HashMap<Product, Integer>();
			session.setAttribute("cart", map);
		}
		
		Product product = new Product();
		product.setProdectNo(no);
		
		int intQuantity = Integer.parseInt(quantity);
		
		Integer integerQuantity = map.get(product);
		if (integerQuantity != null) {
			intQuantity += integerQuantity.intValue();
		}
		
		map.put(product, intQuantity);
		
//		System.out.println("------------------");
//		Set<Product> keys = map.keySet();
//		for (Product p : keys) {
//			System.out.println(p.getProductNo() + " " + map.get(p));
//		}
		
		String path = "/addCartResult.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}