package com.kitri.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// WAS : 소켓/쓰레드 관리를 해주고 있음
// 객체는 1개 이지만 client마다 쓰레드가 1개씩 생성

// 생성자 -> init -> do...   클라이언트 요청에 따라 반복 -> destroy

@SuppressWarnings("serial")
@WebServlet("/life")
public class LifeCycleTest extends HttpServlet {
	
	public LifeCycleTest() {
		System.out.println("생성자 메소드 호출");
	}

	// Servlet은 init() 함수에서 객체를 초기화
	@Override
	public void init() throws ServletException {
		System.out.println("init 메소드 호출");
	}

	// 주로 서비스 영역 부분
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service 메소드 호출");
	}

	// 서비스가 종료되는 시점에서 호출
	@Override
	public void destroy() {
		System.out.println("destroy 메소드 호출");
	}
}
