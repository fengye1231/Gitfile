package com.project.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class Mylistener implements HttpSessionAttributeListener,HttpSessionListener,ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		arg0.getServletContext().setAttribute("num", 0);
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		String name = arg0.getName();
		if("user".equals(name)) {
			int num = (Integer)arg0.getSession().getServletContext().getAttribute("num");
			num++;
			arg0.getSession().getServletContext().setAttribute("num", num);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		int num = (Integer)arg0.getSession().getServletContext().getAttribute("num");
		num--;
		arg0.getSession().getServletContext().setAttribute("num", num);
	}

	

}
