package com.model;

public class Account {
	
	private int accountID;
    private String accountName;
    private String accountPassword;
    private String accountStatus;
    private String accountRole;
    
    public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getAccountRole() {
		return accountRole;
	}
	public void setAccountRole(String accountRole) {
		this.accountRole = accountRole;
	}
	public Account() {
    }

    public Account(int accountID, String accountName, String accountPassword, String accountStatus, String accountRole) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.accountStatus = accountStatus;
        this.accountRole = accountRole;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", accountName='" + accountName + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", accountRole='" + accountRole + '\'' +
                '}';
    }
}
