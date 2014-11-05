package com.unimelb.npd.games.balloon;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class BallGroupView {
	//int ball_count = Constant.BALLCOUNT;
	//BallView[] ballview = new BallView[ball_count];
	//private OnDrawThread odt;
	//private PicRunThread prt;
	GameSurfaceView game_sv;
	public BallGroupView (GameSurfaceView game_sv) {
		this.game_sv = game_sv;

	}

	public void drawSelf(GL10 gl, Context context, Canvas canvas, Paint paint) {

		for (int i=0;i<game_sv.ballList.size();i++){
			BallView ballview = (BallView)game_sv.ballList.get(i);
			ballview.drawSelf(gl, context, canvas, paint);
		}
	}
	
/*public boolean onTouchEvent(MotionEvent event) { 
		
		
        int CurX = (int) event.getX();
        int CurY = (int) event.getY();
        
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
        	if(game_sv.score<Constant.WINSCORE){
        		
        	for(int i=0;i<game_sv.ballList.size();i++){
        		BallView ballview = (BallView)game_sv.ballList.get(i);
        		int x = (int) ballview.picX;
            	int y = (int) ballview.picY;
            //	Log.d("x&y", "x="+x+" y="+y);
            	if((CurX>=x)&& (CurX<=x+150)&&(CurY>=y)&&(CurY<=y+150)){
            		ballview.Pic = R.drawable.explode;
            		ballview.burstFlag = 1; //burst
            		
            		game_sv.ballList.set(i, ballview);
            		if(ballview.color == game_sv.aimColor_int){
            		game_sv.score = game_sv.score+2;
            		}else
            			game_sv.score = game_sv.score-1;
            		return true;
            	}
        	}
        	}
        	else {
        		game_sv.balloongame.LEVEL += 1;
        		game_sv.aimColor_int = new Random().nextInt(game_sv.balloongame.LEVEL)+1;
        		game_sv.aimColor_str = game_sv.aimColor_IntToString(game_sv.aimColor_int); 
        		 
        		
        	}
        default: return false;	
        }
	}
*/

}
