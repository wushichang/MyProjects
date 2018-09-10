package com.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Score;
import com.bean.StudentBean;
import com.dao.Login;
import com.util.JDBCProperties;

public class LoginImp implements Login{
	@Override
	public List<StudentBean> select(StudentBean student) {
		//申明连接对象
		Connection con=null;
		//申明预编译语句
		PreparedStatement ps=null;
		//申明sql 语句
		String sql="select * from student where stuName=?";
		//申明结果集
		ResultSet rs=null;
		//得到连接
		con=JDBCProperties.getConnection();
		//申明聚合得到学生成绩
		List<StudentBean> list=new ArrayList<StudentBean>();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, student.getStuName());
			//提交预编译语句得到结果集
			rs=ps.executeQuery();
			while(rs.next()){
				StudentBean stu=new StudentBean(rs.getString("stuId"), rs.getString("stuName"), rs.getString("stuSex"), rs.getInt("stuAge"), rs.getString("stuEmail"), rs.getString("stuAddress"), null);
				//申明查询学生成绩的sql语句
				String sql1="select subjects.subjectName,scores.score"
						+ " from student,scores,subjects"
						+ " where student.stuId=scores.stuId"
						+ " and scores.subjectId=subjects.subjectId"
						+ " and student.stuId=?";
				//得到预编译语句
				ps=con.prepareStatement(sql1);
				//设置参数
				ps.setString(1, stu.getStuId());
				//执行预编译语句,得到结果集
				ResultSet rs1=ps.executeQuery();
				//申明数组存储学生的成绩
				List<Score>scores=new ArrayList<Score>();
				while(rs1.next()){
					//取出学生每门科目的名称及分数
					scores.add(new Score(rs1.getString("subjectName"), rs1.getInt("score")));
				}
				//将分数集合存入学生对象
				stu.setScores(scores);
				//将学生对象存入集合中
				list.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			 JDBCProperties.close(con, ps, rs);
		}
		return list;
	}
	/*public static void main(String[] args) {
		List<StudentBean> list=new LoginImp().select(new StudentBean("李某人"));
		for (StudentBean studentBean : list) {
			System.out.println(studentBean.getStuName());
			List<Score> scores=studentBean.getScores();
			//输出每位同学的集合的大小
			//System.out.println(scores.size());
			for (Score score : scores) {
				System.out.println("科目:\t"+score.getSubjectName()+"\t+分数:\t"+score.getScore());
			}
		}
	}*/
}
