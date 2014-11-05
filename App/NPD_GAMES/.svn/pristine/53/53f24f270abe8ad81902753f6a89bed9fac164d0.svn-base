package com.unimelb.npd.games.balloon;

import android.util.Log;

public class PicRunThread extends Thread {
	GameSurfaceView game_sv;
	// BalloonGame ballgame = new BalloonGame();
	BallView ballview;

	int picAlphaNum = 255;
	
	//private float picY;
	private Boolean runflag = true;
	public Boolean isrunning;

	public PicRunThread(GameSurfaceView game_sv) {
		super();
		isrunning = false;
		this.game_sv = game_sv;

	}

	@Override
	public void run() {
		super.run();
		
		
		int n = 0; //the number of ball poked or go out of screen
		while (runflag) {
			//while(isrunning){
			while(!Thread.interrupted() && isrunning){
			for (int i = 0; i < game_sv.ballList.size(); i++) {
				BallView ballview = (BallView) game_sv.ballList.get(i);
				if (ballview.burstFlag == 1) {
					ballview.stay ++;
					if(ballview.stay == 10)
						ballview.burstFlag = 2;
					game_sv.ballList.set(i, ballview);
				} else if (ballview.burstFlag == 2){
					game_sv.ballList.remove(i);
					n = n+1;
				} else if (ballview.picY < -200){
					if(ballview.color != game_sv.trapColor_int){
						//change rule
					//game_sv.score = game_sv.score - Constant.MISSBALL;
						game_sv.gameover = true;
						//end
					}
					game_sv.ballList.remove(i);
					n = n+1;
					
				} else {
					ballview.picY = ballview.picY - game_sv.balloongame.LEVEL;
					game_sv.ballList.set(i, ballview);
				}
				Log.d(" n ","n="+n);
				
				//change rule
				if(n == Constant.BALLCOUNT*game_sv.balloongame.LEVEL) {
					runflag = false;
					
					game_sv.ballList.clear();
					game_sv.nextlevel = true;
				}
				
				/*if(game_sv.score>=Constant.WINSCORE){
					runflag = false;
					//game_sv.balloongame.LEVEL += 1;
					game_sv.ballList.clear();
					//Log.d("list size", "size="+game_sv.ballList.size());
				} else if(n == ballview.ballgroupview.ball_count) {
					game_sv.gameover = true;
				}*/
			}
			if (game_sv.ballList == null)
				runflag = false;
			try {
				Thread.sleep(Constant.PICRUNSPEED);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	}

}
