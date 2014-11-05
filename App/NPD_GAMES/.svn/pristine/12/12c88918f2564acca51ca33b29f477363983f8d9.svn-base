package com.unimelb.npd.games.balloon;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BallView{
	GameSurfaceView game_sv;
	BallGroupView ballgroupview;
	BalloonGame balloongame;

	int picX;
	int picY;
	int Pic;
	int burstFlag = 0; // not burst
	int stay = 0;
	//int LEVEL;
	int color;
	
	
	public BallView (BallGroupView ballgroupview, GameSurfaceView game_sv){
		this.ballgroupview = ballgroupview;
		this.game_sv = game_sv;
		
	}

	public void drawSelf(GL10 gl, Context context, Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
	
		Bitmap bitmapBall = BitmapFactory.decodeResource(context.getResources(), Pic);
		int width = bitmapBall.getWidth();
		int heigth = bitmapBall.getHeight();
		int newwidth = game_sv.balloongame.SCREENWIDTH/8;
		int newheigth = game_sv.balloongame.SCREENHEIGHT/9;
		float scaleW = ((float)newwidth)/width;
		float scaleH = ((float)newheigth)/heigth;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleW, scaleH);
		
		bitmapBall = Bitmap.createBitmap(bitmapBall, 0, 0, width, heigth, matrix, true);
		int x = (int) this.picX;
		int y = (int) this.picY;
		//Log.d("xy", "x = "+x+" y = "+y);
		canvas.drawBitmap(bitmapBall, x, y, paint);
		
	}


	

}
