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
import com.twitter.lib.*;
import com.twitter.models.*;
import com.twitter.stores.*;

/**
 * Servlet implementation class Tweet
 */
@WebServlet("/TweetGetter")
public class TweetGetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TweetGetter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TweetListModel tm = new TweetListModel();
		tm.setCluster(cluster);

		String s = request.getRequestURI();
		s = s.substring(s.lastIndexOf("/") + 1);

		UserStore currUser = ((UserStore) request.getSession().getAttribute(
				"UserDetails"));
		
			LinkedList<TweetStore> tweetList = tm.getTweets(currUser
					.getFollowing());

			request.setAttribute("Tweets", tweetList); // Set a bean with the
														// list in it

			RequestDispatcher rd = request
					.getRequestDispatcher("/TweetFeed.jsp");

			rd.forward(request, response);

		;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
