package com.wzy.spring.simple.ioc.test;

public class StudentClass {

	public String cid;
	public String cname;
	public StudentClass(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}
	public StudentClass() {
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "StudentClass [cid=" + cid + ", cname=" + cname + "]";
	}

	
}
