package com.twitter.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;
import com.twitter.lib.CassandraHosts;
import com.twitter.models.UserListModel;


/**
 * Servlet implementation class ListUsers
 */
@WebServlet({ "/ListUsers", "/ListUsers/*" })
public class ListUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUsers() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("Got 1");
		UserListModel ulm = new UserListModel();
		ulm.setCluster(cluster);
System.out.println("Got 2");	
		LinkedList<String> userList = ulm.getUsers();
System.out.println("Got 3");		
		request.setAttribute("Users", userList);
		RequestDispatcher rd = request
				.getRequestDispatcher("/UserList.jsp");
		rd.forward(request, response);	
	}


}
