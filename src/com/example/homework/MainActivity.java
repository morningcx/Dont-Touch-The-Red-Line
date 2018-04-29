package com.example.homework;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.widget.Button;
import android.widget.Toast;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;


public class MainActivity extends Activity implements Callback, SensorEventListener {
    private SurfaceView mSurface;
    private SurfaceHolder mHolder;
    private Paint mPaint,nPaint;
    private Canvas canvas;
    private Sensor accelerometer;
    private SensorManager mgr;
    private int level=1,done=0;
    private int width=0,height=0;
    private float radius;
    private AlertDialog alertDialog=null;
    private float x=0,y=0,vx=0,vy=0;
    private DrawLevel drawlevel1;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSurface=(SurfaceView) findViewById(R.id.ball);
		drawlevel1=new DrawLevel();
		setPaint();getwhi();position(level);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		mHolder=mSurface.getHolder();
		mHolder.addCallback(this);
		mgr=(SensorManager) this.getSystemService(SENSOR_SERVICE);
		accelerometer=mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}
	
	private void getwhi(){
		Intent intent=getIntent();
		if(intent.getStringExtra("level")!=null)
		level=Integer.valueOf(intent.getStringExtra("level"));
		DisplayMetrics dm =getResources().getDisplayMetrics(); 
        width= dm.widthPixels;  
        height= dm.heightPixels; 
        radius=width/24; 
	}
	
	private void setPaint(){
		nPaint=new Paint();
		nPaint.setColor(Color.RED);
		nPaint.setStrokeWidth(15);
		mPaint=new Paint();    
		mPaint.setColor(Color.WHITE);
	}
	
	private void position(int level){
		switch (level) {
		case 1:
			x=radius;y=height/2;
			Toast.makeText(this, "第一关", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			x=(radius*3);y=radius;
			Toast.makeText(this, "第二关", Toast.LENGTH_SHORT).show();
			break;
        case 3:
        	x=radius;y=radius*3;
        	Toast.makeText(this, "第三关", Toast.LENGTH_SHORT).show();
			break;
        case 4:
        	x=width-radius;y=height-radius*2;
			Toast.makeText(this, "第四关", Toast.LENGTH_SHORT).show();
			break;
        case 5:
        	x=width-radius;y=height-radius*2;
        	Toast.makeText(this, "第五关", Toast.LENGTH_SHORT).show();
	        break;
		default:
			break;
		}
	}
	
	private void showdialog(){ 
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		View view=LayoutInflater.from(this).inflate(R.layout.dialog, null);
		Button back=(Button) view.findViewById(R.id.back);
		Button exit=(Button) view.findViewById(R.id.exit2);
		back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					MainActivity.this.finish();
					Intent intent=new Intent(MainActivity.this,HomePage.class);
					startActivity(intent);
					alertDialog.dismiss();
				}
			});
        exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.this.finish();
				alertDialog.dismiss();
			}
		});
        builder.setCancelable(false);
        builder.setView(view);
		alertDialog=builder.show();
	}
	
	private void drawball(float x,float y){	
		canvas=mHolder.lockCanvas();
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		canvas.drawPaint(mPaint);   
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));  
		canvas.drawCircle(x, y,radius, mPaint);
		if(level==6){     
			mgr.unregisterListener(this, accelerometer);
			drawlevel1.drawlevel(level-1, width, height, canvas, nPaint, mPaint);
			showdialog();
		}
		else 
			drawlevel1.drawlevel(level, width, height, canvas, nPaint, mPaint);
		mHolder.unlockCanvasAndPost(canvas);
	}
	
	private void borderline(int i){
		switch (i) {
		case 1:{
			if(y<height/2-width/4+radius){x=radius;y=height/2;vx=0;vy=0;}
			else if(x>width-radius)
			{x=(radius*3);y=radius;vx=0;vy=0;level=2;Toast.makeText(this, "第二关", Toast.LENGTH_SHORT).show();}
			else if(x<radius&&y<height/2+width/4-radius){x=radius;vx=-vx/3;}
			else if(y>height/2+width/4-radius){y=height/2+width/4-radius;vy=-vy/3;}
			break;
		}
		case 2:{
			if(x>(width/4-radius)&&y<(height-width/4+radius)) {x=(radius*3);y=radius;vx=0;vy=0;}
			else if(x>width) {x=radius;y=radius*3;vx=0;vy=0;level=3;Toast.makeText(this, "第三关", Toast.LENGTH_SHORT).show();}
		    else if(x<radius) {x=radius;vx=-vx/3;}
		    if(y>height-radius){ y=height-radius;vy=-vy/3;}
		    else if(y<radius) {y=radius;vy=-vy/3;}
			break;
		}
		case 3:{
			if(x>width-radius||y<radius||y>height-radius){x=radius;y=radius*3;vx=0;vy=0;}
			else if(x>0&&x<(3*width/4)&&y<width/4&&y>(width/4-radius)) 
			{y=width/4-radius;vy=-vy/3;}
			else if(x>3*width/4&&x<(3*width/4+radius)&&y>width/4&&y<(height-width/4))
			{x=3*width/4+radius;vx=-vx/3;}
			else if(x>0&&x<(3*width/4)&&y<(height-width/4+radius)&&y>(height-width/4))
			{y=height-width/4+radius;vy=-vy/3;}	
			else if(x<radius&&y<width/4) {x=radius;vx=-vx/3;}
			else if(x<0&&y>(height-3*width/4)) 
			{x=width-radius;y=height-radius*2;vx=0;vy=0;level=4;Toast.makeText(this, "第四关", Toast.LENGTH_SHORT).show();}
			break;
		}
		case 4:{
			if(x>=width/4&&x<width&&y>height-width/4&&y<height-width/4+radius
				||x<radius&&y>height/2-width/8
				||x<3*width/4&&y>height/2-width/8&&y<height/2-width/8+radius
				||x>width-radius&&y<height/2+width/8
				||y<radius||x<3*width/4&&y>width/4-radius&&y<width/4)
			{x=width-radius;y=height-radius*2;vx=0;vy=0;}
			else if(x>width-radius&&y>height-width/4&&y<height){x=width-radius;vx=-vx/3;}
			else if(y>height-radius){y=height-radius;vy=-vy/3;}
			else if(x>width/4-radius&&x<=width/4&&y<height-width/4&&y>height/2+width/8){x=width/4-radius;vx=-vx/3;}
			else if(x>width/4&&x<width&&y>height/2+width/8-radius&&y<height/2+width/8){y=height/2+width/8-radius;vy=-vy/3;}
			else if(x>3*width/4&&x<3*width/4+radius&&y<height/2-width/8&&y>width/4){x=3*width/4+radius;vx=-vx/3;}
			else if(x<radius&&y<width/4){x=width-radius;y=height-radius*2;vx=0;vy=0;level=5;Toast.makeText(this, "第五关", Toast.LENGTH_SHORT).show();}
			break;
		}
		case 5:{
			if(x>width/6-radius&&x<width&&y>height-width/6&&y<height-width/6+radius
				||x<width/6&&x>width/6-radius&&y>height-width/3&&y<height-width/6
				||x>0&&x<5*width/6&&y<height-width/2+radius&&y>height-width/2
				||x>width-radius&&y<height-width/3
				||x>2*width/3&&x<5*width/6&&y<width/6&&y>width/6-radius
				||x>2*width/3-radius&&x<2*width/3&&y>width/6-radius&&y<height-2*width/3-radius
				||x>0&&x<2*width/3&&y>height-2*width/3-radius&&y<height-2*width/3
				||x>radius&&x<width/6&&y<height-2*width/3-radius&&y>height/2-width/4-radius
				||x>width/6&&x<width/6+radius&&y<height-2*width/3-radius&&y>height/2-width/4
				||x<radius&&y<height/2-width/4-radius
				||x>width/6-radius&&x<width/3&&y<height/2-5*width/12
				||x>width/6&&x<width/3&&y<height/2-5*width/12+radius)
			{x=width-radius;y=height-radius*2;vx=0;vy=0;}
			else if(y>height-radius&&x>=radius){y=height-radius;vy=-vy/3;}
			else if(x<radius&&y>height-width/2){x=radius;vx=-vx/3;}
			else if(x>width-radius&&y>height-width/6+radius){x=width-radius;vx=-vx/3;}
			else if(x<5*width/6+radius&&x>5*width/6&&y>width/6&&y<height-width/2){x=5*width/6+radius;vx=-vx/3;}
			else if(x>width/2&&x<width-radius&&y<radius){y=radius;vy=-vy/3;}
			else if(x>width/2&&x<width/2+radius&&y<height-5*width/6){x=width/2+radius;vx=-vx/3;}
			else if(x<width/2&&x>width/3&&y<height-5*width/6+radius){y=height-5*width/6+radius;vy=-vy/3;}
			else if(x>width/3-radius&&x<width/3&&y<height-5*width/6&&y>height/2-5*width/12+radius){x=width/3-radius;vx=-vx/3;}
			else if(x>width/6&&x<width-radius&&y<height-width/3&&y>height-width/3-radius){y=height-width/3-radius;vy=-vy/3;}
			else if(x<width/6&&y<-radius) level=6;
			break;
		}
		default:
			break;
		}
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		float nx,ny;
		borderline(level);
		nx=(float) (-vx/2.6);
		ny=(float) (vy/2.6);  
		vx+=event.values[0];  
		vy+=event.values[1];
		if(done==1){ 
		try{
			drawball(x+nx,y+ny);
			x+=nx;
			y+=ny;
		}catch(Exception e){
			Log.e("ball"," "+e.getMessage());
		  }
		}
		if(done==0)
			done=1;
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		mgr.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
	}
	
	@Override
	protected void onPause() {
		mgr.unregisterListener(this, accelerometer);
		super.onPause();
	} 

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
}
