package com.nwmu.dao;



import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.nwnu.serivce.impl.Studentinfo;
import com.nwnu.test.database;

import javafx.scene.chart.CategoryAxis;



public class UserDao {
	ChartPanel frame1;

	/**
	 * 创建柱状图
	 * 
	 * @throws Exception
	 */

	public UserDao() throws Exception {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D(
				// 图表标题
				"每日学生确诊情况",
				// 文件夹轴的显示标签
				"性别",
				// 数值轴的显示标签
				"数量",
				// 数据集
				dataset,
				// 图表方向
				PlotOrientation.VERTICAL,
				// 是否显示图例
				true,
				// 是否生成工具
				false,
				// 是否生成URL链接
				false);

		// 获取图表区域对象
		CategoryPlot plot = chart.getCategoryPlot();
		// 水平底部列表
		CategoryAxis domainAxis = plot.getDomainAxis();
		// 水平底部标题
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14));
		// 垂直标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));
		// 获取柱状
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		// 设置标题字体
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));

		frame1 = new ChartPanel(chart, true);

	}

	/**
	 * 使用查询数据库的数据
	 * 
	 * @return 数据集
	 * @throws Exception
	 */
	private static CategoryDataset getDataSet() throws Exception {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		java.util.List<Studentinfo> list = CheckStudent();
		// 装成JFreeChart需要的数据集
		for (Studentinfo studentinfo : list) {
			// 日期形式显示为月-日型
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
			String str = sdf.format(studentinfo.getDate());
			dataset.addValue(studentinfo.getChecknum(), studentinfo.getSex(), str);
		}
		return (CategoryDataset) dataset;
	}

	/**
	 * 产生进行做柱状图的结果表
	 * 
	 * @return list 查询结果
	 * @throws SQLException
	 * @throws Exception
	 */
	public static java.util.List<Studentinfo> CheckStudent() throws SQLException, Exception {
		Connection con = null;
		con = database.getCon();
		java.util.List<Studentinfo> list = new ArrayList<Studentinfo>();
		try {
			String sql = "select date,stu_sex,sum(check1='Y') from (select date,stu_sex,check1 from student s left join stu_info s1 on s.student_id = s1.s_id ) a group by date,stu_sex ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(new Studentinfo(rs.getDate(1), rs.getString(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;

	}

	public ChartPanel getChartPanel() {
		return frame1;

	}

}
