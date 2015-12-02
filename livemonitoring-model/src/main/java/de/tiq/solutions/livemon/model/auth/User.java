package de.tiq.solutions.livemon.model.auth;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


public class User {

	public User() {
		super();
	}
	public User(String fistname, Date endtime, int numberOfClients,String passwort) {
		super();
		this.fistname = fistname;
		this.passwort=passwort;
		 
		this.endtime = endtime;
		this.numberOfClients = numberOfClients;
	}
	private String fistname;
	private Date endtime;
	private int numberOfClients;
	private String passwort;
	
	public String getFistname() {
		return fistname;
	}
	public void setFistname(String fistname) {
		this.fistname = fistname;
	}
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public int getNumberOfClients() {
		return numberOfClients;
	}
	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}
  
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	@Override
	public String toString() {
		return "User [fistname=" + fistname +   ", endtime=" + endtime + ", numberOfClients=" + numberOfClients + ", passwort=" + passwort + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fistname == null) ? 0 : fistname.hashCode());
		result = prime * result + ((passwort == null) ? 0 : passwort.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (fistname == null) {
			if (other.fistname != null)
				return false;
		} else if (!fistname.equals(other.fistname))
			return false;
		if (passwort == null) {
			if (other.passwort != null)
				return false;
		} else if (!passwort.equals(other.passwort))
			return false;
		return true;
	}
	
	
}
