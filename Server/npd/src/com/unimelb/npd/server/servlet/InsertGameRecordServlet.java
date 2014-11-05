package com.unimelb.npd.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unimelb.npd.server.dao.IGameDao;
import com.unimelb.npd.server.dao.IPatientDao;
import com.unimelb.npd.server.dao.impl.GameDaoImpl;
import com.unimelb.npd.server.dao.impl.PatientDaoImpl;
import com.unimelb.npd.server.vo.Game;

/**
 * Servlet implementation class InsertGameRecord
 */
@WebServlet("/InsertGameRecordServlet")
public class InsertGameRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertGameRecordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		IGameDao gameDao = new  GameDaoImpl();
		IPatientDao patientDao = new PatientDaoImpl();
		int  pid = Integer.parseInt(request.getParameter("pid").toString());
		int  gid = Integer.parseInt(request.getParameter("gid").toString());
		int  level = Integer.parseInt(request.getParameter("level").toString());
		int  time = Integer.parseInt(request.getParameter("time").toString());
		int  score = Integer.parseInt(request.getParameter("score").toString());
		String date = request.getParameter("date").toString();
		int  percent = Integer.parseInt(request.getParameter("percent").toString());
		int  accuracy = Integer.parseInt(request.getParameter("accuracy").toString());
//		String ext1 = request.getParameter("ext1").toString();
//		String ext2 = request.getParameter("ext2").toString();
//		String ext3 = request.getParameter("ext3").toString();
		String col = null;
		switch(gid){
		case 1:
			col = "pipe_level";
			break;
		case 2:
			col = "ball_level";
			break;
		case 3:
			col = "balloon_level";
			break;
		case 4:
			col = "breakout_level";
			break;
		case 5:
			col = "poker_level";
			break;
		case 6:
			col = "color_level";
			break;
		}
		String result = null;
		int effect = patientDao.updatePatient(pid, col, level);
		result = effect>0?"UpdatePatient":"F";
		
		Game game = new Game();
		game.setGid(gid);
		game.setPid(pid);
		game.setLevel(level);
		game.setScore(score);
		game.setTime(time);
		game.setAccuracy(accuracy);
		game.setDate(date);
		game.setPercent(percent);
//		game.setExt1(ext1);
//		game.setExt2(ext2);
//		game.setExt3(ext3);
		//int record = gameDao.checkGameRecord(gid, pid, level);
	//	if(record!=-1){
			//update
		//	int update = gameDao.updateGame(game, record);
		//	result += update>0?"UpdateGame":"F";
	//	}else{
			//insert
			int insert = gameDao.addGame(game);
			result += insert>0?"AddGame":"F";
		//}
		response.getOutputStream().write(result.getBytes());
	}

}
