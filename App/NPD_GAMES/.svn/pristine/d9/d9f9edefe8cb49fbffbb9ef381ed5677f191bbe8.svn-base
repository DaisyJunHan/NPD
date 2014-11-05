package com.unimelb.npd.games.pipe;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {

	PipeGameView pgv;
	SurfaceHolder surfaceHolder;
	public boolean flag;
	public boolean isGameOn;
	public int sleep =100;
	public DrawThread(SurfaceHolder surfaceHolder,PipeGameView pgv)
	{
		this.surfaceHolder = surfaceHolder;
		this.pgv=pgv; 
		this.flag = true;
	}
	@Override
	public void run() {
		while(flag)
		{
			Canvas canvas;
			while(isGameOn){
				canvas = null;
				try{
					canvas = surfaceHolder.lockCanvas(null);		//锁定屏幕
					synchronized(surfaceHolder){
						pgv.time++;
						pgv.doDraw(canvas,pgv.time);					//调用相应的绘制方法
					}}catch (Exception e) {
						e.printStackTrace();
					}finally{		//在finally语句中释放锁
						if(canvas != null){
							surfaceHolder.unlockCanvasAndPost(canvas);
						}
					}try{
						Thread.sleep(sleep);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					
			}
//			try 
//			{
//				Thread.sleep(1000);
//			} catch (InterruptedException e) 
//			{
//				e.printStackTrace();
//			}
		}
	}
	
}
