package com.twitter.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.twitter.models.TweetModel;
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
		// TODO Auto-generated method stub
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String pw = request.getParameter("password");
		String em = request.getParameter("email");

		UserModel um = new UserModel();
		um.setCluster(cluster);

		UserStore currUser = um.getUser(em, pw);

		if (currUser != null) {
			request.setAttribute("UserDetails", currUser); // Set a bean with
															// user in it
			RequestDispatcher rd = request
					.getRequestDispatcher("/newTweet.jsp");

			rd.forward(request, response);
		}

	}
}
