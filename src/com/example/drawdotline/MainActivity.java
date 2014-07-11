package com.example.drawdotline;

import java.util.ArrayList;

import com.example.drawdotline.MRelativeLayout.MyOnDrawListner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnGlobalLayoutListener, MyOnDrawListner {

	private static final String LOG_TAG = "MainActivity";
	
	private ImageView iv00;
	private ImageView iv01;
	private ImageView iv02;
	private ImageView iv03;

	private MRelativeLayout mainLayout;

	private ArrayList<Point> points;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		 // inflate your main layout here (use RelativeLayout or whatever your root ViewGroup type is
	     mainLayout = (MRelativeLayout) this.getLayoutInflater().inflate(R.layout.activity_main, null); 
	    
	     // set a global layout listener which will be called when the layout pass is completed and the view is drawn
	     mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
	     mainLayout.setMyOnDrawLisnter(this);
	    /* new ViewTreeObserver.OnGlobalLayoutListener() {
	          public void onGlobalLayout() {
	               //Remove the listener before proceeding
	               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
	                    mainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
	               } else {
	                    mainLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
	               }

	               // measure your views here
	          }
	     }
	 );*/

	 setContentView(mainLayout);
		initViews();
	}

	private void initViews() {

		iv00 = (ImageView) findViewById(R.id.iv00);
		iv01 = (ImageView) findViewById(R.id.iv01);
		iv02 = (ImageView) findViewById(R.id.iv02);
		iv03 = (ImageView) findViewById(R.id.iv03);
		
	}

	private void getDimens() {
		
		points = new ArrayList<Point>();
		points.add(getCenterPointDimens(iv00));
		points.add(getCenterPointDimens(iv01));
		points.add(getCenterPointDimens(iv02));
		points.add(getCenterPointDimens(iv03));
		
	}

	private Point getCenterPointDimens(ImageView view) {
		// TODO Auto-generated method stub
		
		int top = view.getTop();
		int left = view.getLeft();
		int width = view.getWidth();
		int height = view.getHeight();

		int y = top + height/2;
		int x = left + width/2;
		
		Log.d(LOG_TAG, "x =" +x + "y = " + y);
		return new Point(x, y);
		
		
/*		
		Rect rectf = new Rect();
		view.getLocalVisibleRect(rectf);
		
		int top = rectf.top;
		int bottom = rectf.bottom;
		int left = rectf.left;
		int right = rectf.right;
		int centerX = rectf.centerX();
		int centerY = rectf.centerY();
		
		
		Log.d(LOG_TAG, "top" + top);
		Log.d(LOG_TAG, "bottom" + bottom);
		Log.d(LOG_TAG, "left" + left);
		Log.d(LOG_TAG, "right" + right);
		
		Log.d(LOG_TAG, "centerX " + centerX + " centerY " + centerY);	*/
		
		
	}

	
	@SuppressLint("NewApi")
	@Override
	public void onGlobalLayout() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
       } else {
            mainLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
       }
		
//		showDimens();
		getDimens();
		
	}

	@Override
	public void onMyDraw() {
		
		mainLayout.drawPoints(points);
	}

	
}
