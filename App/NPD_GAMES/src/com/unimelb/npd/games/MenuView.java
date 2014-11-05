package com.unimelb.npd.games;

import static com.unimelb.npd.games.pipe.ViewConstant.*;

import com.unimelb.npd.games.R;
import com.unimelb.npd.games.pipe.ViewConstant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MenuView extends SurfaceView implements SurfaceHolder.Callback {
	MainActivity father;
	SurfaceHolder holder;
	int screenW;
	int screenH;
	int status;
	private Bitmap bg;
	private Bitmap[] ibtn = new Bitmap[6];
	private Paint paint;// ����
	private RectF rect1;
	private RectF rect2;
	private RectF rect3;
	private RectF rect4;
	private RectF rect5;
	private RectF rect6;
	

	public MenuView(Context context) {
		super(context);
		this.father = (MainActivity) context;
		holder = getHolder();
		this.getHolder().addCallback(this);
		
		paint = new Paint();
		paint.setAntiAlias(true);
		initBitmap();
	}



	public void initBitmap() {
		float xZoom = ViewConstant.xZoom;
		bg = ViewConstant.scaleToFit(BitmapFactory.decodeResource(
				getResources(), R.drawable.menubg), xZoom);
		ibtn[0] = scaleToFit(
				BitmapFactory.decodeResource(getResources(), R.drawable.btn1),
				xZoom);
		ibtn[1] = scaleToFit(
				BitmapFactory.decodeResource(getResources(), R.drawable.btn2),
				xZoom);
		ibtn[2] = scaleToFit(
				BitmapFactory.decodeResource(getResources(), R.drawable.btn3),
				xZoom);
		ibtn[3] = scaleToFit(
				BitmapFactory.decodeResource(getResources(), R.drawable.btn4),
				xZoom);
		ibtn[4] = scaleToFit(
				BitmapFactory.decodeResource(getResources(), R.drawable.btn5),
				xZoom);
		ibtn[5] = scaleToFit(
				BitmapFactory.decodeResource(getResources(), R.drawable.btn6),
				xZoom);
	}

	private Point point = new Point();// �����

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		point.x = (int) event.getX();
		point.y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			if (rect1.contains(point.x, point.y)) {
				 this.father.sendMessage(1);
			}
			else if(rect2.contains(point.x, point.y)){
				this.father.sendMessage(2);
			} 
			else if(rect3.contains(point.x, point.y)){
				this.father.sendMessage(3);
			} 
			else if(rect4.contains(point.x, point.y)){
				this.father.sendMessage(4);
			} 
			else if(rect5.contains(point.x, point.y)){
				this.father.sendMessage(5);
			} 
			else if (rect6.contains(point.x, point.y)) {
				 this.father.sendMessage(6);
			}
			break;

		default:
			break;
		}
		return true;
	}


	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		screenW = getWidth();
		screenH = getHeight();
		repaint();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
	}


	public void repaint() {
		SurfaceHolder holder = this.getHolder();
		Canvas canvas = holder.lockCanvas();// ��ȡ����
		try {
			synchronized (holder) {
				onDraw(canvas);// ����
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}



	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		RectF rect = new RectF(0, 0, screenW, screenH);
		canvas.drawBitmap(bg, null, rect, null);
		rect1 = new RectF(screenW/10, screenH/2, screenW/2-screenW/20, screenH/2 + screenH/8);
		rect2 = new RectF(screenW/10+screenW/2, screenH/2, screenW-screenW/20, screenH/2 + screenH/8);
		
		rect3 = new RectF(screenW/10, screenH/2+ screenH/8+screenH/20, screenW/2-screenW/20, screenH/2 + screenH/4+screenH/20);
		rect4 = new RectF(screenW/10+screenW/2, screenH/2+ screenH/8+screenH/20, screenW-screenW/20,  screenH/2 + screenH/4+screenH/20);
		
		rect5 = new RectF(screenW/10, screenH/2+screenH/4+screenH/10, screenW/2-screenW/20, screenH/2+screenH*3/8+screenH/10);
		rect6 = new RectF(screenW/10+screenW/2, screenH/2+screenH/4+screenH/10,  screenW-screenW/20, screenH/2+screenH*3/8+screenH/10);
		canvas.drawBitmap(ibtn[0],null, rect1, null);
		canvas.drawBitmap(ibtn[1],null, rect2, null);
		canvas.drawBitmap(ibtn[2],null, rect3, null);
		canvas.drawBitmap(ibtn[3],null, rect4, null);
		canvas.drawBitmap(ibtn[4],null, rect5, null);
		canvas.drawBitmap(ibtn[5],null, rect6, null);
		
		
	}
}