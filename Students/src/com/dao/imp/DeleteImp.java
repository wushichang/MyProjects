package com.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.StudentBean;
import com.dao.Delete;
import com.util.JDBCProperties;

public class DeleteImp implements Delete{
	@Override
	public boolean deleteStudent(StudentBean student) {
		//申明布尔值
		boolean result=false;
		//申明连接对象
		Connection con=null;
		//申明预编译语句
		PreparedStatement ps=null;
		//申明sql语句
		String sql="select stuId from student where stuName=?";
		//得到连接
		con=JDBCProperties.getConnection();
		//申明结果集
		ResultSet rs=null;
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, student.getStuName());
			rs=ps.executeQuery();
			//申明学生编号变量
			String stuId="";
			if(rs.next()) {
				stuId=rs.getString("stuId");
			}
			 //申明删除学生成绩sql语句
			String sql1="delete from scores where stuId=?";
			ps=con.prepareStatement(sql1);
			ps.setString(1, stuId);
			result=ps.executeUpdate()>0;
			//申明删除学生信息的sql语句
			String sql2="delete from student where stuId=?";
			ps=con.prepareStatement(sql2);
			ps.setString(1, stuId);
			result=ps.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCProperties.close(con, ps, rs);
		}
		
		return result;
	}
	/*public static void main(String[] args) {
		new DeleteImp().deleteStudent(new StudentBean("李某人"));
	}*/
}