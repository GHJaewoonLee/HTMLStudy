package com.kitri.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MultipartRequest mr = new MultipartRequest(request, "D:\\javadata");
		//MultipartRequest mr = new MultipartRequest(request, "D:\\javadata", 100 * 1024);
		MultipartRequest mr = new MultipartRequest(request, "D:\\javadata", 100 * 1024, "UTF-8", new DefaultFileRenamePolicy());
		String a = mr.getParameter("a");
		File f1 = mr.getFile("f1");
		
		//System.out.println("a=" + a);
		//System.out.println("fileName=" + f1.getName());
		
		String path = "/uploadresult.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
