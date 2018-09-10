package com.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Score;
import com.bean.StudentBean;
import com.dao.imp.RegisterImp;

/**
 * Servlet implementation class RServlet
 */
public class RServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//得到学生成绩集合
		List<Score> scores=new ArrayList<Score>();
		scores.add(new Score("html",Integer.parseInt(request.getParameter("html"))));
		scores.add(new Score("java基础",Integer.parseInt(request.getParameter("java基础"))));
		scores.add(new Score("sqlServer",Integer.parseInt(request.getParameter("sqlServer"))));
		scores.add(new Score("javaOOP",Integer.parseInt(request.getParameter("javaOOP"))));
		//存储学生信息
		boolean result=new RegisterImp().register(new StudentBean(request.getParameter("stuId"), request.getParameter("stuName"),request.getParameter("stuSex"), Integer.parseInt(request.getParameter("stuAge")),request.getParameter("stuEmail"), request.getParameter("stuAddress"), scores));
		
	}

}
