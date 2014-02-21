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
import com.twitter.models.DeleteTweetModel;
import com.twitter.models.ProfileModel;
import com.twitter.models.TweetListModel;
import com.twitter.stores.TweetStore;
import com.twitter.stores.UserStore;

/**
 * Servlet implementation class DeleteTweet
 */
@WebServlet({"/DeleteTweet","/DeleteTweet/*"})
public class DeleteTweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Cluster cluster;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTweet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tweet = request.getRequestURI();
		tweet = tweet.substring(tweet.lastIndexOf("/")+1);
					
		UUID tweetID = UUID.fromString(tweet);
		
		UserStore currUser = ((UserStore) request.getSession().getAttribute(
				"UserDetails"));
		
		UUID userID = currUser.getID();
		
		DeleteTweetModel dtm = new DeleteTweetModel();
		
		dtm.setCluster(cluster);
		dtm.removeTweet(tweetID, userID);
		
		response.sendRedirect("/TwitterClone_Cassandra/TweetGetter");
	}
}
