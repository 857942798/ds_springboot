package com.ds.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "userServlet",urlPatterns = "/v1/api/test/customs")
public class UserServlet extends HttpServlet{
	@Override
	public void destroy() {
		System.out.println("=====servlet destroy=====");
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		System.out.println("======servlet init======");
		super.init();
	}

	@Override
     public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 System.out.println("======servlet doGet=======");
		 resp.getWriter().print("custom sevlet");
         resp.getWriter().flush();
         resp.getWriter().close();
     }

	 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("=======servlet doPost======");
		this.doGet(req, resp);
	}

	 
    
}
