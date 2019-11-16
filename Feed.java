package com.klimek.first;

import java.sql.Date;

public class Feed {
    private int id;
    private int ilosc;
    private Date date;
    private int zwierzeta_id;
    private int pracownicy_id;
    private int pokarm_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIlosc() {
		return ilosc;
	}
	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getZwierzeta_id() {
		return zwierzeta_id;
	}
	public void setZwierzeta_id(int zwierzeta_id) {
		this.zwierzeta_id = zwierzeta_id;
	}
	public int getPracownicy_id() {
		return pracownicy_id;
	}
	public void setPracownicy_id(int pracownicy_id) {
		this.pracownicy_id = pracownicy_id;
	}
	public int getPokarm_id() {
		return pokarm_id;
	}
	public void setPokarm_id(int pokarm_id) {
		this.pokarm_id = pokarm_id;
	}
}
