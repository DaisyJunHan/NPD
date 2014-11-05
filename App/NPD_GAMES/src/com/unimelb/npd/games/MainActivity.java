package com.unimelb.npd.games;



import static com.unimelb.npd.games.pipe.ViewConstant.height;
import static com.unimelb.npd.games.pipe.ViewConstant.initChessViewFinal;
import static com.unimelb.npd.games.pipe.ViewConstant.sXtart;
import static com.unimelb.npd.games.pipe.ViewConstant.sYtart;
import static com.unimelb.npd.games.pipe.ViewConstant.width;
import static com.unimelb.npd.games.pipe.ViewConstant.xZoom;
import static com.unimelb.npd.games.pipe.ViewConstant.yZoom;

import com.unimelb.npd.games.balanceball.BBallMainActivity;
import com.unimelb.npd.games.balloon.BalloonLevel;
import com.unimelb.npd.games.breakout.CannonGame;
import com.unimelb.npd.games.cardgame.CardGame;
import com.unimelb.npd.games.chosecolor.ChoseColor;
import com.unimelb.npd.games.pipe.PipeGameActivity;
import com.unimelb.npd.server.vo.Patient;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
enum WhichView{WELCOME_VIEW,Login_VIEW,MENU_VIEW,GAME_PIPE_VIEW,GAME_COLOR_VIEW,GAME_BALLOON_VIEW,GAME_BBALL,GAME_BREAKOUT,GAME_CARD_GAME};
public class MainActivity extends Activity {
	public Patient patient = new Patient();
	public int pid;
	public String patient_name;
	public int age;
	public int pipe_level;
	public int ball_level;
	public int balloon_level;
	public int breakout_level;
	public int poker_level;
	public int color_level;
	WhichView wv;
	WelcomeView welcomeView;
	MenuView menuView;
	int screenWidth;
	int screenHeight;
	int activity =0;
	public Handler myHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 0:
				wv = WhichView.MENU_VIEW;
				gotoMenuView();
				break;
				case 1:
					wv = WhichView.GAME_PIPE_VIEW;
					gotoPipeGameActiviry();
					break;
				case 2:
					wv = WhichView.GAME_BBALL;
					gotoBalanceActivity();
					break;
				case 3:
					wv = WhichView.GAME_BALLOON_VIEW;
					gotoBalloonGameActivity();
					break;
				case 4:
					wv = WhichView.GAME_BREAKOUT;
					gotoBreakOutActivity();
					break;
				case 5:
					wv = WhichView.GAME_CARD_GAME;
					gotoCardGameActivity();
					break;
				case 6:
					wv = WhichView.GAME_COLOR_VIEW;
					gotoColorGameActiviry();
					break;
				case -1:
					wv = WhichView.Login_VIEW;
					gotoLoginActivity();
					break;
			}
		}
		};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initPm();
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        
        if (bundle != null && bundle.containsKey("activity")){
		activity = bundle.getInt("activity");
			switch (activity){
			case 0:
				gotoWelcomeView();
				break;
			case -1://from LoginActivity
				patient = (Patient) bundle.get("patient");
				pid = patient.getPid();
				patient_name = patient.getPatient_name();
				age = patient.getAge();
				pipe_level = patient.getPipe_level();
				ball_level = patient.getBall_level();
				balloon_level = patient.getBalloon_level();
				breakout_level = patient.getBreakout_level();
				poker_level = patient.getPoker_level();
				color_level = patient.getColor_level();
				Log.d("patient_name", patient_name);
				gotoMenuView();
				break;
			default:
				gotoWelcomeView();
				break;
		}}else{
			gotoWelcomeView();
		}
		}
       
	private void gotoLoginActivity(){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);
		MainActivity.this.startActivity(intent);
	}
	private void gotoPipeGameActiviry(){
		Intent intent = new Intent(
				MainActivity.this,
				PipeGameActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("activity",0);
		bundle.putSerializable("patient",
				patient);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	private void gotoColorGameActiviry(){
		Intent intent = new Intent(
				MainActivity.this,
				ChoseColor.class);
		Bundle bundle = new Bundle();
		bundle.putInt("activity",0);
		bundle.putSerializable("patient",
				patient);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	private void gotoBalloonGameActivity(){
		Intent intent = new Intent(
				MainActivity.this,
				BalloonLevel.class);
		Bundle bundle = new Bundle();
		bundle.putInt("activity",0);
		bundle.putSerializable("patient",
				patient);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	private void gotoCardGameActivity(){
		Intent intent = new Intent(
				MainActivity.this,
				CardGame.class);
		Bundle bundle = new Bundle();
		bundle.putInt("activity",0);
		bundle.putSerializable("patient",
			patient);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	private void gotoBreakOutActivity(){
		Intent intent = new Intent(
				MainActivity.this,
				CannonGame.class);
		Bundle bundle = new Bundle();
		bundle.putInt("activity",0);
		bundle.putSerializable("patient",
				patient);
		intent.putExtras(bundle);
		this.startActivity(intent);
	}
	private void gotoBalanceActivity(){
		Intent intent = new Intent(
				MainActivity.this,
				BBallMainActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("activity",0);
		bundle.putSerializable("patient",
				patient);
		intent.putExtras(bundle);
		this.startActivity(intent);
	}
	private void gotoWelcomeView()
    {
    	if(welcomeView==null)
    	{
    		welcomeView=new WelcomeView(this);
    	}
    	setContentView(welcomeView);
    	wv=WhichView.WELCOME_VIEW;
    }
	private void gotoMenuView() {
		if(menuView==null)
    	{
			menuView=new MenuView(this);
    	}
    	setContentView(menuView);
    	wv=WhichView.MENU_VIEW;
	}
	public void sendMessage(int what) {
		Message msg1 = myHandler.obtainMessage(what);
		myHandler.sendMessage(msg1);
	}
	 public void initPm()
	    {
	    	//��ȡ��Ļ�ֱ���
	        DisplayMetrics dm=new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(dm);
	        screenHeight=(int) (height=dm.heightPixels);
	        screenWidth=(int) (width=dm.widthPixels); 
//	        
	        if(screenHeight>screenWidth)
	        {
	        	height=screenHeight;
	        	width=screenWidth;
	        }
	        else
	        {
	        	height=screenWidth;
	        	width=screenHeight;
	        }
	        float zoomx=width/480;
			float zoomy=height/800;
			if(zoomx>zoomy){
				xZoom=yZoom=zoomy;
				
			}else
			{
				xZoom=yZoom=zoomx;
			}
			sXtart=(width-480*xZoom)/2;
			sYtart=(height-800*yZoom)/2;
			initChessViewFinal();
	    }
}
