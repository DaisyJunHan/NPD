package com.unimelb.npd.games.pipe;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ViewConstant {
	public static float sXtart=0;
	public static float sYtart=0;
	
	public static boolean isnoPlaySound=true;
	public static float height;
     public static float width; 
	public static boolean isPlaySound=true;
	public static boolean isStart=false;
	public static boolean isSaveLevel;
	
	
	public static int zTime=900000;
	public static int endTime=zTime;
	public static float xZoom=1F;
	public static float yZoom=1F;
	
	public static float xSpan=48.0f*xZoom;
	public static float ySpan=48.0f*yZoom;
	
	public static float scoreWidth = 7*xZoom*4f;//时间数字间隔
	public static boolean DRAW_THREAD_FLAG = true;
	
	
	public static Bitmap scaleToFit(Bitmap bm,float fblRatio)//缩放图片的方法
    {
    	int width = bm.getWidth(); //图片宽度
    	int height = bm.getHeight();//图片高度
    	Matrix matrix = new Matrix(); 
    	matrix.postScale((float)fblRatio, (float)fblRatio);//图片等比例缩小为原来的fblRatio倍
    	Bitmap bmResult = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);//声明位图        	
    	return bmResult;
    }
	public static void initChessViewFinal()
	{
		xSpan=48.0f*xZoom;
		ySpan=48.0f*yZoom;
		
		scoreWidth = 7*xZoom*4f;//时间数字间隔
		
	}
}
