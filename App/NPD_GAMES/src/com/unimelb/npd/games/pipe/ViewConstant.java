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
	
	public static float scoreWidth = 7*xZoom*4f;//ʱ�����ּ��
	public static boolean DRAW_THREAD_FLAG = true;
	
	
	public static Bitmap scaleToFit(Bitmap bm,float fblRatio)//����ͼƬ�ķ���
    {
    	int width = bm.getWidth(); //ͼƬ���
    	int height = bm.getHeight();//ͼƬ�߶�
    	Matrix matrix = new Matrix(); 
    	matrix.postScale((float)fblRatio, (float)fblRatio);//ͼƬ�ȱ�����СΪԭ����fblRatio��
    	Bitmap bmResult = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);//����λͼ        	
    	return bmResult;
    }
	public static void initChessViewFinal()
	{
		xSpan=48.0f*xZoom;
		ySpan=48.0f*yZoom;
		
		scoreWidth = 7*xZoom*4f;//ʱ�����ּ��
		
	}
}
