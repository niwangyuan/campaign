package com.test.string.string;

import java.util.ArrayList;
import java.util.List;

public class Campcenter {

	private String name;
	private Long pv;
	private List<Camp_entity> con = new ArrayList<Camp_entity>();

	public Campcenter(String name, Long pv) {
		this.name = name;
		this.pv = pv;

	}

	public Campcenter(Camp_entity info) {
		this.name = info.getName();
		this.pv = info.getPv();
		con.add(info);
	}

	public void operation() {
		Long max = 0L;
		for (Camp_entity factor : con) {
			Long pv_ = factor.getPv();
			if (pv_ > max) {
				if (!this.name.equals("unknow")) {
					this.name = factor.getName();
				}
				this.pv = pv_;
				max = pv_;
			}
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPv() {
		return pv;
	}

	public void setPv(Long pv) {
		this.pv = pv;
	}

	public List<Camp_entity> getCon() {
		return con;
	}

	public void setCon(List<Camp_entity> con) {
		this.con = con;
	}

}
