package com.unimelb.npd.games.breakout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.util.EncodingUtils;

import com.unimelb.npd.games.R;
import com.unimelb.npd.games.SendResultThread;
import com.unimelb.npd.server.vo.Game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class CannonView extends SurfaceView implements SurfaceHolder.Callback {
	
	private Activity activity; // to display Game Over dialog in GUI thread
	private boolean dialogIsDisplayed = false;
	
	public boolean downloaded = false;
	public GameThread gameThread; // controls the game loop
	public String playerName;
	public int playerId;
	public int servRec;
	
	// constants for game play
	public static final int TARGET_PIECES = 10; // sections in the target
	public static final int NUM_TARGET_LINE = 7; // lines of targets

	// variables for the game loop and tracking statistics
	public boolean gameOver; // is the game over?
	public int totalScore;
	public int currtLevel;
	public boolean fired;

	public Line[] target = new Line[NUM_TARGET_LINE];
	public int[] targetDistance = new int[NUM_TARGET_LINE];
	public int[] targetBeginning = new int[NUM_TARGET_LINE];
	public double pieceLength; // length of a target piece
	public double brickgap;
	public int[] targetEnd = new int[NUM_TARGET_LINE];

	// variables for the padder
	public Line pad;
	public int padDistance;
	private int padBeginning;
	private int padEnd;
	public int life;
	
	// for extension tasks.
	public float padVelocity;

	private int lineWidth; // width of the target and blocker
	private int linegap;
	public boolean[][] hitStates = new boolean[NUM_TARGET_LINE][TARGET_PIECES];
	public boolean[][] specStates = new boolean[NUM_TARGET_LINE][TARGET_PIECES];
	public int targetPiecesHit;
	public int sumOfBricks;

	public int screenWidth; // width of the screen
	public int screenHeight; // height of the screen
	private int padLen;

	// constants and variables for managing sounds
	public static final int TARGET_SOUND_ID = 0;
	public static final int CANNON_SOUND_ID = 1;
	public static final int BLOCKER_SOUND_ID = 2;
	public SoundPool soundPool; // plays sound effects
	public Map<Integer, Integer> soundMap; // maps IDs to SoundPool

	// Paint variables used when drawing each item on the screen
	private Paint targetPaint; // Paint used to draw the target
	private Paint backgroundPaint; // Paint used to clear the drawing area
	private Paint padPaint; // Paint used to draw padder
	private Ball ball;
	
	private SurfaceHolder surfaceHolder;
	
	public CannonGame father;
	
	public boolean flag;
	
	private SendResultThread sendresult;

	// public constructor
	public CannonView(Context context, AttributeSet attrs) {
		super(context, attrs); // call super's constructor
		activity = (Activity) context;
		this.father = (CannonGame) context;

		// register SurfaceHolder.Callback listener
		getHolder().addCallback(this);

		for (int i = 0; i < NUM_TARGET_LINE; i++)
			target[i] = new Line(); // create the target as a Line

		// initialize the pad
		pad = new Line(); // create the pad as a Line

		// initialize hitStates as a boolean array
		hitStates = new boolean[NUM_TARGET_LINE][TARGET_PIECES];
		specStates = new boolean[NUM_TARGET_LINE][TARGET_PIECES];

		// initialize SoundPool to play the app's three sound effects
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

		// create Map of sounds and pre-load sounds
		soundMap = new HashMap<Integer, Integer>(); // create new HashMap
		soundMap.put(TARGET_SOUND_ID,
				soundPool.load(context, R.raw.target_hit, 1));
		soundMap.put(CANNON_SOUND_ID,
				soundPool.load(context, R.raw.cannon_fire, 1));
		soundMap.put(BLOCKER_SOUND_ID,
				soundPool.load(context, R.raw.blocker_hit, 1));
		
		totalScore = 0;

		// construct Paints for drawing 
		padPaint = new Paint(); // Paint for drawing the pad
		targetPaint = new Paint(); // Paint for drawing the target
		backgroundPaint = new Paint(); // Paint for drawing the target
	}
	
	public String readGameSettings(String filename){
		// Read game settings from assets
		String gameSetting = null;
		try {
			InputStream in = getResources().getAssets().open(filename);
			int length = in.available();
			byte[] buffer = new byte[length];
			in.read(buffer);
			gameSetting = EncodingUtils.getString(buffer,"UTF-8");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gameSetting;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

	}

	// reset all the screen elements and start a new game
	public void newGame(String level) {
		
		// get game settings.
		String gameSetting = readGameSettings(level);
		Log.d("asset-level1:",gameSetting);	
		String[] settings = gameSetting.split(",");
		
		padLen = Integer.parseInt(settings[0]);
		Log.d("pad len:",Integer.toString(padLen));
		padEnd = padBeginning + padLen;
		
		pad.start = new Point(padBeginning, padDistance);
		pad.end = new Point(padEnd, padDistance);
		
		fired = false;
		flag = false;
		sumOfBricks = 0;
		
		int ballXsped = Integer.parseInt(settings[1]);
		int ballYsped = Integer.parseInt(settings[2]);
		ball = new Ball(this, null, screenWidth, screenHeight,ballXsped,ballYsped);
		// set every element of hitStates to false--restores target pieces
		for (int i = 0; i < NUM_TARGET_LINE; i++) {
			for (int j = 0; j < TARGET_PIECES; j++){
				if(settings[i+3].charAt(j)=='1'){
					hitStates[i][j] = false;
					specStates[i][j] = false;
					sumOfBricks++;
				}
				else if(settings[i+3].charAt(j)=='2'){
					// for special bricks
					hitStates[i][j] = false;
					specStates[i][j] = true;
					sumOfBricks++;
				}
				else{
					hitStates[i][j] = true;	
					specStates[i][j] = false;
				}
			}
		}
		
		int next = currtLevel;
		if(next > 2){
			next = 2;
		}
		this.father.nextScore = getNextLvLScore("Breakout-level"+Integer.toString(next));
		this.father.sendMessage(0);

		targetPiecesHit = 0; // no target pieces have been hit
		for (int i = 0; i < NUM_TARGET_LINE; i++) {
			target[i].start.set(targetBeginning[i], targetDistance[i]);
			target[i].end.set(targetEnd[i], targetDistance[i]);
		}

		// set the pad
		pad.start.set(padBeginning, padDistance);
		pad.end.set(padEnd, padDistance);

		if (gameOver) {
			gameOver = false; // the game is not over	
			gameThread = new GameThread(getHolder());	
			gameThread.start();
		}
	}
	
	private int getNextLvLScore(String level){
		
		int score = 0;
		// get game settings.
		String gameSetting = readGameSettings(level);
		String[] settings = gameSetting.split(",");
		// set every element of hitStates to false--restores target pieces
		for (int i = 0; i < NUM_TARGET_LINE; i++) {
			for (int j = 0; j < TARGET_PIECES; j++){
				if(settings[i+3].charAt(j)=='1'){
					score += 10;
				}
				if(settings[i+3].charAt(j)=='2'){
					score += 20;
				}
			}
		}
		score += totalScore;
		return score;
	}

	public void writeRecord(String file) {
		
		String prevRec = readRecord();
		Log.d("entered","aa");
		String[] records = prevRec.split(",");
		String[] playerIdd = new String[records.length/2];
		String[] playerScr = new String[records.length/2];
		int mark=0;
		for(int i =0; i<records.length;i++){
			if(records.length>1){
				if(i%2==0){
					playerIdd[mark] = records[i];
					Log.d("preplayer:",playerIdd[mark]);
					playerScr[mark] = records[i+1];
					Log.d("preplayersc:",playerScr[mark]);
					mark++;
				}
			}
		}
		String[] currtfile = file.split(",");
		String player = currtfile[0];
		Log.d("player:",player);
		String playerscore = currtfile[1];
		Log.d("score:",playerscore);
		boolean exist = false;
		for(int i = 0; i<playerIdd.length;i++){
			if(player.equals(playerIdd[i])){
				exist = true;
				if(Integer.parseInt(playerscore) > Integer.parseInt(playerScr[i])){
					playerScr[i] = playerscore;
				}
			}
		}
		if(!exist){
			prevRec+=file;
			Log.d("prevRec:",prevRec);
		}
		else{
			prevRec ="";
			for(int i = 0; i<playerIdd.length;i++){
				prevRec +=  playerIdd[i] +","+playerScr[i]+",";
				Log.d("currtRec:",prevRec);
			}
		}

		try {
			FileOutputStream fos = this.getContext().openFileOutput("BKT-Gamerecord",
					Context.MODE_PRIVATE);
			fos.write(prevRec.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readRecord(){
		String res ="";
		try{
			FileInputStream fin = this.getContext().openFileInput("BKT-Gamerecord");
			int length = fin.available();
			byte[] buffer = new byte[length];
			fin.read(buffer);
			res = EncodingUtils.getString(buffer, "UTF-8");
			fin.close();
		}
		catch(Exception e){
			Log.e("FileNotFoundException", "Couldn't find or open policy file");
			//e.printStackTrace();
		}
		return res;
	}

	public void movePad(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// update the pad position
		double scrollDistance = distanceX;
		
		// if the pad hit the top or bottom, reverse direction
		if (pad.start.x < 0) {
			pad.start.x = 0;
			pad.end.x = pad.start.x + padLen;
		} else if (pad.end.x > screenWidth) {
			pad.end.x = screenWidth;
			pad.start.x = pad.end.x - padLen;
		} else {
			pad.start.x -= scrollDistance;
			pad.end.x -= scrollDistance;
		}
		
		if(!fired){
			float mid = (pad.start.x + pad.end.x)/2 ;
			ball.setX(mid);
		}
	}

	// draws the game to the given Canvas
	public void drawGameElements(Canvas canvas) {

		backgroundPaint.setColor(Color.rgb(55,55,55));
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(),
				backgroundPaint);

		// draw the pad
		padPaint.setColor(Color.WHITE);
		canvas.drawLine(pad.start.x, pad.start.y, pad.end.x, pad.end.y,
				padPaint);

		// draw the target lines
		for (int i = 0; i < NUM_TARGET_LINE; i++) {

			Point currentPoint = new Point(); // start of current target section

			// initialize curPoint to the starting point of the target
			currentPoint.x = target[i].start.x;
			currentPoint.y = target[i].start.y;

			for (int j = 0; j < TARGET_PIECES; j++) {
				// if this target piece is not hit, draw it
				if (!hitStates[i][j]) {
					// alternate coloring the pieces yellow and blue
					switch (i){
						case 0:
							targetPaint.setColor(Color.rgb(255,255,0));
							break;
						case 1:
							targetPaint.setColor(Color.rgb(255,176,1));
							break;
						case 2:
							targetPaint.setColor(Color.rgb(255,127,0));
							break;
						case 3:
							targetPaint.setColor(Color.rgb(255,64,1));
							break;
						case 4:
							targetPaint.setColor(Color.rgb(254,0,0));
							break;
						default:
							targetPaint.setColor(Color.rgb(155,0,0));
							break;
					}
					if(specStates[i][j]){
						//special bricks
						targetPaint.setColor(Color.GREEN);
					}

					canvas.drawLine(currentPoint.x, currentPoint.y,
							(int)(currentPoint.x + pieceLength), currentPoint.y, targetPaint);
				}

				// move curPoint to the start of the next piece
				currentPoint.x += pieceLength + brickgap;
			}
		}
	}

	// display an AlertDialog when the game ends
	public void showGameOverDialog(int messageId) {
		// create a dialog displaying the given String
		final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
				getContext());
		
		dialogBuilder.setTitle(getResources().getString(messageId));
		dialogBuilder.setCancelable(false);
		
		if (messageId == R.string.lose){
			// display number of shots fired and total time elapsed
			dialogBuilder.setMessage(getResources().getString(
					R.string.results_format, totalScore));
			dialogBuilder.setPositiveButton(R.string.reset_game,
					new DialogInterface.OnClickListener() {
						// called when "Reset Game" Button is pressed
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialogIsDisplayed = false;
							String record = playerName+"," + Integer.toString(totalScore)+",";
							writeRecord(record);
							totalScore = 0;
							life =2;
							father.sendMessage(3);
							father.sendMessage(5);
							father.sendMessage(6);
							father.sendMessage(7);
							flag = true;
							sendResultToServ();
							currtLevel = 1;
							newGame("Breakout-level1"); // set up and start a new game
						}
					} 
					);
		}
		
		else if (messageId == R.string.life_reduced){
			
			// display number of shots fired and total time elapsed
			dialogBuilder.setMessage(getResources().getString(
					R.string.life_format, life));
			dialogBuilder.setPositiveButton(R.string.retry,
					new DialogInterface.OnClickListener() {
						// called when "Reset Game" Button is pressed
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialogIsDisplayed = false;
							father.sendMessage(4);
							newGame("Breakout-level"+Integer.toString(currtLevel));
						}
					}
					);
		}

		else{
			// current level passed
			dialogBuilder.setMessage(getResources().getString(
					R.string.results_format, totalScore));
			dialogBuilder.setPositiveButton(R.string.reset_game,
					new DialogInterface.OnClickListener() {
						// called when "Reset Game" Button is pressed
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialogIsDisplayed = false;
							String record = playerName+"," + Integer.toString(totalScore)+",";
							Log.d("Write:",record);
							writeRecord(record);
							totalScore = 0;
							life = 2;
							father.sendMessage(3);
							father.sendMessage(5);
							father.sendMessage(6);
							father.sendMessage(7);
							flag = true;
							sendResultToServ();
							currtLevel = 1;
							newGame("Breakout-level1"); // set up and start a new game
						}
					} 
					); 
			dialogBuilder.setNegativeButton(R.string.next_level,
					new DialogInterface.OnClickListener() {
						// called when "Reset Game" Button is pressed
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialogIsDisplayed = false;
							currtLevel++;
							if(currtLevel > 2){
								currtLevel = 2;
							}
							if(currtLevel>10){
								currtLevel = 1;
							}
							father.sendMessage(2);
							newGame("Breakout-level"+Integer.toString(currtLevel));
						}
					}
					);
		}
		activity.runOnUiThread(new Runnable() {
			public void run() {
				dialogIsDisplayed = true;
				dialogBuilder.show(); // display the dialog
			} 
		} 
		);
	}
	
	public void sendResultToServ(){
		if(flag){
			Game game = new Game();
			game.setGid(4);
			game.setLevel(currtLevel);
			game.setPid(this.father.playerId);
			game.setTime(0);
			game.setScore(totalScore);
			game.setPercent(0);
			game.setAccuracy(0);
			sendresult = new SendResultThread(game);
			sendresult.start();
			flag = false;
		}
	}

	// stops the game
	public void stopGame() {
		if (gameThread != null)
			gameThread.setRunning(false);
	}

	public void fireBall(){
		if(fired){
			return;
		}
		else{
			fired = true;
			soundPool.play(soundMap.get(CANNON_SOUND_ID), 1, 1, 1, 0, 1f);
		}
	}
	// releases resources; called by CannonGame's onDestroy method
	public void releaseResources() {
		soundPool.release(); // release all resources used by the SoundPool
		soundPool = null;
	}

	// called when surface changes size
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		flag = false;
	}

	// called when surface is first created
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		this.surfaceHolder = holder;
		
		playerName = this.father.playerName;
		playerId = this.father.playerId;
		servRec = this.father.servRec;
		
		screenWidth = getWidth(); // store the width
		screenHeight = getHeight(); // store the height
		
		life = 2;
		currtLevel = this.father.currtlevel;
		
		Log.d("width:",Integer.toString(screenWidth));
		Log.d("height:",Integer.toString(screenHeight));
		
		// Fixed configurations of the game:
		brickgap = screenWidth / 120;
		lineWidth = screenHeight / 20;
		linegap = lineWidth/3;
		
		// configure instance variables related to the target
		for (int i = 0; i < NUM_TARGET_LINE; i++) {
			targetDistance[i] = ((2 * i) + 1) * linegap;
			targetBeginning[i] = screenWidth / 10;
			targetEnd[i] =  (screenWidth * 9 / 10);
			target[i].start = new Point(targetBeginning[i], targetDistance[i]);
			target[i].end = new Point( targetEnd[i], targetDistance[i]);
		}
		pieceLength = ((targetEnd[0] - targetBeginning[0]) / TARGET_PIECES) - brickgap;
		
		// configure instance variables related to the pad
		padDistance = screenHeight *7 / 8;
		padBeginning = screenWidth / 2;
		padVelocity = 0;

		// configure Paint objects for drawing game elements
		padPaint.setStrokeWidth(lineWidth / 2); // set line thickness
		targetPaint.setStrokeWidth(lineWidth / 2); // set line thickness
		
		backgroundPaint.setColor(Color.WHITE); // set background color
		
		newGame("Breakout-level"+Integer.toString(currtLevel)); // set up and start a new game
		
		if (!dialogIsDisplayed) {
			gameThread = new GameThread(holder);
			gameThread.setRunning(true);
			gameThread.start();
		}
	}

	// called when the surface is destroyed
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// ensure that thread terminates properly
		boolean retry = true;
		gameThread.setRunning(false);
		
		// save game record.
		String record = playerName+"," + Integer.toString(totalScore)+",";
		writeRecord(record);
		flag = true;
		sendResultToServ();

		while (retry) {
			try {
				gameThread.join();
				retry = false;
			}
			catch (InterruptedException e) {
			}
		}
	} 

	// Thread subclass to control the game loop
	@SuppressLint("WrongCall") class GameThread extends Thread {
		 // for manipulating canvas
		private boolean threadIsRunning = true; // running by default

		// initializes the surface holder
		public GameThread(SurfaceHolder holder) {
			surfaceHolder = holder;
			setName("GameThread");
		}

		// changes running state
		public void setRunning(boolean running) {
			threadIsRunning = running;
		}

		// controls the game loop
		@Override
		public void run() {
			Canvas canvas = null;

			while (threadIsRunning) {
				try {
					canvas = surfaceHolder.lockCanvas(null);

					// lock the surfaceHolder for drawing
					synchronized (surfaceHolder) {
						drawGameElements(canvas); // draw
						ball.onDraw(canvas);
					}
				} 
				finally {
					if (canvas != null)
						surfaceHolder.unlockCanvasAndPost(canvas);
				} // end finally
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
