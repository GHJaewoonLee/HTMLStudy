package com.kitri.control;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String fileName = request.getParameter("filename");
		PrintWriter out = response.getWriter();
		
		String gurupath = "d:\\javadata\\";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""	+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
 
		FileInputStream fileInputStream = new FileInputStream(gurupath + fileName);
 
		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}
}
