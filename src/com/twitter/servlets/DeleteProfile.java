package com.twitter.servlets;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Cluster;
import com.twitter.lib.CassandraHosts;
import com.twitter.models.DeleteUserModel;
/**
 * Servlet implementation class DeleteTweet
 */
@WebServlet({"/DeleteProfile","/DeleteProfile/*"})
public class DeleteProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Cluster cluster;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProfile() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user = request.getRequestURI();
		user = user.substring(user.lastIndexOf("/")+1);
		System.out.println("UUID: "+user);			
		UUID userID = UUID.fromString(user);
			
		
		DeleteUserModel dum = new DeleteUserModel();
		
		dum.setCluster(cluster);
		dum.removeUser(userID);
		
		//response.sendRedirect("/TwitterClone_Cassandra/TweetGetter");

		HttpSession s = request.getSession();
		s.invalidate();
	
		response.sendRedirect("/TwitterClone_Cassandra/ProfileDeleted.jsp");
	}
}
