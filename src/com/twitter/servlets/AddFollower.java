package com.twitter.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Cluster;
import com.twitter.lib.CassandraHosts;
import com.twitter.models.AddFollowerModel;
import com.twitter.stores.UserStore;

/**
 * Servlet implementation class AddFollower
 */
@WebServlet("/AddFollower")
public class AddFollower extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFollower() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession s = request.getSession();
		
		UserStore ProfileOf = (UserStore)request.getSession().getAttribute("ProfileOf");

		UserStore currUser = ((UserStore) request.getSession().getAttribute(
				"UserDetails"));

		AddFollowerModel afm = new AddFollowerModel();
		afm.setCluster(cluster);
		currUser.setFollowing(afm.newFollower(currUser, ProfileOf.getID()));
		
		s.setAttribute("UserDetails", currUser);
		
		response.sendRedirect("ProfileGetter/"+ProfileOf.getName());
	}

}
