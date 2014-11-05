package com.unimelb.npd.games.balanceball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Ball {
	private static float ballRadius = 40;
	
    private WorldView worldView;
    private Bitmap bmp;
   
    public float x;
    public float y;
    public float initX;
    public float initY;
    
    private float xSpeed;
    private float ySpeed;
    
    private int screenWidth;
    private int screenHeight;
    
    public Ball(WorldView worldView, Bitmap bmp, int screenWidth, int screenHeight) {
          this.worldView = worldView;
          this.bmp = bmp;
          this.screenWidth = screenWidth;
          this.screenHeight = screenHeight;
          
          initX = screenWidth/2;
          initY = screenHeight/2;
          setX(initX);
          setY(initY);
          setXSpeed(0);
          setYSpeed(0);
          
          updatePosition(x, y);
    }
    
    public void resetCoords(float screenWidth, float screenHeight, float x, float y, float xSpeed, float ySpeed) {
        this.x = (x/screenWidth)*this.screenWidth;
        this.y = ballRadius;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed*-1;
    }
    
    public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public float getRadius() {
		return ballRadius;
	}

    public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getYSpeed() {
		return ySpeed;
	}

	public void setYSpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}

	public void updatePosition(float x, float y) {
    	this.x = x;
    	this.y = y;
    }
	
	public void updatePhysics() {

    	if(x > screenWidth-ballRadius) {
    		xSpeed = -xSpeed;
    		setXSpeed(xSpeed);
    	}
    	if(x < ballRadius) {
    		xSpeed = -xSpeed;
    		setXSpeed(xSpeed);
    	}
    	if(y > screenHeight-ballRadius) {
    		ySpeed = -ySpeed;
    		setYSpeed(ySpeed);
    	}
    	if(y < ballRadius) {
    		ySpeed = -ySpeed;
    		setYSpeed(ySpeed);
    	}
	}
	
	public void moveBall() {
		x = x + xSpeed;
		y = y + ySpeed;	
	}
   
    public void onDraw(Canvas canvas) {
    	Paint paint = new Paint();
    	paint.setAntiAlias(true);
    	paint.setColor(Color.RED);
    	
    	updatePhysics();

    	if(worldView.onScreen) {
    		moveBall();
    		
    		updatePosition(x, y);
    		canvas.drawCircle(x, y, ballRadius, paint);
    	}
    }
}