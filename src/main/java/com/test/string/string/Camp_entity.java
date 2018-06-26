package com.test.string.string;

public class Camp_entity {
	
	private String name;//去特殊字符
	
	private String raw;//原始字符
	
	private Long pv;//频率
	
	private Campcenter center;//归属簇

	public Camp_entity(Campcenter c) {
		this.name=c.getName();
		this.pv=c.getPv();
	}

	public Camp_entity(String name, Long pv, String raw) {
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

	public Long getPv() {
		return pv;
	}

	public void setPv(Long pv) {
		this.pv = pv;
	}

	public Campcenter getCenter() {
		return center;
	}

	public void setCenter(Campcenter center) {
		this.center = center;
	}

}
