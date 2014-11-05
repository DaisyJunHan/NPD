package com.unimelb.npd.games.balloon;

import java.util.Random;

import com.unimelb.npd.games.R;


public class CreateBallThread extends Thread{

	GameSurfaceView game_sv;
	//SurfaceHolder sh;
	BallGroupView ballgroupview; 
	PicRunThread prt = new PicRunThread(game_sv);
	
	public boolean isrunning;
	public CreateBallThread(GameSurfaceView game_sv, BallGroupView ballgroupview){
		this.game_sv = game_sv;
		isrunning = false;
		this.ballgroupview = ballgroupview;
		
	}
	@Override
	public void run(){
		
		while(!Thread.interrupted() && isrunning){
			
		super.run();
		int count = Constant.BALLCOUNT*game_sv.balloongame.LEVEL;
		for(int i=0;i<count;i++){
			BallView ballview = new BallView(game_sv.ballgroupview, game_sv);
			//ballview.LEVEL = game_sv.balloongame.LEVEL;
			ballview.color = new Random().nextInt(game_sv.balloongame.LEVEL)+1;
			ballview.picX = (int) (Math.random()*(game_sv.balloongame.SCREENWIDTH/8*7));
			ballview.picY = game_sv.balloongame.SCREENHEIGHT;
			switch(ballview.color) {
			case 1: ballview.Pic = R.drawable.b_red;
			break;
			case 2: ballview.Pic = R.drawable.b_green;
			break;
			case 3: ballview.Pic = R.drawable.b_orange;
			break;
			case 4: ballview.Pic = R.drawable.b_pink;
			break;
			case 5: ballview.Pic = R.drawable.b_blue;
			break;
			case 6: ballview.Pic = R.drawable.b_yellow;
			}
			game_sv.addBallView(ballview);
			try {
				Thread.sleep((long) (Math.random()*200*(10-game_sv.balloongame.LEVEL)+1500));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//
		}
		}
	}

}
