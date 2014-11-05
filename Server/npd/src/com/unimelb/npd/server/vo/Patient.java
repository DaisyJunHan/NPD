/**
 * Copyright     2014     Renren.com
 * @author JunHan 
 *  All rights reserved.
 */
package com.unimelb.npd.server.vo;

import java.io.Serializable;


public class Patient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pid;
	private String patient_name;
	private int age;
	private int pipe_level;
	private int ball_level;
	private int balloon_level;
	private int breakout_level;
	private int poker_level;
	private int color_level;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPipe_level() {
		return pipe_level;
	}
	public void setPipe_level(int pipe_level) {
		this.pipe_level = pipe_level;
	}
	public int getBall_level() {
		return ball_level;
	}
	public void setBall_level(int ball_level) {
		this.ball_level = ball_level;
	}
	public int getBalloon_level() {
		return balloon_level;
	}
	public void setBalloon_level(int balloon_level) {
		this.balloon_level = balloon_level;
	}
	public int getBreakout_level() {
		return breakout_level;
	}
	public void setBreakout_level(int breakout_level) {
		this.breakout_level = breakout_level;
	}
	public int getPoker_level() {
		return poker_level;
	}
	public void setPoker_level(int poker_level) {
		this.poker_level = poker_level;
	}
	public int getColor_level() {
		return color_level;
	}
	public void setColor_level(int color_level) {
		this.color_level = color_level;
	}
	
	
	
	

}
