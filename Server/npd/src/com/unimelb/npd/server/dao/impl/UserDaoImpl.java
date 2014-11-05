/**
 * Copyright     2014     Renren.com
 * @author JunHan 
 *  All rights reserved.
 */
package com.unimelb.npd.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.unimelb.npd.server.dao.IUserDao;
import com.unimelb.npd.server.dbutil.DBConn;
import com.unimelb.npd.server.vo.User;


public class UserDaoImpl implements IUserDao {
	private DBConn dbconn = null;

	public UserDaoImpl() {
		this.dbconn = new DBConn();
	}
	@Override
	public User checkLogin(String username, String password) {
		User user = new User();
		String strSQL = "select * from npd.user where username = ? and password =?";
		ResultSet rs = this.dbconn.execQuery(strSQL, new Object[]{username,password});
			try {
				if(rs.next()){
				user.setUid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRole(rs.getString(4));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				dbconn.closeConn();
			}
		return user;
	}

}
