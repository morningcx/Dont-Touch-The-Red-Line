package com.example.homework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DrawLevel {
	public void drawlevel(int level,int width,int height,Canvas canvas,Paint nPaint,Paint mPaint){
	switch (level) {
	case 1:{
		Paint zPaint=new Paint();
		zPaint.setTextSize(90);
		zPaint.setColor(Color.BLACK);
		canvas.drawRect(0, 0, width, height/2-width/4, mPaint);
		canvas.drawRect(0, height/2+width/4, width, height, mPaint);
		canvas.drawLine(0,height/2-width/4 , width, height/2-width/4, nPaint);
		canvas.drawText("倾斜手机避开红线", width/2-360, height/2-width/2-20, zPaint);
		break;
	}
	case 2:{
		canvas.drawLine(width/4, 0, width/4, height-width/4, nPaint);
		canvas.drawLine(width/4, height-width/4, width,height-width/4, nPaint);
		canvas.drawRect(width/4, 0, width, height-width/4, mPaint);
		break;
	}
	case 3:{
		canvas.drawLine(0, 0, width, 0, nPaint);
		canvas.drawLine(width, 0, width, height, nPaint);
		canvas.drawLine(0, height, width, height, nPaint);
		canvas.drawRect(0, width/4, 3*width/4, height-width/4, mPaint);
		break;
	}
    case 4:{
		canvas.drawRect(0, width/4, 3*width/4, height/2-width/8, mPaint);
		canvas.drawRect(width/4, height/2+width/8, width, height-width/4, mPaint);
		canvas.drawLine(width/4, height-width/4, width,height-width/4 , nPaint);
		canvas.drawLine(0, height, 0, height/2-width/8, nPaint);
		canvas.drawLine(0, height/2-width/8, 3*width/4, height/2-width/8, nPaint);
		canvas.drawLine(width, 0, width, height/2+width/8, nPaint);
		canvas.drawLine(0, width/4, 3*width/4, width/4, nPaint);
		canvas.drawLine(0, 0, width, 0, nPaint);
		break;
	}
	case 5:{
		canvas.drawRect(width/6, height-width/3, width, height-width/6, mPaint);
		canvas.drawRect(0, height-2*width/3, 5*width/6, height-width/2, mPaint);
		canvas.drawRect(2*width/3, width/6, 5*width/6, height-2*width/3, mPaint);
		canvas.drawRect(width/3, 0, width/2, height-5*width/6, mPaint);
		canvas.drawRect(0, height/2-width/4, width/6, height-2*width/3, mPaint);
		canvas.drawRect(width/6, 0, width/3, height/2-5*width/12, mPaint);
		canvas.drawLine(0, 0, 0, height/2-width/4, nPaint);
		canvas.drawLine(0, height/2-width/4, width/6, height/2-width/4, nPaint);
		canvas.drawLine(width/6, 0, width/6, height/2-5*width/12, nPaint);
		canvas.drawLine(width/6, height/2-5*width/12, width/3, height/2-5*width/12, nPaint);
		canvas.drawLine(width/6, height/2-width/4, width/6, height-2*width/3, nPaint);
		canvas.drawLine(width/6, height-2*width/3, 2*width/3, height-2*width/3, nPaint);
		canvas.drawLine(2*width/3, width/6, 2*width/3, height-2*width/3, nPaint);
		canvas.drawLine(2*width/3, width/6, 5*width/6, width/6, nPaint);
		canvas.drawLine(width, 0, width, height-width/3, nPaint);
		canvas.drawLine(0, height-width/2, 5*width/6, height-width/2, nPaint);
		canvas.drawLine(width/6, height-width/3, width/6, height-width/6, nPaint);
		canvas.drawLine(width/6, height-width/6, width, height-width/6, nPaint);
		break;
	}
	default:
		break;
	}
}
	
}
