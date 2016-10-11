/**
 * 
 */
package twitter.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import twitter.exception.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import twitter.dao.TwitterDao;
import twitter.model.UserTweets;

/**
 * @author praneeth puligundla
 *
 */
public class TwitterServiceTest {
	private static TwitterDao twitterDaoMock;
	private static String userId = "1200";
	private static String followerId = "1200";
	private static List<String> homeTweets;
	private static List<String> userTweet;
	private static TwitterService twitterservice;
	private static UriInfo uriInfo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		twitterDaoMock = mock(TwitterDao.class);
		uriInfo = mock(UriInfo.class);
		twitterservice = new TwitterService(twitterDaoMock);
		homeTweets = Arrays.asList("first Tweet", "Second Tweet", "Third Tweet");
		userTweet =  Arrays.asList("first Tweet");
		Set<String> followerSet = new HashSet<String>(Arrays.asList("1", "2", "5"));
		when(twitterDaoMock.getTweets(userId)).thenReturn(userTweet);
		when(twitterDaoMock.getHomeTweets(followerId,0,100)).thenReturn(homeTweets);
		when(twitterDaoMock.getFollowers(userId)).thenReturn(followerSet);
		when(uriInfo.getBaseUriBuilder()).thenReturn(UriBuilder.fromUri("http://localhost:8080/twitter/webapi/feed"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		twitterservice.deleteAllUserTweets(userId);
	}

	@Test
	public void testGetHomeTimeLineTweet() {
		Response response = twitterservice.getTimeLineTweets(userId, uriInfo);
		UserTweets usertweets = (UserTweets) response.getEntity();
		assertThat(usertweets.getUserTweets(), equalTo(homeTweets));
	}

	@Test(expected = DataNotFoundException.class)
	public void testGetTweetNoUser() {
		twitterservice.getTimeLineTweets(null, uriInfo);
	}

	@Test
	public void testGetUserTweets() {
		Response response = twitterservice.getUserTweets(userId);
		List<String> userTweet= (List<String>) response.getEntity();
		assertThat(userTweet, equalTo(userTweet));
	}
	
	@Test(expected = DataNotFoundException.class)
	public void testGetUserTweetsException() {
		Response response = twitterservice.getUserTweets(null);
		List<String> userTweet= (List<String>) response.getEntity();
		assertThat(userTweet, equalTo(userTweet));
	}
	

}
