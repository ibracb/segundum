package segundum.infrastructure.rest.user.responses;

import java.util.List;

import segundum.application.readmodels.user.UserInfoReadModel;

/**
 * Represents the response for a list of user info.
 */
public class UserListResponse {
	
	/**
	 * Represents a resource containing a user info and its associated URL.
	 */
	public static class UserResource {
		
		/**
		 * The URL associated with the user info.
		 */
		private String url;
		
		/**
		 * The user info data.
		 */
		private UserInfoReadModel info;
		
		/**
		 * Returns the URL associated with the user info.
		 *
		 * @return the URL
		 */
		public String getUrl() {
			return url;
		}
		
		/**
		 * Sets the URL associated with the user info.
		 *
		 * @param url the URL to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		
		/**
		 * Returns the user info data.
		 *
		 * @return the user info data
		 */
		public UserInfoReadModel getInfo() {
			return info;
		}
		
		/**
		 * Sets the user info data.
		 *
		 * @param info the user info data to set
		 */
		public void setInfo(UserInfoReadModel info) {
			this.info = info;
		}
		
	}
	
	/**
	 * The list of user info resources.
	 */
	private List<UserResource> users;
	
	/**
	 * Returns the list of user info resources.
	 *
	 * @return the list of user info resources
	 */
	public List<UserResource> getUsers() {
		return users;
	}
	
	/**
	 * Sets the list of user info resources.
	 *
	 * @param resources the list of user info resources to set
	 */
	public void setResources(List<UserResource> users) {
		this.users = users;
	}

}
