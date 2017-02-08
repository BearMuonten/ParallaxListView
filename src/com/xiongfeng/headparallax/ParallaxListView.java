package com.xiongfeng.headparallax;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;

public class ParallaxListView extends ListView{
private String tag = ParallaxListView.class.getSimpleName();
	public ParallaxListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	private ImageView parallaxImageView;
	private int orginalHeight;
	private int maxHeight;
	public void setParallaxImageView(ImageView parallaxImageView) {
		this.parallaxImageView = parallaxImageView;
		
		orginalHeight = parallaxImageView.getHeight();
		maxHeight = parallaxImageView.getDrawable().getIntrinsicHeight();
		Log.e(tag, "orginalHeight: "+orginalHeight);
	}
	
	//listview滑动到头时�?调用
	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
			int scrollY, int scrollRangeX, int scrollRangeY,
			int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
		//deltaY: y方向手指滑动的距�?正：底部到头  负：顶部到头
		//scrollY：y方向滚动的距�?
		//maxOverScrollY：最大可滑动的距�?
		//isTouchEvent: 是否是手指拖动到头， true：是    false:惯�?滑动到头
		Log.e(tag, "deltaY: "+deltaY +"  isTouchEvent:"+isTouchEvent);
		
		if(deltaY<0 && isTouchEvent){
			parallaxImageView.getLayoutParams().height = parallaxImageView.getHeight() - deltaY/3;
			if(parallaxImageView.getLayoutParams().height>maxHeight)parallaxImageView.getLayoutParams().height = maxHeight;
			parallaxImageView.requestLayout();
		}
		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
				scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
	}

	@Override
		public boolean onTouchEvent(MotionEvent ev) {
			switch (ev.getAction()) {
			case MotionEvent.ACTION_UP:
				ResetHeightAnimation animation = new ResetHeightAnimation(parallaxImageView, orginalHeight);
				startAnimation(animation);
				break;
			}
			return super.onTouchEvent(ev);
		}

	
	
}
