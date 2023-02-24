package wifidb;

public class Bookmark {
	private int ID;
	private String MAIN_NM;
	private String REGISTER_DATE;
	private String bookmark_name;
	private int BG_ID;
	private String MRG_NO;
	
	public String getBookmark_name() {
		return bookmark_name;
	}
	public void setBookmark_name(String bookmark_name) {
		this.bookmark_name = bookmark_name;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getMAIN_NM() {
		return MAIN_NM;
	}
	public void setMAIN_NM(String mAIN_NM) {
		MAIN_NM = mAIN_NM;
	}
	public String getREGISTER_DATE() {
		return REGISTER_DATE;
	}
	public void setREGISTER_DATE(String rEGISTER_DATE) {
		REGISTER_DATE = rEGISTER_DATE;
	}
	public int getBG_ID() {
		return BG_ID;
	}
	public void setBG_ID(int bG_ID) {
		BG_ID = bG_ID;
	}
	public String getMRG_NO() {
		return MRG_NO;
	}
	public void setMRG_NO(String mRG_NO) {
		MRG_NO = mRG_NO;
	}
}
