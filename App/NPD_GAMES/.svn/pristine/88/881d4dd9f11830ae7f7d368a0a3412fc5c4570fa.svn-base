// Main Activity for the Cannon Game app.
package com.unimelb.npd.games.breakout;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.util.EncodingUtils;

import com.unimelb.npd.games.R;
import com.unimelb.npd.server.vo.Patient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class CannonGame extends Activity implements OnClickListener {
	
	private GestureDetector gestureDetector; // listens for double taps
	private CannonView cannonView; // custom view to display the game
	private TextView currtscoreView;
	private TextView currtLevel;
	private TextView lastScore;
	private TextView nextLvlScore;
	private TextView player;
	private TextView life;
	private TextView rank;
	
	private int totalscore;
	public int currtlevel;
	public int nextScore;
	private int liferemain;
	public boolean downloaded;
	private VelocityTracker vt;
	public String playerName;
	public int playerId;
	public int servRec;
	
	public Patient patient;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch(msg.what){
				case 1:
					//score updated
					totalscore +=10;
					String last = highstScore();
					if(totalscore > Integer.parseInt(last)){
						lastScore.setText("   " + Integer.toString(totalscore));
					}
					currtscoreView.setText("   " + Integer.toString(totalscore));
					break;
				case 2:
					//level up
					currtlevel++;
					currtLevel.setText("   " + Integer.toString(currtlevel));
					break;
				case 3:
					//level reseted
					currtlevel = 1;
					totalscore = 0;
					currtscoreView.setText("   " + Integer.toString(totalscore));
					currtLevel.setText("   " + Integer.toString(currtlevel));
					break;
				case 4:
					// life decreased
					liferemain--;
					life.setText("   " + Integer.toString(liferemain));
					break;
				case 5:
					// game over, reset
					liferemain = 2;
					life.setText("   " + Integer.toString(liferemain));
					break;
				case 6:
					// refresh highest score
					lastScore.setText(highstScore());
					break;
				case 7:
					// refresh rank
					rank.setText(getRank());
					break;
				case 8:
					//score updated
					totalscore +=20;
					String lastsc = highstScore();
					if(totalscore > Integer.parseInt(lastsc)){
						lastScore.setText("   " + Integer.toString(totalscore));
					}
					currtscoreView.setText("   " + Integer.toString(totalscore));
					break;
					
				case 0:
					//for display next lvl score
					nextLvlScore.setText("    " + Integer.toString(nextScore));
					break;
			}
		}
	};
	
	public void sendMessage(int what) {
		Message msg1 = handler.obtainMessage(what);
		handler.sendMessage(msg1);
	}

	// called when the app first launches
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // call super's onCreate method
		setContentView(R.layout.breakoutmain);
		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
		
		totalscore = 0;
		currtlevel = 1;
		liferemain = 2;
		patient = new Patient();
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();

		if (bundle != null && bundle.containsKey("patient")) {
			patient = (Patient) bundle.getSerializable("patient");
			playerId = patient.getPid();
			playerName = patient.getPatient_name();
		}
		
		servRec = 0;
			
		// get the CannonView
		cannonView = (CannonView) findViewById(R.id.cannonView);
		lastScore = (TextView) findViewById(R.id.lastScore);
		nextLvlScore = (TextView) findViewById(R.id.sveniD);
		player = (TextView) findViewById(R.id.textView8);
		player.setText(playerName);
		life = (TextView) findViewById(R.id.life);
		life.setText(Integer.toString(liferemain));
		
		rank = (TextView) findViewById(R.id.textRank);
		rank.setText(getRank());
		
		nextLvlScore.setText("");
		
		lastScore.setText(highstScore());

		currtscoreView = (TextView) findViewById(R.id.currtscoreView);
		currtscoreView.setText(" "+ Integer.toString(totalscore));
		
		currtLevel = (TextView) findViewById(R.id.currtLevel);
		currtLevel.setText(" "+ Integer.toString(currtlevel));
		
		// initialize the GestureDetector
		gestureDetector = new GestureDetector(this, gestureListener);	
		vt=null;
		// allow volume keys to set game volume
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
	} // end method onCreate

	public void writeFiles(String name, String file) {
		// Log.d("log", "writeFiles");
		try {
			FileOutputStream fos = this.openFileOutput(name,
					Context.MODE_PRIVATE);
			fos.write(file.getBytes());
			// Log.d("log", new String(file.getBytes()));
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
		String res ="0";
		try{
			FileInputStream fin = openFileInput("BKT-Gamerecord");
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
	
	public String highstScore(){
		String res = "0";
		String[] records = readRecord().split(",");
		String[] playerId = new String[records.length/2];
		String[] playerScr = new String[records.length/2];
		int mark=0;
		for(int i =0; i<records.length;i++){
			if(records.length>1){
				if(i%2==0){
					playerId[mark] = records[i];
					playerScr[mark] = records[i+1];
					mark++;
				}
			}
		}
		int highest = 0;
		for(int i =0; i<playerScr.length;i++){
				if(Integer.parseInt(playerScr[i])>highest){
					highest = Integer.parseInt(playerScr[i]);
			}
		}
		res = Integer.toString(highest);
		return res;
	}
	
	public String getRank(){
		
		String res = "-";
		
		String[] records = readRecord().split(",");
		String[] playerIdd = new String[records.length/2];
		String[] playerScr = new String[records.length/2];
		int mark=0;
		for(int i =0; i<records.length;i++){
			if(records.length>1){
				if(i%2==0){
					playerIdd[mark] = records[i];
					playerScr[mark] = records[i+1];
					mark++;
				}
			}
		}

		for(int i=0;i<playerScr.length;i++){
			for(int j=0;j<playerScr.length;j++){
				String temp;
				String tempId;
				if(Integer.parseInt(playerScr[i]) > Integer.parseInt(playerScr[j])){
					temp = playerScr[i];
					tempId = playerIdd[i];
					
					playerScr[i] = playerScr[j];
					playerIdd[i] = playerIdd[j];
					
					playerScr[j] = temp;
					playerIdd[j] = tempId;
				}
			}
		}
		
		int num = playerScr.length;
		for(int i =0; i<num;i++){
			if(playerName.equals(playerIdd[i])){
				res = Integer.toString(i+1);
			}
		}
		
		return res;
	}
	
	public void onClick(View v) {

	}

	// when the app is pushed to the background, pause it
	@Override
	public void onPause() {
		super.onPause(); // call the super method
		cannonView.stopGame(); // terminates the game
	}

	// release resources
	@Override
	protected void onDestroy() {
		super.onDestroy();
		cannonView.releaseResources();
	} // end method onDestroy

	// called when the user touches the screen in this Activity
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// get int representing the type of action which caused this event
		int action = event.getAction();

		// the user user touched the screen or dragged along the screen
		if (action == MotionEvent.ACTION_MOVE) {
			if(vt==null){
				vt=VelocityTracker.obtain();
			}
			else{
				vt.clear();
			}
			vt.addMovement(event);
		} // end if

		// call the GestureDetector's onTouchEvent method
		return gestureDetector.onTouchEvent(event);
	}

	// listens for touch events sent to the GestureDetector
	SimpleOnGestureListener gestureListener = new SimpleOnGestureListener() {
		// called when the user double taps the screen
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			cannonView.fireBall();
			return true; // the event was handled
		}

		// control finger fling on the screen
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			cannonView.movePad(e1, e2, distanceX, distanceY);
			vt.computeCurrentVelocity(1000);
			cannonView.padVelocity=vt.getXVelocity();
			return true;
		}
	};
}
