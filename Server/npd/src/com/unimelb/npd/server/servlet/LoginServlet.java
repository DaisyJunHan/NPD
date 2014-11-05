package com.unimelb.npd.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unimelb.npd.server.dao.IPatientDao;
import com.unimelb.npd.server.dao.impl.PatientDaoImpl;
import com.unimelb.npd.server.tools.SerializableObj;
import com.unimelb.npd.server.vo.Patient;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username").toString();
		IPatientDao userDao = new PatientDaoImpl();
		Patient user = new Patient();
		user  = userDao.checkLogin(username);
//		HttpSession hs = request.getSession();
//		if(user!=null){
//		hs.setAttribute("login", user.getUid());
//		hs.setAttribute("username", user.getUsername());
//		hs.setAttribute("score", user.getScore());
//		response.getWriter().write("Welcome, "+user.getUsername()+".<br>Your highest score is: "+user.getScore());  
		
//		System.out.print(user.getScore());
		SerializableObj so = new SerializableObj();
		
		response.getOutputStream().write(so.User2Bytes(user));
//		}else{
////		hs.setAttribute("login", 0);
//		response.getOutputStream().write(null);
//		}
	}

}
