package com.nwnu.serivce.impl;

import java.math.BigDecimal;
import java.util.Date;

public class Teacherinfo {
	/** ��ʦ���� */
	private int teacher_id = -1;
	/** ��ʦȷ������ */
	private int checknum;
	/** ��ʦ�����¶� */
	private BigDecimal temperature;
	/** ��ʦ�Ƿ���֢״ */
	private String symptom;
	/** ��ʦ������Ϣ��д���� */
	private Date date;
	/** ��ʦȷ����� */
	private String check;
	/** ��ʦ�Ա� */
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Teacherinfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacherinfo(int teacher_id, BigDecimal temperature, String symptom, Date date, String check) {
		super();
		this.teacher_id = teacher_id;
		this.temperature = temperature;
		this.symptom = symptom;
		this.date = date;
		this.check = check;

	}

	public Teacherinfo(java.sql.Date date, String sex, int checknum) {
		// TODO Auto-generated constructor stub
		super();
		this.date = date;
		this.sex = sex;
		this.setChecknum(checknum);
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
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

}
