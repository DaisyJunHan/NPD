package com.unimelb.npd.games.pipe;


import static com.unimelb.npd.games.pipe.ViewConstant.*;

import com.unimelb.npd.games.R;
import com.unimelb.npd.games.R.drawable;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 
 * Gameover view
 *
 */
public class GameOverView extends SurfaceView 
implements SurfaceHolder.Callback  
{
	PipeGameActivity activity;
	Paint paint;
	int currentAlpha=0;
	
	float screenWidth= this.getContext().getResources().getDisplayMetrics().widthPixels;
	float screenHeight=this.getContext().getResources().getDisplayMetrics().heightPixels;
	int sleepSpan=50;
		
	Bitmap bitmapGameOver;
	int currentX;
	int currentY;
	
	public GameOverView(PipeGameActivity activity) {
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);//设置生命周期回调接口的实现者
		paint = new Paint();//创建画笔
		paint.setAntiAlias(true);//打开抗锯齿
		
		//加载图片
		//bitmapGameOver.recycle();
		bitmapGameOver=BitmapFactory.decodeResource(activity.getResources(), R.drawable.gameover); 
		
	}
	public void onDraw(Canvas canvas){	
		//绘制黑填充矩形清背景
		paint.setColor(Color.BLACK);//设置画笔颜色
		paint.setAlpha(255);
		canvas.drawRect(0, 0, screenWidth, screenHeight, paint);
		
		//进行平面贴图
		if(bitmapGameOver==null)return;
		paint.setAlpha(currentAlpha);		
		canvas.drawBitmap(bitmapGameOver, currentX, currentY, paint);	
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}
	public void surfaceCreated(SurfaceHolder holder) {//创建时被调用		
		new Thread()
		{
			@SuppressLint("WrongCall")
			public void run()
			{									
					//计算图片位置
					currentX=(int) (width/2-bitmapGameOver.getWidth()/2);
					currentY=(int) (height/2-bitmapGameOver.getHeight()/2);
					
					for(int i=255;i>-10;i=i-5)
					{//动态更改图片的透明度值并不断重绘	
						currentAlpha=i;
						if(currentAlpha<0)
						{
							currentAlpha=0;
						}
						SurfaceHolder myholder=GameOverView.this.getHolder();
						Canvas canvas = myholder.lockCanvas();//获取画布
						try{
							synchronized(myholder){
								onDraw(canvas);//绘制
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}
						finally{
							if(canvas != null){
								myholder.unlockCanvasAndPost(canvas);
							}
						}						
						try
						{
							if(i==255)
							{//若是新图片，多等待一会
								Thread.sleep(1000);
							}
							Thread.sleep(sleepSpan);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				
				//动画播放完毕后，去主菜单界面				
				 activity.myHandler.sendEmptyMessage(1);
			}
		}.start();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//销毁时被调用

	}
}