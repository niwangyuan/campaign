package com.test.string.string;

public class Campinfo {
	
	private String name;//去特殊字符
	
	private String raw;//原始字符
	
	private double pv;//频率
	
	private Campcenter center;//归属簇

	public Campinfo(Campcenter c) {
		this.name=c.getName();
		this.pv=c.getPv();

	}

	public Campinfo(String name, double pv, String raw) {
		this.name = name;
		this.pv = pv;
		this.raw = raw;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public double getPv() {
		return pv;
	}

	public void setPv(double pv) {
		this.pv = pv;
	}

	public Campcenter getCenter() {
		return center;
	}

	public void setCenter(Campcenter center) {
		this.center = center;
	}

}
