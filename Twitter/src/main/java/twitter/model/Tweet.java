package twitter.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tweet {

	private String tweetId;
	private String tweet;

	public Tweet(){
		
	}
	
	public Tweet(String tweetId, String tweet) {
		super();
		this.tweetId = tweetId;
		this.tweet = tweet;
	}

	public String getTweetId() {
		return tweetId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tweet == null) ? 0 : tweet.hashCode());
		result = prime * result + ((tweetId == null) ? 0 : tweetId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (tweet == null) {
			if (other.tweet != null)
				return false;
		} else if (!tweet.equals(other.tweet))
			return false;
		if (tweetId == null) {
			if (other.tweetId != null)
				return false;
		} else if (!tweetId.equals(other.tweetId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tweet [tweetId=" + tweetId + ", tweet=" + tweet + "]";
	}


}
