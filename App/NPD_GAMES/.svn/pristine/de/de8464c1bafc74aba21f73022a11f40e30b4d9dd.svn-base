package com.unimelb.npd.games;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.unimelb.npd.games.tools.Config;
import com.unimelb.npd.server.vo.Patient;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity {
	protected static final int IS_LOGIN_ERROR = 1;
	protected static final int IS_NET_ERROR = 2;
	protected static final int REG_SUCCESS = 3;
	protected static final int REG_FAIL = 4;
	private EditText patientID;
	private ImageButton btnLogin;
	private ImageButton btnRegister;
	private final String REQUEST_URL = "http://" + Config.SERVER_IP + ":"
			+ Config.SERVER_PORT + "/" + Config.SERVER_NAME + "/"
			+ Config.LOGIN;
	private final String REQUEST_REG_URL = "http://" + Config.SERVER_IP + ":"
			+ Config.SERVER_PORT + "/" + Config.SERVER_NAME + "/"
			+ "RegisterServlet";
	public String out = null;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			String errorMsg = "";
			Log.d("what", "what=" + msg.what);
			switch (msg.what) {
			case IS_LOGIN_ERROR:
				// out = msg.obj.toString();
				errorMsg = "Username error! Please input again.";
				break;

			case IS_NET_ERROR:
				errorMsg = "Network Error! try again later, please.";
				break;
			case REG_SUCCESS:
				errorMsg = "Register Successfully. Please Login Now.";
				break;
			case REG_FAIL:
				errorMsg = "Register Failed. Please try again.";
				break;
			}
			Log.d("errorMsg", "error=" + errorMsg);
			Toast toast = Toast.makeText(getApplicationContext(), errorMsg,
					Toast.LENGTH_LONG);
			toast.show();

		};
	};
	public void requestServer(String url,List<NameValuePair> params){
		try {
			HttpEntity request = new UrlEncodedFormEntity(
					params, HTTP.UTF_8);
			Log.d("url", url);
			HttpPost post = new HttpPost(url);
			post.setEntity(request);
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				byte[] bytes = EntityUtils
						.toByteArray(entity);
				ObjectInputStream ois = new ObjectInputStream(
						new ByteArrayInputStream(bytes));
				Patient patient = new Patient();
				patient = (Patient) ois.readObject();
				ois.close();
				if (patient.getPid() > 0) {
					Intent intent = new Intent(
							LoginActivity.this,
							MainActivity.class);
					Bundle bundle = new Bundle();
					bundle.putInt("activity",-1);
					bundle.putSerializable("patient",
							patient);
					intent.putExtras(bundle);
					startActivity(intent);
					LoginActivity.this.finish();
				} else {
					Message message = Message.obtain();
					message.what = IS_LOGIN_ERROR;
					mHandler.sendMessage(message);
				}
			} else {
				Message message = Message.obtain();
				message.what = IS_NET_ERROR;
				mHandler.sendMessage(message);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		patientID = (EditText) findViewById(R.id.username_text);
		btnLogin = (ImageButton) findViewById(R.id.btnLogin);
		btnRegister = (ImageButton) findViewById(R.id.btnRegister);
		btnRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = patientID.getText().toString();
				final List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username", username));
				params.add(new BasicNameValuePair("age", "10"));
				if ((!username.equals(""))) {
					Thread thread = new Thread() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							super.run();
							try {
								Message message = Message.obtain();
								HttpEntity request = new UrlEncodedFormEntity(
										params, HTTP.UTF_8);
								Log.d("url", REQUEST_REG_URL);
								HttpPost post = new HttpPost(REQUEST_REG_URL);
								post.setEntity(request);
								HttpClient client = new DefaultHttpClient();
								HttpResponse response = client.execute(post);
								if (response.getStatusLine().getStatusCode() == 200) {
									HttpEntity entity = response.getEntity();
									byte[] bytes = EntityUtils
											.toByteArray(entity);
									String result = new String(bytes);
									Log.d("result", result);
									
									if(result.equals("s")){
										
										message.what = REG_SUCCESS;
										mHandler.sendMessage(message);
									}else{
										message.what = REG_FAIL;
										mHandler.sendMessage(message);
									}
								} else {
									message.what = IS_NET_ERROR;
									mHandler.sendMessage(message);
								}
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}

					};
					thread.start();
				}
				
			}
		});
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String username = patientID.getText().toString();
				final List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username", username));
				if ((!username.equals(""))) {
					Thread thread = new Thread() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							super.run();
							requestServer(REQUEST_URL,params);
						}

					};
					thread.start();
				}
			}
		});
	}

}
