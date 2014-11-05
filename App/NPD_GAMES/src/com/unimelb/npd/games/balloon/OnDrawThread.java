package com.unimelb.npd.games.balloon;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

@SuppressLint("WrongCall")
public class OnDrawThread extends Thread{
	GameSurfaceView game_sv;
	SurfaceHolder sh;
	BallGroupView ballgroupview;
	
	public boolean isrunning;
	
	public OnDrawThread(GameSurfaceView game_sv){
		super();
		this.game_sv = game_sv;
		isrunning = false;
		ballgroupview = game_sv.ballgroupview;
		sh = game_sv.getHolder();
	}
	@Override
	public void run() {
		super.run();
		Canvas canvas = null;
		
		//while(isrunning) {
		while(!Thread.interrupted() && isrunning){
			try {
				canvas = sh.lockCanvas(null);
				synchronized (this.sh) {
					if(canvas != null) {
						//change rule
						//if(game_sv.score<Constant.WINSCORE){
						if(game_sv.nextlevel == false){
							//end
							if( game_sv.gameover == false)
								game_sv.onDraw(canvas);
							else
								game_sv.drawGameOver(canvas);
						}
							
						else{
							game_sv.drawNextLevel(canvas);
						}
					}
				}
			} finally {
				try {
					if (sh!=null) {
						sh.unlockCanvasAndPost(canvas);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(Constant.ONDRAWSPEED);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		

		
	}
	
}
