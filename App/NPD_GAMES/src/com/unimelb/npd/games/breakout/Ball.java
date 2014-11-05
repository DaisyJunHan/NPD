package com.unimelb.npd.games.breakout;

import java.util.Map;

import com.unimelb.npd.games.R;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.SoundPool;
import android.util.Log;

public class Ball {
	public int NUM_TARGET_LINE;
	public int TARGET_PIECES;
	public double pieceLength;
	public Line[] target = new Line[NUM_TARGET_LINE];
	public int[] targetDistance = new int[NUM_TARGET_LINE];
	public int[] targetBeginning = new int[NUM_TARGET_LINE];
	public int[] targetEnd = new int[NUM_TARGET_LINE];
	public boolean[][] hitStates;
	public int totalScore;
	public float padVelocity;
	public int targetPiecesHit;
	public SoundPool soundPool;
	public Map<Integer, Integer> soundMap;
	public static final int TARGET_SOUND_ID = 0;
	public int sumOfBricks;
	public double brickgap;
	public boolean fired;

	private float ballRadius = 10;
	
    private CannonView worldView;
    private Bitmap bmp;
   
    public float x;
    public float y;
    public float initX;
    public float initY;
    
    private float xSpeed;
    private float ySpeed;
    
    private int screenWidth;
    private int screenHeight;
    
    public Line pad;
    public int padDistance;
    public CannonGame father;
    
    public Ball(CannonView worldView, Bitmap bmp, int screenWidth, int screenHeight, int vx, int vy) {
    	
          this.worldView = worldView;
          this.bmp = bmp;
          this.father = worldView.father;
          
          this.pad = worldView.pad;
          this.padDistance = worldView.padDistance;
          this.target = worldView.target;
          
          this.fired = worldView.fired;
          this.brickgap = worldView.brickgap;
         
          this.NUM_TARGET_LINE = worldView.NUM_TARGET_LINE;
          this.TARGET_PIECES = worldView.TARGET_PIECES;
          this.padVelocity = worldView.padVelocity;
          this.targetPiecesHit = worldView.targetPiecesHit;
          this.targetDistance = worldView.targetDistance;
          hitStates = new boolean[NUM_TARGET_LINE][TARGET_PIECES];
          this.hitStates = worldView.hitStates;
          this.pieceLength = worldView.pieceLength;
          this.screenWidth = screenWidth;
          this.screenHeight = screenHeight;
          
          this.sumOfBricks = worldView.sumOfBricks;
          
          initX = (pad.start.x + pad.end.x)/2;
          initY = (pad.start.y + pad.end.y)/2 - ballRadius -10;
          setX(initX);
          setY(initY);
          setXSpeed(vx);
          setYSpeed(vy);
          
          updatePosition(x, y);
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
		
        this.pad = worldView.pad;
        this.padDistance = worldView.padDistance;
        this.target = worldView.target;
        this.bmp = bmp;
        this.father = worldView.father;
        this.padVelocity = worldView.padVelocity;
        this.pieceLength = worldView.pieceLength;
        this.targetPiecesHit = worldView.targetPiecesHit;
        this.targetDistance = worldView.targetDistance;
        this.hitStates = worldView.hitStates;
        this.sumOfBricks = worldView.sumOfBricks;
        this.brickgap = worldView.brickgap;
		//Log.d("padx:",Integer.toString(pad.start.x));
		
    	if(x > screenWidth-ballRadius) {
    		xSpeed = -xSpeed;
    		setXSpeed(xSpeed);
    		x = screenWidth - ballRadius;
    		setX(x);
    		
    	}
    	if(x < ballRadius) {
    		xSpeed = -xSpeed;
    		setXSpeed(xSpeed);
    		setX(ballRadius);
    	}
    	if(y < ballRadius) {
    		ySpeed = -ySpeed;
    		setYSpeed(ySpeed);
    		setY(ballRadius);
    	}
    	if(y > screenHeight-ballRadius) {
    		// Game over or life reduce
    		ySpeed = -ySpeed;
    		setYSpeed(ySpeed);
    		y = screenHeight - ballRadius;
    		setY(y);
    		
    		
    		worldView.life--;
			if(worldView.life==0){
				worldView.gameThread.setRunning(false);
				worldView.showGameOverDialog(R.string.lose); 
				worldView.gameOver = true; // the game is over
			}
			else{
				worldView.gameThread.setRunning(false);
				worldView.showGameOverDialog(R.string.life_reduced); 
				worldView.gameOver = true;
			}
			
    	}
    	
		if (x + ballRadius >= pad.start.x
				&& x - ballRadius <= pad.end.x
				&& y + ballRadius >= padDistance
				&& y - ballRadius <= padDistance) {
			ySpeed = -ySpeed; // reverse cannonball's Y direction
			setYSpeed(ySpeed);
			x += padVelocity/1000;
			setX(x);
			setY(initY);
		}
		
		// check for collisions with targets
		for (int i = NUM_TARGET_LINE - 1; i >=0; i--) {
			if (x + ballRadius >= target[i].start.x
					&& x - ballRadius <= target[i].end.x
					&& y + ballRadius >= targetDistance[i]
					&& y - ballRadius <= targetDistance[i]) {
				// determine target section number (0 is the top)
				int section = (int) ((x - target[i].start.x) / (pieceLength+brickgap));

				// check if the piece hasn't been hit yet
				if ((section >= 0 && section < TARGET_PIECES)
						&& !hitStates[i][section]) {
					
					hitStates[i][section] = true;
					worldView.hitStates[i][section] = true;
					
					worldView.soundPool.play(worldView.soundMap.get(TARGET_SOUND_ID), 1, 1, 1, 0, 1f);
					
					ySpeed = -ySpeed; // reverse cannonball's Y direction
					setYSpeed(ySpeed);
					
					if(worldView.specStates[i][section]){
						totalScore += 20;
						worldView.totalScore += 20;
						this.father.sendMessage(8);
					}
					else{
						totalScore += 10;
						worldView.totalScore += 10;
						this.father.sendMessage(1);
					}
					targetPiecesHit++;
					worldView.targetPiecesHit++;
				}
			}
		}
	}
	
	public void moveBall() {
		x = x + xSpeed;
		y = y + ySpeed;	
	}
   
    public void onDraw(Canvas canvas) {
    	Paint paint = new Paint();
    	paint.setAntiAlias(true);
    	paint.setColor(Color.WHITE);
    	
    	this.fired = worldView.fired;
    	
    	if(fired){
        	moveBall();
        	updatePosition(x, y);
        	updatePhysics();
			if (targetPiecesHit == sumOfBricks) {
				worldView.gameThread.setRunning(false);
				worldView.showGameOverDialog(R.string.win); // show winning dialog
				worldView.gameOver = true; // the game is over
			}
    	}
    	
    	canvas.drawCircle(x, y, ballRadius, paint);

    }
}
