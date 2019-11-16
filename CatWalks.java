package com.klimek.first;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CatWalks {
    private int id;
    private String name;
    private String type;
    private int pavilonN;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPavilonN() {
		return pavilonN;
	}
	public void setPavilonN(int pavilonN) {
		this.pavilonN = pavilonN;
	}
}
