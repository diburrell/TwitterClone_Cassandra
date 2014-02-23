package com.twitter.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Cluster;
import com.twitter.lib.CassandraHosts;
import com.twitter.models.ProfileModel;
import com.twitter.models.TweetListModel;
import com.twitter.stores.TweetStore;
import com.twitter.stores.UserStore;

/**
 * Servlet implementation class Profiles
 */
@WebServlet({"/ProfileGetter","/ProfileGetter/*"})
public class ProfileGetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Cluster cluster;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileGetter() {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession s = request.getSession();
		
		
		TweetListModel tm= new TweetListModel();
		ProfileModel pm = new ProfileModel();
		
		tm.setCluster(cluster);
		pm.setCluster(cluster);
		
		String name = request.getRequestURI();
		name = name.substring(name.lastIndexOf("/")+1);
				
		UserStore profUser = pm.getUser(name);
		s.setAttribute("ProfileOf", profUser);
		
		Set<UUID> profID = new HashSet<UUID>();
		profID.add(profUser.getID());
		
		LinkedList<TweetStore> tweetList = tm.getTweets(profID);						
		
		request.setAttribute("ProfileTweets", tweetList); //Set a bean with the list in it
		request.setAttribute("ProfileOf", profUser); //Set a bean with the list in it
		
		RequestDispatcher rd = request.getRequestDispatcher("/Profiles.jsp"); 
		
		rd.forward(request, response);
	}

}
