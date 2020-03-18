package com.nwnu.serivce;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;

import org.apache.tomcat.jni.User;
import org.omg.DynamicAny.DynAnyOperations;

import com.nwmu.dao.UserDao;
import com.nwmu.dao.UserDao1;
import com.nwmu.dao.impl.Selectstudent;
import com.nwmu.dao.impl.Selectsum;
import com.nwmu.dao.impl.Selectteacher;
import com.nwnu.serivce.impl.Student;
import com.nwnu.serivce.impl.Studentinfo;
import com.nwnu.serivce.impl.Teacher;
import com.nwnu.serivce.impl.Teacherinfo;
import com.nwnu.test.database;




public class UserSerivce {
	public static void main(String[] args) throws Exception {
		Menu();
		ArrayList<Teacher> t = new ArrayList<Teacher>();
		ArrayList<Teacherinfo> tinfo = new ArrayList<Teacherinfo>();
		ArrayList<Student> s = new ArrayList<Student>();
		ArrayList<Studentinfo> sinfo = new ArrayList<Studentinfo>();
		int choice;
		int choice1;
		int choice2;
		int choice3;
		int choice4;
		// 菜单显示
		while (true) {
			Scanner in = new Scanner(System.in);
			choice = in.nextInt();
			switch (choice) {
			case 1:
				// 添加操作
				MenuAdd();
				do {
					Scanner in_addteacher = new Scanner(System.in);
					choice3 = in_addteacher.nextInt();
					switch (choice3) {
					case 1:
						// 添加教师信息
						TeacherAdd(t);
						MenuAdd();
						break;

					case 2:
						// 添加学生信息
						StudentAdd(s);
						MenuAdd();
						break;

					case 3:
						// 添加教师防疫信息
						AddTeacherInfo(tinfo);
						MenuAdd();
						break;

					case 4:
						// 添加学生防疫信息
						AddStudentInfo(sinfo);
						MenuAdd();
						break;

					}
				} while (choice3 != 5);
				break;

			case 2:
				// 全部信息显示
				Menu1();
				do {
					Scanner in1 = new Scanner(System.in);
					choice1 = in1.nextInt();
					switch (choice1) {
					case 1:
						// 教师信息
						SelectionAllTeacher();
						Menu1();
						break;

					case 2:
						// 学生信息
						SelectionAllStudent();
						Menu1();
						break;
						

					case 3:
						// 查看学生确诊情况
						SelectionStudentCheck();
						Menu2();
						break;

					case 4:
						// 查看教师确诊情况
						SelectionTeacherCheck();
						Menu2();
						break;
					}
				} while (choice1 != 5);
				break;

			case 3:
				// 特征查询
				Menu2();
				do {
					Scanner in2 = new Scanner(System.in);
					choice2 = in.nextInt();
					switch (choice2) {
					case 1:
						// 根据工号查询
						SelectionTeacherById();
						Menu2();
						break;

					case 2:
						// 根据学号查询
						SelectionStudentById();
						Menu2();
						break;
						
					case 3:
						SelectTeacherByIdDate();
						Menu2();
						break;


					case 4:
						// 根据日期查询信息
						SelectionDate();
						Menu2();
						break;

					case 5:
						// 根据日期区间查询信息
						SelctionDateBetween();
						Menu2();
						break;

					}

				} while (choice2 != 6);
				break;

			case 4:
				// 柱状图
				MenuFrame();
				do {
					Scanner in4 = new Scanner(System.in);
					choice4 = in.nextInt();
					switch (choice4) {
					case 1:
						// 教师确诊男女情况图
						TeacherCheckInfo();
						MenuFrame();
						break;

					case 2:
						// 学生确诊男女情况图
						StudentCheckInfo();
						MenuFrame();
						break;
					}
				} while (choice4 != 3);
				break;
				
			case 5:
				System.exit(0);

			}
			Menu();
		}
	}



	/**
	 * 菜单
	 */
	private static void Menu() {
		// TODO Auto-generated method stub
		System.out.println("请输入想要进行的操作:");
		System.out.println("1.添加信息");
		System.out.println("2.查询全部信息");
		System.out.println("3.特征查询");
		System.out.println("4.柱状图");
		System.out.println("5.退出系统");
	}

	/**
	 * 添加信息菜单
	 */
	private static void MenuAdd() {
		// TODO Auto-generated method stub
		System.out.println("请输入想要进行的操作:");
		System.out.println("1.添加教师");
		System.out.println("2.添加学生");
		System.out.println("3.添加教师防疫信息");
		System.out.println("4.添加学生防疫信息");
		System.out.println("5.返回上一级");
	}

	/**
	 * 显示信息菜单
	 */
	private static void Menu1() {
		// TODO Auto-generated method stub
		System.out.println("请输入想要进行的操作:");
		System.out.println("1.查询全部教师信息");
		System.out.println("2.查询全部学生信息");
		System.out.println("3.显示学生确诊信息");
		System.out.println("4.显示教师确诊信息");
		System.out.println("5.返回上一级");
	}

	/**
	 * 特征查询菜单
	 */
	private static void Menu2() {
		// TODO Auto-generated method stub
		System.out.println("请输入想要进行的操作:");
		System.out.println("1.根据工号查询教师信息");
		System.out.println("2.根据学号查询学生信息");
		System.out.println("3.根据日期/id查询信息");
		System.out.println("4.查询日期信息");
		System.out.println("5.查询段时间信息");
		System.out.println("6.返回上一级");

	}

	/**
	 * 柱状图菜单
	 */
	private static void MenuFrame() {
		// TODO Auto-generated method stub
		System.out.println("请输入想要进行的操作:");
		System.out.println("1.教师每日确诊情况");
		System.out.println("2.学生每日确诊情况");
		System.out.println("3.返回上一级");
	}

	/**
	 * 教师信息添加
	 * 
	 * @param arrayList
	 * @return
	 * @throws Exception
	 */
	private static ArrayList<Teacher> TeacherAdd(ArrayList<Teacher> arrayList) throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Selectteacher dao = new Selectteacher(con);
		System.out.println("请输入教师信息(按工号,姓名,性别,所在城市,所在省份)：");
		Scanner t = new Scanner(System.in);
		int teacher_id = t.nextInt();
		String teacher_name = t.next();
		String sex = t.next();
		String t_pro = t.next();
		String t_city = t.next();
		Teacher teacher = new Teacher();
		teacher.setTeacher_id(teacher_id);
		teacher.setTeacher_name(teacher_name);
		teacher.setSex(sex);
		teacher.setT_pro(t_pro);
		teacher.setT_city(t_city);

		try {
			dao.AddTeacher(con, teacher);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}
		if (arrayList != null) {
			System.out.println("添加成功");
			return arrayList;
		}
		System.out.println("添加失败");
		return null;

	}

	/**
	 * 学生信息添加
	 * 
	 * @param arrayList 学生信息
	 * @return arrayList
	 * @throws Exception
	 */
	private static ArrayList<Student> StudentAdd(ArrayList<Student> arrayList) throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Selectstudent dao = new Selectstudent(con);
		System.out.println("请输入学生信息(按学号,姓名,性别,所在城市,所在省份)：");
		Scanner t = new Scanner(System.in);
		int student_id = t.nextInt();
		String student_name = t.next();
		String stu_sex = t.next();
		String student_pro = t.next();
		String student_city = t.next();
		Student student = new Student();
		student.setStudent_id(student_id);
		student.setStu_name(student_name);
		student.setStu_sex(stu_sex);
		student.setStu_pro(student_pro);
		student.setStu_city(student_city);

		try {
			dao.AddStudent(con, student);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}
		if (arrayList != null) {
			System.out.println("添加成功");
			return arrayList;
		}
		System.out.println("添加失败");
		return null;
	}

	/**
	 * 教师防疫信息添加
	 * 
	 * @param arrayList 教师防疫信息
	 * @return arrayList
	 * @throws Exception
	 */
	private static ArrayList<Teacherinfo> AddTeacherInfo(ArrayList<Teacherinfo> arrayList) throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Selectteacher dao = new Selectteacher(con);

		System.out.println("请输入教师防疫信息(按工号,有无症状(Y/N),是否确诊(Y/N),温度)：");
		Scanner info = new Scanner(System.in);
		int teacher_id = info.nextInt();
		String symptom = info.next();
		String check = info.next();
		BigDecimal temperature = info.nextBigDecimal();

		java.util.Date curDate = new java.util.Date();
		// 进行日期的转换
		java.sql.Date date = new java.sql.Date(curDate.getTime());

		Teacherinfo tinfo = new Teacherinfo();
		tinfo.setTeacher_id(teacher_id);
		tinfo.setSymptom(symptom);
		tinfo.setCheck(check);
		tinfo.setTemperature(temperature);
		tinfo.setDate(date);

		try {
			dao.AddTeacherInfo(con, tinfo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}

		if (arrayList != null) {
			System.out.println("添加成功");
			return arrayList;
		}
		System.out.println("添加失败");
		return null;

	}

	/**
	 * 学生防疫信息添加
	 * 
	 * @param arrayList 学生防疫信息
	 * @return arrayList
	 * @throws Exception
	 */
	private static ArrayList<Studentinfo> AddStudentInfo(ArrayList<Studentinfo> arrayList) throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Selectstudent dao = new Selectstudent(con);

		System.out.println("请输入学生防疫信息(按学号,有无症状(Y/N),是否确诊(Y/N),温度)：");
		Scanner info = new Scanner(System.in);
		int student_id = info.nextInt();
		String symptom = info.next();
		String check = info.next();
		BigDecimal temperature = info.nextBigDecimal();
		java.util.Date curDate = new java.util.Date();
		java.sql.Date date = new java.sql.Date(curDate.getTime());

		Studentinfo sinfo = new Studentinfo();
		sinfo.setStudent_id(student_id);
		sinfo.setSymptom(symptom);
		sinfo.setCheck(check);
		sinfo.setTemperature(temperature);
		sinfo.setDate(date);
		try {
			dao.AddStudentinfo(con, sinfo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}

		if (arrayList != null) {
			System.out.println("添加成功");
			return arrayList;
		}
		System.out.println("添加失败");
		return null;

	}

	/**
	 * 教师确诊信息
	 * 
	 * @throws Exception
	 */
	private static void SelectionTeacherCheck() throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Selectsum dao = new Selectsum(con);
		try {
			dao.selectTeacherCheck();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}
	}

	/**
	 * 学生确诊信息
	 * 
	 * @throws Exception
	 */
	private static void SelectionStudentCheck() throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Selectsum dao = new Selectsum(con);
		try {
			dao.selectStudentCheck();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}
	}

	/**
	 * 根据学号进行学生信息查询
	 * 
	 * @throws Exception
	 */
	private static void SelectionStudentById() throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Scanner in = new Scanner(System.in);
		System.out.println("请输入学号：");
		int id = in.nextInt();
		Selectstudent dao = new Selectstudent(con);
		try {
			dao.selectStudentById(con, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}
	}

	/**
	 * 根据工号进行教师信息查询
	 * 
	 * @throws Exception
	 */
	private static void SelectionTeacherById() throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Scanner in = new Scanner(System.in);
		System.out.println("请输入教师工号：");
		int id = in.nextInt();
		Selectteacher dao = new Selectteacher(con);
		try {
			dao.selectTeacherById(con, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}

	}

	/**
	 * 显示所有学生信息以及防疫信息
	 * 
	 * @throws Exception
	 */
	private static void SelectionAllStudent() throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Selectstudent dao = new Selectstudent(con);
		try {
			dao.selectAllStudent();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}

	}

	/**
	 * 显示所有教师信息以及防疫信息
	 * 
	 * @throws Exception
	 */
	private static void SelectionAllTeacher() throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Selectteacher dao = new Selectteacher(con);
		try {
			dao.selectAllTeacher();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}

	}
	/**
	 * 根据id和日期查询信息
	 * @throws Exception
	 */
	private static void SelectTeacherByIdDate() throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Scanner in = new Scanner(System.in);
		System.out.println("请输入查询日期以及id");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = in.next();
		Date date = sdf.parse(str);
		java.sql.Date date1 = new java.sql.Date(date.getTime());
		int id=in.nextInt();
		Selectteacher dao1 = new Selectteacher(con);
		Selectstudent dao2=new Selectstudent(con);
		System.out.println("查询结果：");
		System.out.println("id\t\t姓名\t性别\t所处省份\t市区\t当日温度\t症状\t填报时间\t\t是否确诊\t");
		try {
			dao2.SelectByDateIdStudent(con, date1, id);
		    dao1.SelectByDateIdTeacher(con, date1, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}
	}
	/**
	 * 根据日期查询学生以及教师防疫信息
	 * 
	 * @throws Exception
	 */
	private static void SelectionDate() throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Scanner in = new Scanner(System.in);
		System.out.println("请输入查询日期(YYYY-MM-DD)：");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = in.next();
		Date date = sdf.parse(str);
		java.sql.Date date1 = new java.sql.Date(date.getTime());
		Selectsum dao = new Selectsum(con);
		try {
			dao.selectDate(con, date1);
			dao.selectDateTeacher(con, date1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}

	}

	/**
	 * 根据日期区间查询学生以及教师防疫信息
	 * 
	 * @throws Exception
	 */
	private static void SelctionDateBetween() throws Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		Scanner in = new Scanner(System.in);
		System.out.println("请输入查询日期区间(YYYY-MM-DD)：");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String start = in.next();
		String end = in.next();
		Date date = sdf.parse(start);
		Date end_date = sdf.parse(end);
		java.sql.Date date1 = new java.sql.Date(date.getTime());
		java.sql.Date date2 = new java.sql.Date(end_date.getTime());
		Selectsum dao = new Selectsum(con);
		try {
			dao.selectBetweenDateS(con, date1, date2);
			dao.selectBetweenDateT(con, date1, date2);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.closeCon(con);
		}
	}

	/**
	 * 教师每日确诊情况
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void TeacherCheckInfo() throws SQLException, Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		JFrame frame = new JFrame("柱状图");
		frame.add(new UserDao1().getChartPanel()); // 添加柱形图
		frame.setBounds(50, 50, 800, 600);
		frame.setVisible(true);

	}

	/**
	 * 学生每日确诊情况
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void StudentCheckInfo() throws SQLException, Exception {
		// TODO Auto-generated method stub
		Connection con = database.getCon();
		JFrame frame = new JFrame("柱状图");
		frame.add(new UserDao().getChartPanel()); // 添加柱形图
		frame.setBounds(50, 50, 800, 600);
		frame.setVisible(true);

	}

}
