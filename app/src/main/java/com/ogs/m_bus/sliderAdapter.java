package com.ogs.m_bus;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

/**
 * Created by admin on 3/23/2018.
 */

public class sliderAdapter extends PagerAdapter{
    Context context;
    LayoutInflater layoutInflater;

    public sliderAdapter(Context context) {
        this.context = context;
    }
    //Arrays
    public int[] slide_images={
            R.drawable.buspass,
            R.drawable.busticket
    };
    public String[] slide_headings={"BUS PASS","BUS TICKET"};

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout)object;
    }
    @Override
    public Object instantiateItem(ViewGroup container ,int position){
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageButton slideImage= (ImageButton)view.findViewById(R.id.buspassimg);
        TextView buspass = (TextView)view.findViewById(R.id.buspass);
        slideImage.setImageResource(slide_images[position]);
        buspass.setText(slide_headings[position]);

        container.addView(view);


        return view;
    }
    @Override
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((RelativeLayout)object);
    }
}
