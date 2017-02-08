package com.xiongfeng.headparallax;

import com.heima45.headparallax.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ParallaxListView listView;
	
	private View head;
	private ImageView parallaxImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ParallaxListView) findViewById(R.id.parallaxListView);
		listView.setOverScrollMode(AbsListView.OVER_SCROLL_NEVER);
		
		head = View.inflate(MainActivity.this, R.layout.layout_head, null);
		parallaxImageView = (ImageView) head.findViewById(R.id.parallaxImageView);
		parallaxImageView.setImageResource(R.drawable.parallax_img);
		
		head.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				listView.setParallaxImageView(parallaxImageView);
				
				head.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
		
		
		listView.addHeaderView(head);
		
		
		listView.setAdapter(new MyAdapter());
	}

	
	class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return 30;
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(MainActivity.this, R.layout.adapter_list, null);
			TextView textView = (TextView) view.findViewById(R.id.text);
			textView.setText(position+"");
			return view;
		}
		
	}
}
