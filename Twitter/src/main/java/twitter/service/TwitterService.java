package twitter.service;

import java.rmi.server.UID;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import twitter.dao.TwitterDao;
import twitter.exception.DataNotFoundException;
import twitter.model.Follower;
import twitter.model.UserTweet;
import twitter.model.UserTweets;
import twitter.resource.TwitterResource;

/**
 * @author praneeth puligundla
 */

public class TwitterService {

	TwitterDao twitterDao;

	public TwitterService() {
		twitterDao = new TwitterDao();
	}

	public TwitterService(TwitterDao twitterDao) {
		this.twitterDao = twitterDao;
	}
	
	/**
	 * Gets the 100 recent time line tweets for the given userId.
	 * @param userId identifies the user whose tweets are being queried.
	 * @param uriInfo provides request URI information (provided by jax rs).
	 * @return response - with retrieved tweets as entity and 200 as status code 
	 * 					  if successfully retrieved.
	 */
	public Response getTimeLineTweets(String userId, UriInfo uriInfo) {
		List<String> tweets = twitterDao.getHomeTweets(userId,0,100);
		if (tweets.isEmpty()) {
			throw new DataNotFoundException("No TimeLine Tweets for given user");
		}
		UserTweets userTweets = new UserTweets(userId, tweets);
		String uri = uriInfo.getBaseUriBuilder().path(TwitterResource.class).path("/feed").build().toString();
		userTweets.addLink(uri, "self");
		return Response.status(Status.OK).entity(userTweets).build();
	}
	
	/**
	 * Gets tweets for the given userId.
	 * @param userId identifies the user whose tweets are being queried.
	 * @return response - with retrieved tweets as entity and 200 as status code 
	 * 					  if successfully retrieved.
	 */

	public Response getUserTweets(String userId) {
		List<String> tweets = twitterDao.getTweets(userId);
		if (tweets.isEmpty()) {
			throw new DataNotFoundException("No Tweets for given user");
		}
		return Response.status(Status.OK).entity(tweets).build();
	}

	/**
	 * Posts the given tweet to followers.
	 * @param userTweet that is to be posted to followers.
	 * @return response - with the posted tweet as entity and 201 as status code
	 * 					  if successfully posted.
	 */
	public Response postTweetToFollowers(UserTweet userTweet) {
		String tweetId = addUserTweetToDB(userTweet);
		Set<String> followerSet = twitterDao.getFollowers(userTweet.getUserId());
		for (String followerId : followerSet) {
			twitterDao.postTweet(followerId, tweetId);
		}
		return Response.status(Status.CREATED).entity(userTweet).build();
	}

	
	/**
	 * Adds a given userTweet to the database along with the tweetId created for it.
	 * @param userTweet that is going to be added to be the database.
	 * @return the tweetId created for the given tweet.
	 */
	private String addUserTweetToDB(UserTweet userTweet) {
		String tweetId = getTweetId().toString();
		twitterDao.addTweet(tweetId, userTweet.getTweet(), userTweet.getUserId());
		return tweetId;
	}

	/**
	 * Creates a tweetId in the format of UID.
	 * @return the created tweetId.
	 */
	private UID getTweetId() {
		UID tweetId = new UID();
		return tweetId;
	}

	/**
	 * Adds the given follower.
	 * @param follower that needs to be added.
	 * @return Response - with the added follower as entity and 201 as status code
	 * 					  if successfully added.
	 */
	public Response addUserFollower(Follower follower) {
		twitterDao.addFollower(follower.getUserId(), follower.getFollowerId());
		return Response.status(Status.CREATED).entity(follower).build();
	}

	/**
	 * Deletes all tweets for a given userId.
	 * @param userId of the user whose tweets are to be deleted.
	 */
	public void deleteAllUserTweets(String userId) {
		twitterDao.deleteUserContents(userId);
	}

}
