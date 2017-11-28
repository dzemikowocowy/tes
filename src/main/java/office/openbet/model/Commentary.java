package office.openbet.model;

public class Commentary {
	
	public static final String VOID_COMMENTARY = "General information";
	
	String commentType;
	
	String type1Score;
	
	String type2Score;
	
	String freeText;
	

	public Commentary() {
		this.commentType = VOID_COMMENTARY;
		this.type1Score = "";
		this.type2Score = "";
		this.freeText = "";
	}
	

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public String getType1Score() {
		return type1Score;
	}

	public void setType1Score(String type1Score) {
		this.type1Score = type1Score;
	}

	public String getType2Score() {
		return type2Score;
	}

	public void setType2Score(String type2Score) {
		this.type2Score = type2Score;
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

}
