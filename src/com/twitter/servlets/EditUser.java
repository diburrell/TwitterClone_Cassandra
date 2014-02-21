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
import com.twitter.models.EditUserModel;
import com.twitter.stores.UserStore;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserStore currUser = ((UserStore) request.getSession().getAttribute(
				"UserDetails"));
			
		  String sex = request.getParameter("sex");
		  String fname = request.getParameter("FirstName");  
		  String lname = request.getParameter("LastName");
		  String bio = request.getParameter("bio");

		  currUser.setSex(sex);
		  currUser.setFirst(fname);
		  currUser.setLast(lname);
		  currUser.setBio(bio);
		  
		  EditUserModel eum = new EditUserModel();

		  eum.setCluster(cluster);
		  
		  eum.editUser(currUser);
		  
		  HttpSession s = request.getSession();
		  s.setAttribute("UserDetails", currUser);
		  
UserStore testUser = (UserStore)s.getAttribute("UserDetails");		  		  
System.out.println(testUser.getEmail());

		  response.sendRedirect("ChangesConf.jsp");
		  
	}

}
