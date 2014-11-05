package com.unimelb.npd.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.unimelb.npd.server.dao.IUserDao;
import com.unimelb.npd.server.dao.impl.UserDaoImpl;
import com.unimelb.npd.server.vo.User;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserLogin() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		IUserDao userDao = new UserDaoImpl();
		User user = new User();
		user = userDao.checkLogin(username, password);
		if(user!=null){
//			ServletContext sc =this.getServletContext();
			Map map = new HashMap();
			map.put("uid", user.getUid());
			map.put("username", user.getUsername());
			map.put("password", user.getPassword());
			map.put("role", user.getRole());
			JSONObject json = JSONObject.fromObject( map ); 
			System.out.println( json );  
			HttpSession session=request.getSession();
			session.setAttribute("login_user", json);
			out.write(json.toString());
		}else{
			out.write("f");
		}
//		response.sendRedirect("index.html");
	}
}
