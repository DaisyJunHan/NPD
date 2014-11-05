/**
 * Copyright     2014     Renren.com
 * @author JunHan 
 *  All rights reserved.
 */
package com.unimelb.npd.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unimelb.npd.server.dao.IPatientDao;
import com.unimelb.npd.server.dbutil.DBConn;
import com.unimelb.npd.server.vo.Patient;

public class PatientDaoImpl implements IPatientDao {
	private DBConn dbconn = null;

	public PatientDaoImpl() {
		this.dbconn = new DBConn();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.unimelb.mobile.breakout.server.dao.IUserDao#checkLogin(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Patient checkLogin(String username) {
		// TODO Auto-generated method stub
		Patient patient = new Patient();
		String strSQL = "select * from npd.patient where patient_name=?";
		ResultSet rs = this.dbconn.execQuery(strSQL, new Object[]{username});
		try {
			if(rs.next()){
			patient.setPid(rs.getInt(1));
			patient.setPatient_name(rs.getString(2));
			patient.setAge(rs.getInt(3));
			patient.setPipe_level(rs.getInt(4));
			patient.setBall_level(rs.getInt(5));
			patient.setBalloon_level(rs.getInt(6));
			patient.setBreakout_level(rs.getInt(7));
			patient.setPoker_level(rs.getInt(8));
			patient.setColor_level(rs.getInt(9));}
			return patient;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			patient.setPid(0);
			return patient;
		} finally{
			this.dbconn.closeConn();
		}
		
	}

	@Override
	public int updatePatient(int pid, String game, int level) {
		String strSQL = "update npd.patient set " + game +"=? where pid=?";
		int result = this.dbconn.execOther(strSQL, new Object[]{level,pid});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.unimelb.npd.server.dao.IPatientDao#addPatient(com.unimelb.npd.server.vo.Patient)
	 */
	@Override
	public int addPatient(Patient patient) {
		String strSQL = "insert into npd.patient values(null,?,?,?,?,?,?,?,?)";
		String patient_name = patient.getPatient_name();
		int age = patient.getAge();
		int pipe_level = patient.getPipe_level();
		int ball_level = patient.getBall_level();
		int balloon_level = patient.getBalloon_level();
		int breakout_level = patient.getBreakout_level();
		int poker_level = patient.getPoker_level();
		int color_level = patient.getColor_level();
		int result = this.dbconn.execOther(strSQL, new Object[]{patient_name,age,pipe_level,ball_level,balloon_level,breakout_level,poker_level,color_level});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.unimelb.npd.server.dao.IPatientDao#findAll()
	 */
	@Override
	public List<Patient> findAll() {
		String strSQL = "select * from npd.patient";
		ResultSet rs = this.dbconn.execQuery(strSQL, new Object[]{});
		List<Patient> lst = new ArrayList<Patient>();
		try {
			while(rs.next()){
			Patient patient = new Patient();
			patient.setPid(rs.getInt(1));
			patient.setPatient_name(rs.getString(2));
			patient.setAge(rs.getInt(3));
			patient.setPipe_level(rs.getInt(4));
			patient.setBall_level(rs.getInt(5));
			patient.setBalloon_level(rs.getInt(6));
			patient.setBreakout_level(rs.getInt(7));
			patient.setPoker_level(rs.getInt(8));
			patient.setColor_level(rs.getInt(9));
			lst.add(patient);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.dbconn.closeConn();
		}
		return lst;
	}

	/* (non-Javadoc)
	 * @see com.unimelb.npd.server.dao.IPatientDao#updatePatient(int, com.unimelb.npd.server.po.Patient)
	 */
//	@Override
//	public int updatePatient(int pid, Patient patient) {
//		String patient_name = patient.getPatient_name();
//		int age = patient.getAge();
//		int pipe_level = patient.getPipe_level();
//		int ball_level = patient.getBall_level();
//		int balloon_level = patient.getBalloon_level();
//		int breakout_level = patient.getBreakout_level();
//		int poker_level = patient.getPoker_level();
//		int color_level = patient.getColor_level();
//		String strSQL = "update patient set patient_name =?,age=?,pipe_level=?,ball_level=?,balloon_level=?,breakout_level=?,poker_level=?,color_level=? where pid=?";
//		int result = this.dbconn.execOther(strSQL, new Object[]{patient_name,age,pipe_level,ball_level,balloon_level,breakout_level,poker_level,color_level,pid});
//		return result;
//	}

}
