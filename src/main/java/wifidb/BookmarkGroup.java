package wifidb;

public class BookmarkGroup {
	private int ID;
	private String BOOKMARK_NAME;
	private int ORDER;
	private String REGISTER_DATE;
	private String EDIT_DATE;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getBOOKMARK_NAME() {
		return BOOKMARK_NAME;
	}
	public void setBOOKMARK_NAME(String bOOKMARK_NAME) {
		BOOKMARK_NAME = bOOKMARK_NAME;
	}
	public int getORDER() {
		return ORDER;
	}
	public void setORDER(int oRDER) {
		ORDER = oRDER;
	}
	public String getREGISTER_DATE() {
		return REGISTER_DATE;
	}
	public void setREGISTER_DATE(String rEGISTER_DATE) {
		REGISTER_DATE = rEGISTER_DATE;
	}
	public String getEDIT_DATE() {
		return EDIT_DATE;
	}
	public void setEDIT_DATE(String eDIT_DATE) {
		EDIT_DATE = eDIT_DATE;
	}
}
