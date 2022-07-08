package com.model;

public class Upload {

	private int uploadID;
	private String uploadName;
	private int umId;
	private String mName;
	public int getUploadID() {
		return uploadID;
	}
	public void setUploadID(int uploadID) {
		this.uploadID = uploadID;
	}
	public String getUploadName() {
		return uploadName;
	}
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	public int getUmId() {
		return umId;
	}
	public void setUmId(int umId) {
		this.umId = umId;
	}	
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public Upload() {
    }
	public Upload(int uploadID, String uploadName, int umId, String mName) {
        this.uploadID = uploadID;
        this.uploadName = uploadName;
        this.umId = umId;
        this.mName = mName;
    }
	public Upload(int uploadID, String uploadName, int umId) {
        this.uploadID = uploadID;
        this.uploadName = uploadName;
        this.umId = umId;
    }
	@Override
    public String toString() {
        return "Upload{" +
                "uploadID=" + uploadID +
                ", uploadName='" + uploadName + '\'' +
                ", umId=" + umId +
                ", mName='" + mName + '\'' +
                '}';
    }
}
