package com.unimelb.npd.games.balanceball;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.OutputStream;

import com.unimelb.npd.games.SendResultThread;
import com.unimelb.npd.server.vo.Game;

public class WorldView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
	private SurfaceHolder surfaceHolder;
	private boolean running = false;
	public Ball ball;
	public boolean onScreen = true;
	public OutputStream outputStream;
	public boolean connected = false;
	public boolean dialogShow = false;
	public boolean gameover = false;
	private float circleR;
	private boolean flag;
	SendResultThread sendResultThread;
	
	private Activity activity;
	private int width;
	private int height;
	private int currtLevel;
	private Thread t;
	private double timeremain;
	private double totalElapsedTime;
	BBallMainActivity father;
	
	public WorldView(Context context, AttributeSet attrs) {
		super(context, attrs);
		activity = (Activity) context;
		this.father = (BBallMainActivity) context;
		AccelerometerSensor aSensor = new AccelerometerSensor(this, context);
		getHolder().addCallback(this);
		setFocusable(true);
	}
	
	@SuppressLint("WrongCall")
	public void run() {
		running = true;
		long previousFrameTime = System.currentTimeMillis();
		while(running) {
			Canvas canvas = null;
			
			try {
				canvas = surfaceHolder.lockCanvas(null);
				synchronized(surfaceHolder) {
					long currentTime = System.currentTimeMillis();
					double elapsedTimeMS = currentTime - previousFrameTime;
					totalElapsedTime += elapsedTimeMS / 1000.00;
					timeremain -= elapsedTimeMS / 1000.00;
					onDraw(canvas);
					ball.onDraw(canvas);
					boundDect();
					timeDect();
					previousFrameTime = currentTime;
				}
        	} finally {
        		if (canvas != null) {
        			surfaceHolder.unlockCanvasAndPost(canvas);
        		}
        	}
			try {
				Thread.sleep(10);
			} catch(Exception e) {}
		}
	}
	
	public void newGame(int level){
		
		if(gameover){
			gameover = false;
		}
		
		circleR = (8-level) * ball.getRadius();
		totalElapsedTime = 0.0;
		timeremain = 20.0;
		flag = false;
		t = new Thread(this);
		t.start();	
	}
	
	public void timeDect(){
		if(timeremain<=0){
			gameover = true;
			running = false;
			
			final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
					getContext());
			
			dialogBuilder.setTitle("Level " +Integer.toString(currtLevel)+" Passed!");
			dialogBuilder.setCancelable(false);
			dialogBuilder.setMessage(String.format("%s %.2f %s", "Total Time:",totalElapsedTime,"seconds"));
			dialogBuilder.setPositiveButton("Next Level",
					new DialogInterface.OnClickListener() {
						// called when "Reset Game" Button is pressed
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialogShow = false;
							ball.setX(width/2);
							ball.setY(height/2);
							ball.setXSpeed(0);
							ball.setYSpeed(0);
							currtLevel++;
							newGame(currtLevel);
						}
					}
					);
			dialogBuilder.setNegativeButton("Retry",
					new DialogInterface.OnClickListener() {
						// called when "Reset Game" Button is pressed
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialogShow = false;
							ball.setX(width/2);
							ball.setY(height/2);
							ball.setXSpeed(0);
							ball.setYSpeed(0);
							newGame(currtLevel);
						}
					}
					);
			activity.runOnUiThread(new Runnable() {
				public void run() {
					dialogShow = true;
					dialogBuilder.show();
				}
			}
			);
			
		}
	}
	
	private void sendResult(){
		if(flag){
			Game game = new Game();
			game.setGid(2);
			game.setLevel(currtLevel);
			game.setPid(this.father.pid);
			game.setTime((int)(totalElapsedTime + currtLevel*20));
			game.setScore(0);
			game.setPercent(0);
			game.setAccuracy(0);
			sendResultThread = new SendResultThread(game);
			sendResultThread.start();
			flag = false;
		}
	}
	public void boundDect(){
		double distance;
		double x = ball.getX();
		double y = ball.getY();
		double ballRadius = ball.getRadius();
		double square = (x-width/2)*(x-width/2)+(y-height/2)*(y-height/2);
		
		distance = Math.sqrt(square);
		if(distance>circleR-ballRadius){
			gameover = true;
			running = false;
			final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
					getContext());
			
			dialogBuilder.setTitle("Out of bound!");
			dialogBuilder.setCancelable(false);
			dialogBuilder.setMessage(String.format("%s %.2f %s", "Total Time:",totalElapsedTime,"seconds"));
			dialogBuilder.setPositiveButton("Reset Game",
					new DialogInterface.OnClickListener() {
						// called when "Reset Game" Button is pressed
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialogShow = false;
							ball.setX(width/2);
							ball.setY(height/2);
							ball.setXSpeed(0);
							ball.setYSpeed(0);
							flag = true;
							sendResult();
							newGame(1);
						}
					}
					);
			dialogBuilder.setNegativeButton("Retry",
					new DialogInterface.OnClickListener() {
						// called when "Reset Game" Button is pressed
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialogShow = false;
							ball.setX(width/2);
							ball.setY(height/2);
							ball.setXSpeed(0);
							ball.setYSpeed(0);
							flag = true;
							sendResult();
							newGame(currtLevel);
						}
					}
					);
			activity.runOnUiThread(new Runnable() {
				public void run() {
					dialogShow = true;
					dialogBuilder.show();
				}
			}
			);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder surfaceHolder) {
		if(!dialogShow){
			this.surfaceHolder = surfaceHolder;
			this.running = true;
			
			width = getWidth();
			height = getHeight();
			ball = new Ball(this, null, width, height);
			currtLevel = 1;
			newGame(currtLevel);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
		// TODO Auto-generated method stub
		boolean retry = true;
		running = false;
		while(retry){
			try {
				t.join();
				retry = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
    @Override
    protected void onDraw(Canvas canvas) {
    	//Draw the Background
    	canvas.drawColor(Color.BLUE);
    	
    	//Draw the Backgroud Circle
    	Paint paint = new Paint();
    	paint.setColor(Color.GRAY);
        // display time remaining
    	Paint textPaint = new Paint();
        textPaint.setTextSize(width / 20); // text size 1/20 of screen width
        textPaint.setAntiAlias(true); // smoothes the text
    	canvas.drawText(String.format("%s: %.1f %s","Time remain", timeremain, "seconds"), 30, 50, textPaint);

    	canvas.drawCircle(width/2, height/2, circleR , paint);
    }
}
