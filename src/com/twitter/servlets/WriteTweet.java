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
import com.twitter.lib.*;
import com.twitter.models.WriteModel;
import com.twitter.stores.TweetStore;
import com.twitter.servlets.*;

/**
 * Servlet implementation class dataWrite
 */
@WebServlet("/WriteTweet")
public class WriteTweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteTweet() {
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
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		WriteModel wm = new WriteModel();
		wm.setCluster(cluster);
		
		TweetStore newTweet = new TweetStore();
		
		String currName= (String)request.getSession().getAttribute("name");
			
		newTweet.setUser(currName);
System.out.println("NAME IS...." + newTweet.getUser());
		newTweet.setTweet(request.getParameter("tweet"));
			
		wm.storeTweet(newTweet);

	}


}

