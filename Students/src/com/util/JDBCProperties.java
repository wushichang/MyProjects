package com.util;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class JDBCProperties {
	public static Connection getConnection(){
		//申明连接对象
		Connection con=null;
		//得到文件工具类
	Properties pro=new Properties();
		//得到数据流
		try {
			pro.load(new FileReader(JDBCProperties.class.getClassLoader().getResource("dbcp.properties").getPath()));
			DataSource dataSource=BasicDataSourceFactory.createDataSource(pro);
			con=dataSource.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con,PreparedStatement ps,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(con!=null){
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/*public static void main(String[] args) {
		if(getConnection()!=null){
			System.out.println("ok");
		}
	}*/

}
