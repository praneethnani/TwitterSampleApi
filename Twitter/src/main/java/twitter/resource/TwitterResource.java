package twitter.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import twitter.model.Follower;
import twitter.model.UserTweet;
import twitter.service.TwitterService;

/**
 * @author praneeth puligundla
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterResource {

	TwitterService twitterService = new TwitterService();

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "application/json" media type.
	 *
	 * @param userId
	 *            - UserId of the user who's tweets have to be retrieved
	 * @param uriInfo
	 *            - URI information from the context
	 * @return Response - with retrieved tweets as entity and status as 'Ok' if
	 *         successfully retrieved.
	 */
	@GET
	@Path("/feed")
	public Response getFeed(@HeaderParam("userId") String userId, @Context UriInfo uriInfo) {
		return twitterService.getTimeLineTweets(userId, uriInfo);
	}

	/**
	 * Method handling HTTP Post requests. The returned object will be sent to
	 * the client as "application/json" media type. Add's tweet to given user
	 * and posts to followers of the user
	 * 
	 * @param userTweet
	 *            - Has userid and tweet information to add tweet
	 * @return response - with retrieved tweets as entity and status as
	 *         'Created' if successfully created.
	 */
	@POST
	@Path("/addTweet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTweet(final UserTweet userTweet) {
		return twitterService.postTweetToFollowers(userTweet);
	}

	/**
	 * Method handling HTTP Post requests. The returned object will be sent to
	 * the client as "application/json" media type.
	 * 
	 * Add's follower to the given user
	 * 
	 * @param follower
	 *            - Has followerId to be added to userId
	 * @return response - with retrieved tweets as entity and status as
	 *         'Created' if successfully created.
	 */
	@POST
	@Path("/addFollower")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addFollower(final Follower follower) {
		return twitterService.addUserFollower(follower);
	}

}
