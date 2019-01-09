package Myvisit;

import java.util.Date;

public class Visitor {
	int ID;
	int UserID;
	Date VisitTime;
	Date LeftTime;
	String ip;
	String comefrom;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public Date getVisitTime() {
		return VisitTime;
	}

	public void setVisitTime(Date visitTime) {
		VisitTime = visitTime;
	}

	public Date getLeftTime() {
		return LeftTime;
	}

	public void setLeftTime(Date leftTime) {
		LeftTime = leftTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getComefrom() {
		return comefrom;
	}

	public void setComefrom(String comefrom) {
		this.comefrom = comefrom;
	}

	@Override
	public String toString() {
		return "Visitor [UserID=" + UserID + ", VisitTime=" + VisitTime + ", LeftTime=" + LeftTime + ", ip=" + ip
				+ ", comefrom=" + comefrom + "]";
	}
	
}
