package com.example.drawdotline;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class MRelativeLayout extends RelativeLayout {
	
	private static final String LOG_TAG = "MRelativeLayout";
	Canvas mCanvas;
	private MyOnDrawListner myOnDrawListner;
	
	public MRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}
	
	public MRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}
	
	
	public MRelativeLayout(Context context) {
		super(context);
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}
	
	public interface MyOnDrawListner{
		void onMyDraw();
	}
	
	public void setMyOnDrawLisnter(MyOnDrawListner myOnDrawListner){
		this.myOnDrawListner = myOnDrawListner;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mCanvas = canvas;
		
		if(myOnDrawListner != null){
			myOnDrawListner.onMyDraw();
		}
		
	}
	
	
	public void drawPoints(ArrayList<Point> points){

		if(mCanvas != null ){
			Paint fgPaintSel = new Paint();
			fgPaintSel.setARGB(255, 0, 0,0);
			fgPaintSel.setStyle(Style.STROKE);
			float width = getResources().getDisplayMetrics().density * 4;
			fgPaintSel.setStrokeWidth(width);
			
			fgPaintSel.setPathEffect(new DashPathEffect(new float[] {width,width,width,width}, 1));
			
			if(points != null){
				for (int i = 0; i < points.size()-1; i++) {
					Point currPoint = points.get(i);
					Point nextPoint = points.get(i + 1);
					mCanvas.drawLine(currPoint.x, currPoint.y, nextPoint.x, nextPoint.y, fgPaintSel);
					Log.d(LOG_TAG, "drawed line from " +currPoint.x + ":" + currPoint.y +  " to " + nextPoint.x+":"+nextPoint.y);
				}
			}
		}
	}


}
