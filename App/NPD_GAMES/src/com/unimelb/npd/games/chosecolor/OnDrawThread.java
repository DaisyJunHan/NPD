package com.unimelb.npd.games.chosecolor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class OnDrawThread extends Thread {

	ChoseColorView ccv;
	TimeUPView tuv;
	SurfaceHolder sh;
	int runFlag = 0; //0:playing 1: pause 2:stop

	public OnDrawThread(ChoseColorView ccv) {
		super();
		this.ccv = ccv;
		sh = ccv.getHolder();
	}

	@Override
	public void run() {
		super.run();
		Canvas canvas;
		Paint paint = new Paint();
		TimeUPView tuv = new TimeUPView(ccv);
		int i = 0;
		while (runFlag != 2) {
			canvas = null;
			try {
				canvas = sh.lockCanvas(null);
				
				synchronized (this.sh) {
					if (canvas != null) {
						
						//canvas.drawText("Time: "+ccv.choseColor.time+"s", ccv.choseColor.SCREENWIDTH/8*5, ccv.choseColor.SCREENHEIGHT/20*2, paint);
						//ccv.myDraw(canvas,paint);
						//ccv.DrawTitle(canvas, paint);
						
						canvas.drawColor(Color.rgb(255,250,205));
						
						ccv.DrawPic(canvas,paint);
						ccv.DrawTitle(canvas, paint);
						ccv.DrawText(canvas, paint);
						ccv.DrawMesh(canvas, paint);
						if(runFlag == 0 && i % 5 == 0) {
							i = 0;
							ccv.time -= 1;
						}
							
						if(ccv.time < 0 ){
							runFlag = 2;
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							tuv.drawTimeup(canvas,paint);
						}
						
					}
				}
			} finally {
			try {
				if (sh != null) {
					sh.unlockCanvasAndPost(canvas);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			//Log.d(" i ","i="+i);
			
		}
	}
}
