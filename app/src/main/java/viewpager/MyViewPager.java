package viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by steven on 2017/7/3.
 * 用于关闭Activity中的ViewPager的左右滑动功能
 */

public class MyViewPager extends ViewPager {
    private  boolean scrollble=true;
    public MyViewPager(Context context){
        super(context);
    }
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);


    }
    @Override
    public boolean onTouchEvent(MotionEvent ev){
            if(!scrollble){
                return false;
            }
        return  super.onTouchEvent(ev);
    }

    public boolean isScrollble(){
        return scrollble;
    }

    public void setScrollble(boolean scrollble){
        this.scrollble=scrollble;
    }

}
