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
import com.twitter.lib.CassandraHosts;
import com.twitter.lib.PasswordHasher;
import com.twitter.models.NewUserModel;
import com.twitter.models.UserModel;
import com.twitter.stores.UserStore;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		cluster = CassandraHosts.getCluster();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession s = request.getSession();

		s.setAttribute("badDetails", false);
		s.setAttribute("wrongLogin", false);
		
		String pw = request.getParameter("newPassword");
		String em = request.getParameter("newEmail");
		String un = request.getParameter("newName");

		if (pw.equals("") || em.equals("") || un.equals("")) {
			
			s.setAttribute("badDetails", true);
			response.sendRedirect("SignUpForm.jsp");

		} else {
			UserStore newUser = new UserStore();

			newUser.setEmail(em);
			newUser.setName(un);

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
						
			if (currUser == null) {
				NewUserModel newUM = new NewUserModel();
				newUM.setCluster(cluster);

				currUser = newUM.storeUser(newUser, pw);
				s.setAttribute("wrongLogin", false);
				s.setAttribute("UserDetails", currUser); // Set a bean with
														 // user in it
				response.sendRedirect("MoreDetails.jsp");

			} else {
				s.setAttribute("badDetails", true);
				response.sendRedirect("SignUpForm.jsp");
			}
		}
	}

}
