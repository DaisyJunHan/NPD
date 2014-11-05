package com.unimelb.npd.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.unimelb.npd.server.dao.IGameDao;
import com.unimelb.npd.server.dao.impl.GameDaoImpl;


@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GameServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String pid = request.getParameter("pid").toString();
		int gid = Integer.parseInt(request.getParameter("gid").toString());
		
		IGameDao gameDao = new GameDaoImpl();
		JSONArray json = pid.equals("all")?gameDao.findRecordByGid(gid):gameDao.findGameRecord(gid, pid);
		
		response.getWriter().write(json.toString());
	}

}
