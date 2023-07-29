package Data;

public class JwtDTO {
	
	private String messageResult;
	private String jwtoken;
	
	public JwtDTO() {
		
	}
	
	public String getMessageResult() {
		return messageResult;
	}
	
	public void setMessageResult(String messageResult) {
		this.messageResult = messageResult;
	}
	
	public String getJwtoken() {
		return jwtoken;
	}
	
	public void setJwtoken(String jwtoken) {
		this.jwtoken = jwtoken;
	}

}
