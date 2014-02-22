package com.twitter.servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Cluster;
import com.twitter.lib.*;
import com.twitter.models.*;
import com.twitter.stores.*;

/**
 * Servlet implementation class loginCheck
 */
@WebServlet("/loginCheck")
public class loginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {	
		cluster = CassandraHosts.getCluster();
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession s = request.getSession();
		
		s.setAttribute("wrongLogin", false);
		s.setAttribute("badDetails", false);
		
		String pw = request.getParameter("password");
		String em = request.getParameter("email");

		if (pw.equals("") || em.equals("")) {
						
			s.setAttribute("wrongLogin", true);		
			response.sendRedirect("");
		}

		else {
			UserModel um = new UserModel();
			um.setCluster(cluster);

			
			try
			{
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
			    pw = PasswordHasher.byteArrayToString(digest.digest(pw.getBytes("UTF-8")));
				
			}
			catch (NoSuchAlgorithmException e)
			{ }
			UserStore currUser = um.getUser(em, pw);

			if (currUser != null) {
				s.setAttribute("wrongLogin", false);
				s.setAttribute("UserDetails", currUser); // Set a bean with //
															// user in it
				response.sendRedirect("TweetGetter");
			} else {
				
				s.setAttribute("wrongLogin", true);
				response.sendRedirect("");
			}
		}
	}
}
