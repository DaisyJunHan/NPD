/**
 * Copyright     2014     Renren.com
 * @author JunHan 
 *  All rights reserved.
 */
package com.unimelb.npd.server.dao;

import net.sf.json.JSONArray;

import com.unimelb.npd.server.vo.Game;


public interface IGameDao {
	public abstract int addGame(Game game);
	public abstract int updateGame(Game game,int rid);
	public abstract JSONArray findGameRecord(int gid, String pid);
	public abstract JSONArray findRecordByGid(int gid);
	public abstract int checkGameRecord(int gid, int pid,int level);
}
