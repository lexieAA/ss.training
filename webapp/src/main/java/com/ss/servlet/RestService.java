package com.ss.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ss.dto.Login;
import com.ss.dto.User;

/**
 * Servlet implementation class RestService
 */
@WebServlet({ "/user", "/user/", "/user/id/*" })
public class RestService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		
		Gson gson = new Gson();
		
		List<User> users = new ArrayList<User>();
		
		users.add(new User(1, "Brat", "Pitt"));
		users.add(new User(2, "Al", "Pacino"));
		users.add(new User(3, "Al", "Pacino"));
		users.add(new User(4, "Natalie", "Portman"));
		
		
		if("/user".equals(path) || "/user/".equals(path)) {
			response.setContentType("application/json");
			     
			PrintWriter out = response.getWriter();
			  
			out.print(gson.toJson(users));
			out.flush();
		}
		
		if(path.contains("/user/id")) {
			String pathInfo = request.getPathInfo();
			if(pathInfo == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			else {
				try {
					Integer id = Integer.parseInt(pathInfo.replaceAll("/", ""));
					
					response.setContentType("application/json");
				     
					PrintWriter out = response.getWriter();
					  
					out.print(gson.toJson( users.stream()
												.filter( user -> user.getId() == id )
												.collect(Collectors.toList()) )
							);
					
					out.flush();
				}
				catch(Exception e) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		boolean loginResult = false;
		List<Login> usersLogin = new ArrayList<Login>();
		
		usersLogin.add(new Login("Brat", "password"));
		usersLogin.add(new Login("Alex", "password123"));
		usersLogin.add(new Login("Al", "123password"));
		usersLogin.add(new Login("Natalie", "1234password"));
		
		if(path.contains("/user")) {
			
		
			BufferedReader reader = request.getReader();
			Gson gson = new Gson();
			Login login = gson.fromJson(reader, Login.class);
					
			for (Login valid : usersLogin) {
	            if (valid.getUserName().equals(login.getUserName()) && valid.getPassword().equals(login.getPassword()) ) {
	            	loginResult = true;
	            }
	        }
			
			if(loginResult == true) {
				response.setStatus(HttpServletResponse.SC_OK);
			}else{
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
	}

}
