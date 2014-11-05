package com.unimelb.npd.games;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import android.util.Log;

import com.unimelb.npd.games.tools.Config;
import com.unimelb.npd.server.vo.Game;

public class SendResultThread extends Thread {

	public final List<NameValuePair> params = new ArrayList<NameValuePair>();
	private final String REQUEST_URL = "http://" + Config.SERVER_IP + ":"
			+ Config.SERVER_PORT + "/" + Config.SERVER_NAME + "/"
			+ Config.GAMERECORD;
	
	public SendResultThread(Game g){
		System.out.println(REQUEST_URL);
		
		int pid = g.getPid();
		int gid = g.getGid();
		int time = g.getTime();
		int level = g.getLevel();
		int score = g.getScore();
		int percent = g.getPercent();
		int accuracy = g.getAccuracy();
//		String ext1 = g.getExt1();
//		String ext2 = g.getExt2();
//		String ext3 = g.getExt3();
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
		String date = sdf.format(dt);
		
		params.add(new BasicNameValuePair("pid", pid+""));
		params.add(new BasicNameValuePair("gid", gid+""));
		params.add(new BasicNameValuePair("time", time+""));
		params.add(new BasicNameValuePair("level", level+""));
		params.add(new BasicNameValuePair("score", score+""));
		params.add(new BasicNameValuePair("date", date));
		params.add(new BasicNameValuePair("percent", percent+""));
		params.add(new BasicNameValuePair("accuracy", accuracy+""));
		
	}
	@Override
	public void run() {
		super.run();
		try {
			HttpEntity request = new UrlEncodedFormEntity(
					params, HTTP.UTF_8);
			Log.d("url", REQUEST_URL);
			HttpPost post = new HttpPost(REQUEST_URL);
			post.setEntity(request);
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				byte[] bytes = EntityUtils
						.toByteArray(entity);
				String result = new String(bytes);
				Log.d("result", result);
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

}
