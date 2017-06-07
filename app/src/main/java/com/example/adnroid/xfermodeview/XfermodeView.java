package com.example.adnroid.xfermodeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by adnroid on 17/6/7.
 */

public class XfermodeView extends View {
    private Paint mPaint;
    private Path mPath;
    private Bitmap mBgBitMap;
    private Bitmap mFgBitmap;
    private Canvas mCanvas;

    public XfermodeView(Context context) {
        this(context,null);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(50);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPath = new Path();
        mBgBitMap = BitmapFactory.decodeResource(getResources(),R.drawable.beauty);
        mFgBitmap = Bitmap.createBitmap(mBgBitMap.getWidth(),mBgBitMap.getHeight(), Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());

                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;

        }
        mCanvas.drawPath(mPath,mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBgBitMap,0,0,null);
        canvas.drawBitmap(mFgBitmap,0,0,null);
    }
}
