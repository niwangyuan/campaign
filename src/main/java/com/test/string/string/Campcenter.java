package com.test.string.string;

import java.util.ArrayList;
import java.util.List;

public class Campcenter {

	private String name;
	private double pv;
	private List<Campinfo> con=new ArrayList<Campinfo>();
	
	public Campcenter(String name,int pv)
	{
		this.name=name;
		this.pv=pv;

	}
	
	public Campcenter(Campinfo info) {
		this.name=info.getName();
		this.pv=info.getPv();
		con.add(info);
	}
	
	public void operation() {
		double max=0;
		for (Campinfo factor:con) {
			double pv_=factor.getPv();
			if(pv_>max) {
				this.name=factor.getName();
				this.pv=pv_;
				max=pv_;
			}
		}
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPv() {
		return pv;
	}
	public void setPv(double pv) {
		this.pv = pv;
	}
	public List<Campinfo> getCon() {
		return con;
	}
	public void setCon(List<Campinfo> con) {
		this.con = con;
	}


}
