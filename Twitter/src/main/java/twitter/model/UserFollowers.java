package twitter.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserFollowers {

	private String userId;
	private List <String> followerIds;
	
	public UserFollowers(){
		
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List<String> getFollowerIds() {
		return followerIds;
	}
	
	public void addFollower(String followerId) {
		this.followerIds.add(followerId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((followerIds == null) ? 0 : followerIds.hashCode());
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
		UserFollowers other = (UserFollowers) obj;
		if (followerIds == null) {
			if (other.followerIds != null)
				return false;
		} else if (!followerIds.equals(other.followerIds))
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
		return "UserFollowers [userId=" + userId + ", followerIds=" + followerIds + "]";
	}
	
}
