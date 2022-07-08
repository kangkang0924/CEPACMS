package com.model;

public class Community {

	private int cId;
    private String cName;
    private String cProvince;
    private String cCity;
    private String cStreet;
    private String cTel;   

    public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcProvince() {
		return cProvince;
	}
	public void setcProvince(String cProvince) {
		this.cProvince = cProvince;
	}
	public String getcCity() {
		return cCity;
	}
	public void setcCity(String cCity) {
		this.cCity = cCity;
	}
	public String getcStreet() {
		return cStreet;
	}
	public void setcStreet(String cStreet) {
		this.cStreet = cStreet;
	}
	public String getcTel() {
		return cTel;
	}
	public void setcTel(String cTel) {
		this.cTel = cTel;
	}
	public Community() {
    }

    public Community(int cId, String cName, String cProvince, String cCity, String cStreet, String cTel) {
        this.cId = cId;
        this.cName = cName;
        this.cProvince = cProvince;
        this.cCity = cCity;
        this.cStreet = cStreet;
        this.cTel = cTel;
    }

    @Override
    public String toString() {
        return "Community{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cProvince='" + cProvince + '\'' +
                ", cCity='" + cCity + '\'' +
                ", cStreet='" + cStreet + '\'' +
                ", cTel='" + cTel + '\'' +
                '}';
    }
}
