package test_eCommerceAPIEndToEnd_pojo;

public class Login_Response {
	
	 private String token;
	    private String userId;
	    private String message;

	    // Getters and Setters
	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public String getUserId() {
	        return userId;
	    }

	    public void setUserId(String userId) {
	        this.userId = userId;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
}