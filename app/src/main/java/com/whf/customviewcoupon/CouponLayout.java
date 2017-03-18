package com.whf.customviewcoupon;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 自定义优惠券布局（四周具有圆状锯齿）
 * Created by WHF on 2017/3/18.
 */

public class CouponLayout extends LinearLayout {

    /**
     * 小圆的半径
     */
    private int mRadio;

    /**
     * 小圆之间的间隔
     */
    private int mSpace;

    /**
     * 小圆的个数
     */
    private int mNum;

    /**
     * 画完所有圆和边距剩余的个数
     */
    private float mRemain;

    public CouponLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attrArray = context.obtainStyledAttributes(attrs,R.styleable.CouponLayout);
        mRadio = attrArray.getDimensionPixelSize(R.styleable.CouponLayout_sawtoothRadius, 0);
        mSpace = attrArray.getDimensionPixelSize(R.styleable.CouponLayout_sawtoothSpace, 0);
        attrArray.recycle();
    }

    public CouponLayout(Context context) {
        super(context);
    }

    /**
     * 计算小圆的个数： int num = (int)(width-mSpace)/(2*mRadio+mSpace)
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (mRadio == 0) {
            mNum = 0;
        } else {
            if (mRemain == 0) {
                mRemain = (w - mSpace) % (2 * mRadio + mSpace);
            }
            mNum = (w - mSpace) / (2 * mRadio + mSpace);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        for (int i = 0; i < mNum; i++) {
            float x = mSpace + mRadio + mRemain /2 +((mSpace + mRadio * 2) * i);
            canvas.drawCircle(x, 0, mRadio, paint);//上边
            canvas.drawCircle(x, getHeight(), mRadio, paint);//下边
        }
        super.onDraw(canvas);
    }
}
