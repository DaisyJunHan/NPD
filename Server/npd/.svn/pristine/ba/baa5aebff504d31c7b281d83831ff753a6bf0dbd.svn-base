package com.unimelb.npd.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unimelb.npd.server.dao.IPatientDao;
import com.unimelb.npd.server.dao.impl.PatientDaoImpl;
import com.unimelb.npd.server.vo.Patient;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username").toString();
		String strAge = request.getParameter("age").toString();
		int age = strAge==null?0:Integer.parseInt(strAge);
		IPatientDao userDao = new PatientDaoImpl();
		Patient user = new Patient();
		user.setPatient_name(username);
		user.setAge(age);
		user.setBall_level(1);
		user.setBalloon_level(1);
		user.setBreakout_level(1);
		user.setColor_level(1);
		user.setPipe_level(1);
		user.setPoker_level(1);
		int r = userDao.addPatient(user);
		String result = r>0?"s":"f";
		response.getWriter().write(result);
	}

}
