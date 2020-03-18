package com.nwmu.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import com.jakey.pojo.Teacher;
import com.jakey.pojo.Teacherinfo;

//教师查询
public class Selectteacher {
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
	 * @param Connection con 连接数据库
	 * 
	 */
	public Selectteacher(Connection con) {

		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 添加教师信息
	 * 
	 * @param con     连接数据库
	 * @param teacher 添加Teacher类实例
	 * @return 返回结果集
	 */
	public int AddTeacher(Connection con, Teacher teacher) throws SQLException {
		String sql = "insert into teacher value(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, teacher.getTeacher_id());
		pstmt.setString(2, teacher.getTeacher_name());
		pstmt.setString(3, teacher.getSex());
		pstmt.setString(4, teacher.getT_pro());
		pstmt.setString(5, teacher.getT_city());
		return pstmt.executeUpdate();
	}

	/**
	 * 
	 * 添加教师防疫信息
	 * 
	 * @param con   连接数据库
	 * @param tinfo 添加Teacherinfo类实例
	 * @return 返回结果集
	 * 
	 */
	public int AddTeacherInfo(Connection con, Teacherinfo tinfo) throws SQLException {
		String sql = "insert into teacher_info value(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, tinfo.getTeacher_id());
		pstmt.setString(2, tinfo.getSymptom());
		pstmt.setString(3, tinfo.getCheck());
		pstmt.setBigDecimal(4, tinfo.getTemperature());
		pstmt.setDate(5, (java.sql.Date) tinfo.getDate());
		return pstmt.executeUpdate();
	}
	

	public void SelectByDateIdTeacher(Connection con, Date date, int id) throws SQLException {
		String sql = "select * from teacher t, teacher_info t1 where t.teacher_id = t1.teacher_id and t1.date = ? and t.teacher_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setDate(1, (java.sql.Date) date);
		pstmt.setInt(2, id);
		rs = pstmt.executeQuery();
		display();
	}

	/** 显示所有教师信息 */
	public void selectAllTeacher() throws SQLException {
		String sql = "select * from teacher t, teacher_info t1 where t.teacher_id = t1.teacher_id order by t.teacher_id";
		rs = stmt.executeQuery(sql);
		System.out.println("教师信息");
		System.out.println("工号\t姓名\t性别\t所处省份\t市区\t当日温度\t症状\t填报时间\t\t是否确诊\t");
		display();
	}

	/**
	 * 根据工号查询信息
	 *
	 * @param con        连接数据库
	 * @param teacher_id 针对teacher_id进行查询
	 */
	public void selectTeacherById(Connection con, int teacher_id) throws SQLException {
		String sql = "select * from teacher t, teacher_info t1 where t.teacher_id = t1.teacher_id and t.teacher_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, teacher_id);
		rs = pstmt.executeQuery();
		display();
	}

	/**
	 * 从数据读出查询结果并输出
	 * 
	 * @throws 抛出SQLException
	 */
	private void display() throws SQLException {
		// TODO Auto-generated method stub
		while (rs.next()) {
			String id = rs.getString("teacher_id");
			String name = rs.getString("teacher_name");
			String sex = rs.getString("sex");
			String t_pro = rs.getString("t_pro");
			String t_city = rs.getString("t_city");
			BigDecimal temprature = rs.getBigDecimal("temprature");
			Date date = rs.getDate("date");
			String symptom = rs.getString("symptom");
			String check = rs.getString("check1");
			System.out.print(id + "\t" + name + "\t" + sex + "\t" + t_pro + "\t" + t_city + "\t" + temprature + "\t"
					+ symptom + "\t" + date + "\t" + check + "\n" + "\n");
		}
	}

}
