package com.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Score;
import com.bean.StudentBean;
import com.dao.Register;
import com.util.JDBCProperties;

public class RegisterImp implements Register{
	
	public boolean register(StudentBean student) {
		//申明布尔值
		boolean result=false;
		//申明连接对象
		Connection con=null;
		//申明预编译语句
		PreparedStatement ps=null;
		//申明增加学生信息sql语句
		String sql="insert into student(stuId,stuName,stuSex,stuAge,stuEmail,stuAddress)values(?,?,?,?,?,?)";
		//得到连接对象
		con=JDBCProperties.getConnection();
		//申明结果集
		ResultSet rs=null;
		try {
			//得到预编译语句
			ps=con.prepareStatement(sql);
			//设置参数
			ps.setString(1, student.getStuId());
			ps.setString(2,student.getStuName());
			ps.setString(3, student.getStuSex());
			ps.setInt(4, student.getStuAge());
			ps.setString(5, student.getStuEmail());
			ps.setString(6, student.getStuAddress());
			result=ps.executeUpdate()>0;
			//申明增加学生成绩信息sql语句
			String sql1="insert into scores(stuId,score,subjectId)values(?,?,?)";
			//申明查询科目表的sql语句
			String sql2="select subjectId from subjects where subjectName=?";
			//申明科目编号
			int subjectId=0;
			//得到学员成绩
			List<Score>scores=student.getScores();
			for (int i = 0; i < scores.size(); i++) {
				//提交查询科目表sql语句
				ps=con.prepareStatement(sql2);
				//设置参数
				ps.setString(1, scores.get(i).getSubjectName());
				//根据科目名得到科目编号
				rs=ps.executeQuery();
				if(rs.next()){
					//得到科目编号
					subjectId=rs.getInt("subjectId");
				}
				ps=con.prepareStatement(sql1);
				ps.setString(1, student.getStuId());
				ps.setInt(2, scores.get(i).getScore());
				ps.setInt(3, subjectId);
				result=ps.executeUpdate()>0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCProperties.close(con, ps, rs);
		}
		return result;
	}
	/*public static void main(String[] args) {
		List<Score>scores=new ArrayList<Score>();
		scores.add(new Score("html",60));
		scores.add(new Score("java基础",70));
		scores.add(new Score("javaOOP",80));
		scores.add(new Score("sqlServer",90));
		if(new RegisterImp().register(new StudentBean("43092119","李某人", "男", 18, "myEmail", "myAddress", scores))){
			System.out.println("ok");
		}
	}*/
}
