package com.nwmu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Selectsum {
	/** 创建Statement对象 */
	Statement stmt;
	/** 创建结果集 */
	ResultSet rs;

	public Statement getStmt() {
		return stmt;
	}

	/**
	 * 
	 * 建立到eps_management.sql的连接
	 * 
	 * @param con 连接数据库
	 * 
	 */
	public Selectsum(Connection con) {
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查询所有确诊学生信息
	 */

	public void selectStudentCheck() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from student s,stu_info s1 where s1.check1='Y' and s.student_id = s1.s_id order by s.student_id";
		rs = stmt.executeQuery(sql);
		System.out.println("确诊学生信息");
		System.out.println("学号\t" + "\t姓名\t性别\t所处省份\t市区\t当日温度\t" + "    " + "日期");
		displayStudent();
	}

	/**
	 * 查询所有确诊教师信息
	 */

	public void selectTeacherCheck() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from teacher t, teacher_info t1 where t.teacher_id = t1.teacher_id and t1.check1='Y' order by t.teacher_id";
		rs = stmt.executeQuery(sql);
		System.out.println("确诊教师信息");
		System.out.println("工号\t姓名\t性别\t所处省份\t市区\t当日温度\t填报时间\t");
		displayTeacher();

	}

	/**
	 * 根据日期查询学生信息
	 *
	 * @param con  连接数据库
	 * @param date 针对date进行查询
	 */

	public void selectDate(Connection con, Date date) throws SQLException {
		String sql = "select * from student s, stu_info s1 where s.student_id = s1.s_id and s1.date like ? order by s.student_id";
		PreparedStatement pstmt = con.prepareStatement(sql);
		// 查找输入的时间相关的信息
		pstmt.setDate(1, (java.sql.Date) date);
		rs = pstmt.executeQuery();
		System.out.println("该天信息");
		System.out.println("学生信息");
		displayStudent();
	}

	/**
	 * 根据日期查询教师信息
	 *
	 * @param con  连接数据库
	 * @param date 针对date进行查询
	 */
	public void selectDateTeacher(Connection con, Date date) throws SQLException {
		String sql = "select * from teacher t, teacher_info t1 where t.teacher_id = t1.teacher_id and t1.date like ?\"%\" order by t.teacher_id";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setDate(1, (java.sql.Date) date);
		rs = pstmt.executeQuery();
		System.out.println("教师信息");
		displayTeacher();
	}

	/**
	 * 根据日期区间查询教师信息
	 *
	 * @param con   连接数据库
	 * @param date1 针对>=date1进行查询
	 * @param date2 针对<=date1进行查询
	 */
	public void selectBetweenDateT(Connection con, Date date1, Date date2) throws SQLException {
		String sql = "select * from teacher t, teacher_info t1 where t.teacher_id = t1.teacher_id and t1.date>= ? and t1.date<= ? order by t.teacher_id";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setDate(1, (java.sql.Date) date1);
		pstmt.setDate(2, (java.sql.Date) date2);
		rs = pstmt.executeQuery();
		System.out.println("教师信息");
		displayTeacher();

	}

	/**
	 * 根据日期区间查询学生信息
	 *
	 * @param con   连接数据库
	 * @param date1 针对>=date1进行查询
	 * @param date2 针对<=date1进行查询
	 */
	public void selectBetweenDateS(Connection con, Date date1, Date date2) throws SQLException {
		String sql = "select * from student s,stu_info s1 where s.student_id = s1.s_id  and s1.date>= ? and s1.date<= ? order by s.student_id";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setDate(1, (java.sql.Date) date1);
		pstmt.setDate(2, (java.sql.Date) date2);
		rs = pstmt.executeQuery();
		System.out.println("学生信息:");
		displayStudent();

	}


	/**
	 * 从数据读出查询关于学生结果并输出
	 * 
	 * @throws 抛出SQLException
	 */
	public void displayStudent() throws SQLException {
		while (rs.next()) {
			String id = rs.getString("s_id");
			String name = rs.getString("student_name");
			String stu_sex = rs.getString("stu_sex");
			String student_pro = rs.getString("student_pro");
			String student_city = rs.getString("student_city");
			Float temprature = rs.getFloat("temperature");
			Date date = rs.getDate("date");
			System.out.print(id + "\t" + name + "\t" + stu_sex + "\t" + student_pro + "\t" + student_city + "\t"
					+ temprature + "\t" + date + "\t" + "\n" + "\n");
		}
	}

	/**
	 * 从数据读出关于教师查询结果并输出
	 * 
	 * @throws 抛出SQLException
	 */
	public void displayTeacher() throws SQLException {
		while (rs.next()) {
			String id = rs.getString("teacher_id");
			String name = rs.getString("teacher_name");
			String sex = rs.getString("sex");
			String t_pro = rs.getString("t_pro");
			String t_city = rs.getString("t_city");
			Float temprature = rs.getFloat("temprature");
			Date date = rs.getDate("date");
			System.out.print(id + "\t" + name + "\t" + sex + "\t" + t_pro + "\t" + t_city + "\t" + temprature + "\t"
					+ date + "\n" + "\n");
		}
	}

}
