package com.twitter.servlets;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;
import com.twitter.lib.CassandraHosts;
import com.twitter.models.TweetModel;
import com.twitter.stores.TweetStore;

/**
 * Servlet implementation class SingleTweet
 */
@WebServlet({"/SingleTweet","/SingleTweet/*"})
public class SingleTweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SingleTweet() {
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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String tweet = request.getRequestURI();
		tweet = tweet.substring(tweet.lastIndexOf("/") + 1);

		UUID tweetID = UUID.fromString(tweet);

		TweetModel tm = new TweetModel();
		tm.setCluster(cluster);

		TweetStore tweetDeets = tm.getTweet(tweetID);

		request.setAttribute("TweetID", tweet);
		request.setAttribute("Tweet", tweetDeets); // Set a bean with the
		// list in it

		RequestDispatcher rd = request.getRequestDispatcher("/SingleTweet.jsp");

		rd.forward(request, response);

	}

}
