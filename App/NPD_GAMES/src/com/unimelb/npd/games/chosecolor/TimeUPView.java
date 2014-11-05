package com.unimelb.npd.games.chosecolor;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class TimeUPView {
	
	ChoseColorView ccv;
	public TimeUPView(ChoseColorView ccv) {
		this.ccv = ccv;
	}

	public void drawTimeup(Canvas canvas, Paint paint) {
		paint.setColor(Color.rgb(255, 69, 0));
		canvas.drawColor(Color.rgb(255,250,205));
		
		paint.setTextSize(ccv.choseColor.SCREENHEIGHT/16);
		paint.setStrokeWidth(ccv.choseColor.SCREENHEIGHT/10);
		String text1 = "Time's Up!";
		canvas.drawText(text1, ccv.choseColor.SCREENWIDTH/2, ccv.choseColor.SCREENHEIGHT/10*4, paint);
		String text2 = "Your Score is "+ccv.score;
		canvas.drawText(text2, ccv.choseColor.SCREENWIDTH/2, ccv.choseColor.SCREENHEIGHT/10*5, paint);
		//if(ccv.score>ccv.choseColor.patient.chosecolor_score)
		if(ccv.score>100){
			String text4 = "Congratulation! It's a new high score!";
			canvas.drawText(text4, ccv.choseColor.SCREENWIDTH/2, ccv.choseColor.SCREENHEIGHT/10*6, paint);
		}
		String text3 = "Touch Screen to try again!";
		paint.setTextSize(ccv.choseColor.SCREENHEIGHT/18);
		canvas.drawText(text3, ccv.choseColor.SCREENWIDTH/2, ccv.choseColor.SCREENHEIGHT/10*7, paint);
	}
	
	
}
