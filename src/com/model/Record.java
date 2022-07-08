package com.model;

public class Record {

	private int rId;
    private int rmId;
    private int rcId;
    private String rIsNucleicAcidTest;
    private String rIsOutCity;
    private String rIsFromHB;
    private String rIsHousehold;
    private String rNowTime;
    private String cName;
    private String mName;

    public int getrId() {
        return rId;
    }
    public void setrId(int rId) {
        this.rId = rId;
    }
    public int getRmId() {
        return rmId;
    }
    public void setRmId(int rmId) {
        this.rmId = rmId;
    }
    public int getRcId() {
        return rcId;
    }
    public void setRcId(int rcId) {
        this.rcId = rcId;
    }
    public String getrIsNucleicAcidTest() {
		return rIsNucleicAcidTest;
	}
	public void setrIsNucleicAcidTest(String rIsNucleicAcidTest) {
		this.rIsNucleicAcidTest = rIsNucleicAcidTest;
	}
	public String getrIsOutCity() {
		return rIsOutCity;
	}
	public void setrIsOutCity(String rIsOutCity) {
		this.rIsOutCity = rIsOutCity;
	}
	public String getrIsFromHB() {
		return rIsFromHB;
	}
	public void setrIsFromHB(String rIsFromHB) {
		this.rIsFromHB = rIsFromHB;
	}
	public String getrIsHousehold() {
		return rIsHousehold;
	}
	public void setrIsHousehold(String rIsHousehold) {
		this.rIsHousehold = rIsHousehold;
	}
	public String getrNowTime() {
        return rNowTime;
    }
    public void setrNowTime(String rNowTime) {
        this.rNowTime = rNowTime;
    }
    public String getcName() {
        return cName;
    }
    public void setcName(String cName) {
        this.cName = cName;
    }
    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
    public Record() {
    }

    public Record(int rId, int rmId, int rcId, String rIsNucleicAcidTest, String rIsOutCity, String rIsFromHB, String rIsHousehold, String rNowTime, String cName, String mName) {
        this.rId = rId;
        this.rmId = rmId;
        this.rcId = rcId;
        this.rIsNucleicAcidTest = rIsNucleicAcidTest;
        this.rIsOutCity = rIsOutCity;
        this.rIsFromHB = rIsFromHB;
        this.rIsHousehold = rIsHousehold;
        this.rNowTime = rNowTime;
        this.cName = cName;
        this.mName = mName;
    }

    public Record(int rId, int rmId, int rcId, String rIsNucleicAcidTest, String rIsOutCity, String rIsFromHB, String rIsHousehold, String rNowTime) {
        this.rId = rId;
        this.rmId = rmId;
        this.rcId = rcId;
        this.rIsNucleicAcidTest = rIsNucleicAcidTest;
        this.rIsOutCity = rIsOutCity;
        this.rIsFromHB = rIsFromHB;
        this.rIsHousehold = rIsHousehold;
        this.rNowTime = rNowTime;
    }
    @Override
    public String toString() {
        return "Record{" +
                "rId=" + rId +
                ", rmId=" + rmId +
                ", rcId=" + rcId +
                ", rIsNucleicAcidTest='" + rIsNucleicAcidTest + '\'' +
                ", rIsOutCity='" + rIsOutCity + '\'' +
                ", rIsFromHB='" + rIsFromHB + '\'' +
                ", rIsHousehold='" + rIsHousehold + '\'' +
                ", rNowTime='" + rNowTime + '\'' +
                ", cName='" + cName + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }
}
