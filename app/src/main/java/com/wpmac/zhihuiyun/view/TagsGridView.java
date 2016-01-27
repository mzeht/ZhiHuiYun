package com.wpmac.zhihuiyun.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class TagsGridView extends GridView {

	 public TagsGridView(Context context, AttributeSet attrs) {
		         super(context, attrs);  
		     }  
		   
		     public TagsGridView(Context context) {
		         super(context);  
		     }  
		   
		     public TagsGridView(Context context, AttributeSet attrs, int defStyle) {
		         super(context, attrs, defStyle);  
		     } 


	 @Override
	 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	 int expandSpec= MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
	                 MeasureSpec.AT_MOST);
	 super.onMeasure(widthMeasureSpec, expandSpec);
	 }



}
