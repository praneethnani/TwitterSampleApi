package twitter.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserTweet {
	
    private String userId;
    private String tweet;
    
    public UserTweet(){
    	
    }
    
	public UserTweet(String userId, String tweet) {
		super();
		this.userId = userId;
		this.tweet = tweet;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserTweet other = (UserTweet) obj;
		if (tweet == null) {
			if (other.tweet != null)
				return false;
		} else if (!tweet.equals(other.tweet))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserTweet [userId=" + userId + ", tweet=" + tweet + "]";
	}
    
}
