package com.kitri.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


@WebListener
public class MySessionAttributeListener implements HttpSessionAttributeListener {

	private int loginCnt;
	
    public MySessionAttributeListener() {

    }

    
    public void attributeAdded(HttpSessionBindingEvent e)  { 
    	String attrName = e.getName();
    	if (attrName.equals("loginInfo")) {
    		loginCnt++;
    		System.out.println(e.getValue() + " now login");
    		System.out.println("Login users : " + loginCnt);
    	}
    }

    public void attributeRemoved(HttpSessionBindingEvent e)  { 
    	String attrName = e.getName();
    	if (attrName.equals("loginInfo")) {
    		loginCnt--;
    		System.out.println(e.getValue() + " now logout");
    		System.out.println("Login users : " + loginCnt);
    	}
    }

    public void attributeReplaced(HttpSessionBindingEvent e)  { 
    	
    }
}
