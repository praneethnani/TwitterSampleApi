package twitter.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import twitter.exception.RedisException;


public class TwitterDao {
	
	private String ownTweets = "ownTweets";
	private String homeTweets = "homeTweets";
	
	private static Jedis jedis = null;

	public Jedis getJedis() {
		if (jedis==null){
			jedis = new Jedis("localhost");
		}
		return jedis;
	}
	
	public  List<String> getHomeTweets(String userId,int start, int end){
		try (Jedis jedis = getJedis()) {
			return getTweetData(jedis.lrange(userId+homeTweets, start, end));
		}catch(Exception e){
			throw new RedisException("Redis database Exception");
		}
	}
	
	private List<String> getTweetData(List<String> tweetIds){
		 List<String> tweets = new LinkedList<String>();
		 tweetIds.forEach(tweetId->tweets.add(jedis.get(tweetId)));
		 return tweets;
	}
	
	public  List<String> getTweets(String userId){	
		try (Jedis jedis = getJedis()) {
			 return getTweetData(jedis.lrange(userId+ownTweets, 0, -1));
		}catch(Exception e){
			throw new RedisException("Redis database Exception");
		}
	}
	
	public  void addTweet(String tweetId,String tweet, String userId){	
		try (Jedis jedis = getJedis()) {
			jedis.set(tweetId, tweet);
			jedis.lpush(userId+ownTweets, tweetId);
		}catch(Exception e){
			throw new RedisException("Redis database Exception");
		}
	}

	public void postTweet(String followerId, String tweetId){
		try (Jedis jedis = getJedis()) {
			followerId=followerId+homeTweets;
			List<String> tweets = jedis.mget(followerId);
			if (tweets.isEmpty()) {
				jedis.lpush(followerId, tweetId);
				jedis.ltrim(followerId, 0, 800);
			} else {
				jedis.lpush(followerId, tweetId);
			}
		}catch(Exception e){
			throw new RedisException("Redis database Exception");
		}
	}
	
	public Set<String> getFollowers(String userId){
		try (Jedis jedis = getJedis()) {
			return jedis.smembers(userId);
		}catch(Exception e){
			throw new RedisException("Redis database Exception");
		}
	}
	
	public void addFollower(String userId,String followerId){
		try (Jedis jedis = getJedis()) {
			jedis.sadd(userId,followerId);
		}catch(Exception e){
			throw new RedisException("Redis database Exception");
		}
	}
	
	public void deleteUserContents(String userId){
		try (Jedis jedis = getJedis()) {
			jedis.del(userId);
			jedis.del(userId+ownTweets);
			jedis.del(userId+homeTweets);
		}catch(Exception e){
			throw new RedisException("Redis database Exception");
		}
	}
	
	
}
