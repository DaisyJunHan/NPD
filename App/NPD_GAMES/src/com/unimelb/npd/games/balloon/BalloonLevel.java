package com.unimelb.npd.games.balloon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.unimelb.npd.games.MainActivity;
import com.unimelb.npd.games.R;
import com.unimelb.npd.server.vo.Patient;


public class BalloonLevel extends Activity {
	public int LEVEL = 1;
	public Patient patient;
	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.balloonview);
		patient = (Patient) getIntent().getSerializableExtra("patient");
		LEVEL = getIntent().getIntExtra("LEVEL", LEVEL);

		textView = (TextView) findViewById(R.id.user_level);
		textView.setText("Your best Level is "+LEVEL);
				
		Button Btn_level1 = (Button) findViewById(R.id.balloon_level1);
		Button Btn_level2 = (Button) findViewById(R.id.balloon_level2);
		Button Btn_level3 = (Button) findViewById(R.id.balloon_level3);
		Button Btn_level4 = (Button) findViewById(R.id.balloon_level4);
		Button Btn_level5 = (Button) findViewById(R.id.balloon_level5);
		Button Btn_level6 = (Button) findViewById(R.id.balloon_level6);
		
		Btn_level1.setOnClickListener(new button_listener());
		Btn_level2.setOnClickListener(new button_listener());
		Btn_level3.setOnClickListener(new button_listener());
		Btn_level4.setOnClickListener(new button_listener());
		Btn_level5.setOnClickListener(new button_listener());
		Btn_level6.setOnClickListener(new button_listener());
		
	}
	private class button_listener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()) {
			case R.id.balloon_level1:
				LEVEL = 1;
				break;
			case R.id.balloon_level2:
				LEVEL = 2;
				break;
			case R.id.balloon_level3:
				LEVEL = 3;
				break;
			case R.id.balloon_level4:
				LEVEL = 4;
				break;
			case R.id.balloon_level5:
				LEVEL = 5;
				break;
			case R.id.balloon_level6:
				LEVEL = 6;
				break;

			}
			Intent toShowLevel = new Intent(BalloonLevel.this, BalloonGame.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("patient", patient);
			bundle.putInt("LEVEL", LEVEL);
			toShowLevel.putExtras(bundle);
			
			startActivity(toShowLevel);
			
		}
	}
	@Override
	public boolean onKeyDown(int KeyCode, KeyEvent event){
		if(KeyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
		{
/*			Intent backToWelcome = new Intent();
			backToWelcome.setClass(BalloonLevel.this, MainActivity.class);
			startActivity(backToWelcome);*/
			finish();
			
		}
		return true;
	}

}
