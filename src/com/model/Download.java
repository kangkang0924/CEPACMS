package com.model;

public class Download {

	private int downloadID;
	private String downloadName;
	private int dmId;
	private String mName;
	public int getDownloadID() {
		return downloadID;
	}
	public void setDownloadID(int downloadID) {
		this.downloadID = downloadID;
	}
	public String getDownloadName() {
		return downloadName;
	}
	public void setDownloadName(String downloadName) {
		this.downloadName = downloadName;
	}
	public int getDmId() {
		return dmId;
	}
	public void setDmId(int dmId) {
		this.dmId = dmId;
	}	
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public Download() {
    }
	public Download(int downloadID, String downloadName, int dmId, String mName) {
        this.downloadID = downloadID;
        this.downloadName = downloadName;
        this.dmId = dmId;
        this.mName = mName;
    }
	public Download(int downloadID, String downloadName, int dmId) {
        this.downloadID = downloadID;
        this.downloadName = downloadName;
        this.dmId = dmId;
    }
	@Override
    public String toString() {
        return "Download{" +
                "downloadID=" + downloadID +
                ", downloadName='" + downloadName + '\'' +
                ", dmId=" + dmId +
                ", mName='" + mName + '\'' +
                '}';
    }
}
