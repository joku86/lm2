package de.tiq.solutions.livemon.model.auth;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class UserModel {

	private Set<User> user;
	
	public static UserModel create(){
		return new UserModel();
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
	
	
	
}
