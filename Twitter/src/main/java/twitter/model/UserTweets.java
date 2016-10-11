package twitter.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UserTweets {

	private String userId;
	private List<String> userTweets;
	private List<Link> links = new ArrayList<>();

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public UserTweets() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getUserTweets() {
		return userTweets;
	}

	public void setUserTweets(List<String> userTweets) {
		this.userTweets = userTweets;
	}

	public UserTweets(String userId, List<String> userTweets) {
		super();
		this.userId = userId;
		this.userTweets = userTweets;
	}

	public void addLink(String url, String rel) {
		Link link = new Link(url, rel);
		links.add(link);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userTweets == null) ? 0 : userTweets.hashCode());
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
		UserTweets other = (UserTweets) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userTweets == null) {
			if (other.userTweets != null)
				return false;
		} else if (!userTweets.equals(other.userTweets))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserTweets [userId=" + userId + ", userTweets=" + userTweets + "]";
	}

}
