package com.nwnu.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
	private static String dbUrl = "jdbc:mysql://localhost:3306/eps_management?useUnicode=true&characterEncoding=utf8";
	private static String dbUserName = "root";
	private static String dbPassword = "";
	private static String jdbcName = "com.mysql.jdbc.Driver";

	public static Connection getCon() throws Exception {
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}

	public static void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

	public static void main(String[] args) {
		database dbUtil = new database();
		try {
			database.getCon();
			System.out.println("输出有错!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
