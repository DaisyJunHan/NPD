package com.unimelb.npd.games.chosecolor;

import java.util.Random;

import android.R;
import android.R.string;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

public class ChoseColorView extends SurfaceView implements SurfaceHolder.Callback {

	Paint paint;
	ChoseColor choseColor;
	Canvas canvas;
	OnDrawThread odt;
	SurfaceHolder sh;
	int[] myColor = { Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.GRAY };
	String[] words = { "BLACK", "BLUE", "GREEN", "RED", "YELLOW", "GRAY" };
	int inkColor = new Random().nextInt(myColor.length);
	int aim = new Random().nextInt() % 2;
	int word = new Random().nextInt(myColor.length);
	// int nextDo; //0 nothing, 1 new pic, 2 game over
	int score = 0;
	int time = 10;

	// DrawTitle drawTitle;

	public ChoseColorView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		this.choseColor = (ChoseColor) context;
		this.getHolder().addCallback(this);
		odt = new OnDrawThread(this);

	}

	public void Draw() {
		// nextDo = 0;
		SurfaceHolder holder = this.getHolder();
		canvas = holder.lockCanvas();
		Paint paint = new Paint();
		try {
			synchronized (holder) {
				myDraw(canvas, paint);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	protected void myDraw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		canvas.drawColor(Color.rgb(255, 250, 205));

		DrawPic(canvas, paint);
		DrawTitle(canvas, paint);
		DrawText(canvas, paint);
		DrawMesh(canvas, paint);

	}

	public void DrawPic(Canvas cavas, Paint paint) {
		// roundRect
		// inkColor = new Random().nextInt(myColor.length);
		paint.setColor(myColor[inkColor]);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(choseColor.SCREENHEIGHT/28);
		float R_left = choseColor.SCREENWIDTH / 6;
		float R_right = choseColor.SCREENWIDTH / 6 * 5;
		float R_top = choseColor.SCREENHEIGHT / 10 * 2;
		float R_bottom = choseColor.SCREENHEIGHT / 10 * 5;
		RectF rectf = new RectF(R_left, R_top, R_right, R_bottom);
		canvas.drawRoundRect(rectf, choseColor.SCREENHEIGHT/40, choseColor.SCREENHEIGHT/40, paint);
		// Text
		// word = new Random().nextInt(myColor.length);
		if (word == inkColor)
			word = (word + 2) % myColor.length;
		// int W_left = choseColor.SCREENWIDTH/
		// paint.setStyle(Style.FILL);
		paint.setStrokeWidth(choseColor.SCREENHEIGHT/72);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(choseColor.SCREENHEIGHT / 10);
		// paint.setTypeface(Typeface.DEFAULT_BOLD);

		canvas.drawText(words[word], rectf.centerX(), rectf.centerY() + choseColor.SCREENHEIGHT / 20, paint);

	}

	public void DrawTitle(final Canvas canvas, final Paint paint) {
		// score
		paint.setColor(Color.rgb(255, 69, 0));
		paint.setStrokeWidth(choseColor.SCREENHEIGHT/10);
		paint.setStyle(Style.FILL);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setTextSize(choseColor.SCREENHEIGHT / 24);
		canvas.drawText("score: " + score, choseColor.SCREENWIDTH / 8, choseColor.SCREENHEIGHT / 20 * 2, paint);
		// time
		// int perScore = ((int)Math.floor(score/100)+1)*10;
		canvas.drawText("Time: " + time + "s", choseColor.SCREENWIDTH / 8 * 5, choseColor.SCREENHEIGHT / 20 * 2, paint);

	}

	public void DrawTime(Canvas canvas, Paint paint) {

	}

	public void DrawText(Canvas canvas, Paint paint) {
		paint.setColor(Color.rgb(255, 69, 0));
		// aim = new Random().nextInt()%2;
		String aimString;
		if (aim == 0)
			aimString = "color of the INK";
		else
			aimString = "meaning of the WORD";
		String text = "Choose the " + aimString;
		paint.setStrokeWidth(choseColor.SCREENHEIGHT/20);
		paint.setStyle(Style.FILL);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(choseColor.SCREENHEIGHT / 24);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(text, choseColor.SCREENWIDTH / 2, choseColor.SCREENHEIGHT / 10 * 7, paint);
	}

	public void DrawMesh(Canvas canvas, Paint paint) {
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 3; j++) {
				paint.setColor(myColor[i * 3 + j]);
				paint.setStyle(Style.FILL);
				float M_left = choseColor.SCREENWIDTH / 3 * j + 2;
				float M_top = choseColor.SCREENHEIGHT / 10 * (8 + i);
				float M_right = choseColor.SCREENWIDTH / 3 * (j + 1) - 2;
				float M_bottom = choseColor.SCREENHEIGHT / 10 * (9 + i) - 2;
				RectF rectf = new RectF(M_left, M_top, M_right, M_bottom);
				canvas.drawRect(rectf, paint);
			}
	}


	/*
	 * public void drawGameOver(Canvas canvas) { String text; //Canvas canvas =
	 * new Canvas(); Paint paint = new Paint(); paint.setColor(Color.rgb(255,
	 * 69, 0)); paint.setStrokeWidth(12); paint.setStyle(Style.FILL);
	 * paint.setTextAlign(Paint.Align.CENTER);
	 * paint.setTextSize(choseColor.SCREENHEIGHT/24);
	 * paint.setTextAlign(Paint.Align.CENTER); if(score <= 100) { text =
	 * "Your Score is "+score; } else { text = "Congratulations! "+score+
	 * " is your new highest score!"; } canvas.drawText(text,
	 * choseColor.SCREENWIDTH/2, choseColor.SCREENHEIGHT/10*7, paint); }
	 */
	/*
	 * public void drawTimeup(Canvas canvas, Paint paint) {
	 * paint.setColor(Color.rgb(255, 69, 0));
	 * canvas.drawColor(Color.rgb(255,250,205)); paint.setStrokeWidth(30);
	 * paint.setTextSize(choseColor.SCREENHEIGHT/16); String text =
	 * "Time's Up!"; canvas.drawText(text, choseColor.SCREENWIDTH/2,
	 * choseColor.SCREENHEIGHT/10*7, paint); }
	 */

	public boolean onTouchEvent(MotionEvent event) {
		int CurX = (int) event.getX();
		int CurY = (int) event.getY();
		int meshTouched = 6;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (time >= 0) {
				if (CurY > choseColor.SCREENHEIGHT / 10 * 8) {
					if (CurY > choseColor.SCREENHEIGHT / 10 * 8 && CurY < choseColor.SCREENHEIGHT / 10 * 9) {
						if (CurX > 0 && CurX < choseColor.SCREENWIDTH / 3 - 2)
							meshTouched = 0;
						else if (CurX > choseColor.SCREENWIDTH / 3 + 2 && CurX < choseColor.SCREENWIDTH / 3 * 2 - 2)
							meshTouched = 1;
						else if (CurX > choseColor.SCREENWIDTH / 3 * 2 + 2 && CurX < choseColor.SCREENWIDTH - 2)
							meshTouched = 2;
					} else if (CurY > choseColor.SCREENHEIGHT / 10 * 9 && CurY < choseColor.SCREENHEIGHT - 2) {
						if (CurX > 0 && CurX < choseColor.SCREENWIDTH / 3 - 2)
							meshTouched = 3;
						else if (CurX > choseColor.SCREENWIDTH / 3 + 2 && CurX < choseColor.SCREENWIDTH / 3 * 2 - 2)
							meshTouched = 4;
						else if (CurX > choseColor.SCREENWIDTH / 3 * 2 + 2 && CurX < choseColor.SCREENWIDTH - 2)
							meshTouched = 5;
					}
					int perScore = ((int) Math.floor(score / 100) + 1) * 10;
					if (aim == 0) {

						if (meshTouched == inkColor) {
							// nextDo = 1;
							score = score + perScore;
							if(score < 900)
								time = 10 - (int) Math.floor(score / 100);
							else
								time = 2;
							inkColor = new Random().nextInt(myColor.length);
							aim = new Random().nextInt() % 2;
							word = new Random().nextInt(myColor.length);

							// Draw();
						} else {
							// nextDo = 2;
							// odt.runFlag = 2;
							// drawGameOver(canvas);
							choseColor.gameOver();
						}
					} else {
						if (meshTouched == word) {
							// nextDo = 1;
							score = score + perScore;
							if(time > 2)
								time = 10 - (int) Math.floor(score / 100);
							else time = 2;
							// Draw();
							inkColor = new Random().nextInt(myColor.length);
							aim = new Random().nextInt() % 2;
							word = new Random().nextInt(myColor.length);
						} else {
							// nextDo = 2;
							// odt.runFlag = 2;
							// drawGameOver(canvas);
							choseColor.gameOver();
						}
					}
					return true;
				}
			} else {
				choseColor.gameOver();
			}
		default:
			return false;
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Draw();
		odt.start();
		/*
		 * while(true) { if(odt.runFlag == 2) choseColor.gameOver(); }
		 */}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

}
