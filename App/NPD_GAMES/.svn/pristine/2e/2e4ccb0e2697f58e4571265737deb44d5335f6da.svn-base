package com.unimelb.npd.games.balanceball;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.unimelb.npd.games.R;
import com.unimelb.npd.server.vo.Patient;

public class BBallMainActivity extends Activity {
	private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //UUID for Bluetooth Connections
	private final static int REQUEST_ENABLE_BT = 1;
	
	public Patient patient;
	public int pid;
	public String patient_name;
	
	
	private WorldView worldView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Force device to stay in portrait orientation
		requestWindowFeature(Window.FEATURE_NO_TITLE); //Remove banner from the top of the activity
		setContentView(R.layout.balancegamemain); //Set the layout to activity_main
		
		patient = new Patient();
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();

		if (bundle != null && bundle.containsKey("patient")) {
			patient = (Patient) bundle.getSerializable("patient");
			pid = patient.getPid();
			patient_name = patient.getPatient_name();
		}
		
		worldView = (WorldView) findViewById(R.id.worldView);
		//startBluetooth();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
