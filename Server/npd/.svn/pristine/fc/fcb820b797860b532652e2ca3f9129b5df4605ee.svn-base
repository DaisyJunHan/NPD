package com.unimelb.npd.server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.unimelb.npd.server.dao.IPatientDao;
import com.unimelb.npd.server.dao.impl.PatientDaoImpl;
import com.unimelb.npd.server.vo.Patient;

/**
 * Servlet implementation class GetAllPatient
 */
@WebServlet("/GetAllPatient")
public class GetAllPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllPatient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		JSONObject login = (JSONObject) request.getSession().getAttribute("login_user");
		JSONArray jsonArray = new JSONArray(); 
		if(login!=null){
			IPatientDao patientDao = new PatientDaoImpl();
			List<Patient> lst = new ArrayList<Patient>();
			lst = patientDao.findAll();
			System.out.println(lst.size());
			jsonArray = JSONArray.fromObject( lst );  
			System.out.println(jsonArray);
		}
		response.getWriter().write(jsonArray.toString());
	}

}
