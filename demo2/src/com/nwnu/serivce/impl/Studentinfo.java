package com.nwnu.serivce.impl;

import java.math.BigDecimal;
import java.util.Date;

public class Studentinfo {
	/** 学生学号*/
	private int student_id = -1;
	/** 学生确诊人数*/
	private int checknum;
	/** 学生当天体温*/
	private BigDecimal temperature;
	/** 学生是否存在症状*/
	private String symptom;
	/** 学生填写日期*/
	private Date date;
	/** 学生当天确诊情况*/
	private String check;
	/** 学生性别*/
	private String sex;

	public Studentinfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Studentinfo(int student_id, BigDecimal temperature, String symptom, Date date, String check) {
		super();
		this.student_id = student_id;
		this.temperature = temperature;
		this.symptom = symptom;
		this.date = date;
		this.check = check;

	}


	public Studentinfo(java.sql.Date date, String sex, int checknum) {
		// TODO Auto-generated constructor stub
		super();
		this.checknum=checknum;
		this.date=date;
		this.sex=sex;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature2) {
		this.temperature = temperature2;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public int getChecknum() {
		return checknum;
	}

	public void setChecknum(int checknum) {
		this.checknum = checknum;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
