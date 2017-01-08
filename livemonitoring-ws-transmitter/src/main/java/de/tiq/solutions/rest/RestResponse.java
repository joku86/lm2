package de.tiq.solutions.rest;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestResponse {

  private String msg;
  private int wert=99;
  @XmlAttribute
	public int getWert() {
	return wert;
}

public void setWert(int wert) {
	this.wert = wert;
}

	@XmlElement
  public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

public RestResponse() {}

  public RestResponse(String msg) {
    this.msg = msg;
  }
}
