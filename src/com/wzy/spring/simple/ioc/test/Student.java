package com.wzy.spring.simple.ioc.test;

public class Student {

	public String sid;
	public String sname;
	public StudentClass sclass;
	public Student(String sid, String sname, StudentClass sclass) {
		this.sid = sid;
		this.sname = sname;
		this.sclass = sclass;
	}
	public Student() {
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public StudentClass getSclass() {
		return sclass;
	}
	public void setSclass(StudentClass sclass) {
		this.sclass = sclass;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", sclass="
				+ sclass + "]";
	}
	
	
	
	
}
