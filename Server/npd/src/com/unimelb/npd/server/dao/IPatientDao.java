/**
 * Copyright     2014     Renren.com
 * @author JunHan 
 *  All rights reserved.
 */
package com.unimelb.npd.server.dao;

import java.util.List;

import com.unimelb.npd.server.vo.Patient;


public interface IPatientDao {
	public abstract Patient checkLogin(final String username);
	public abstract int updatePatient(int pid,String game, int level);
	public abstract int addPatient(Patient patient);
	public abstract List<Patient> findAll();
}
