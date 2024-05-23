package com.jason.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class CustomImageView extends AppCompatImageView {
    private float progress = 0f; // 0-1f 的透明度进度值
    private Paint paint;
    private Drawable drawable;

    public CustomImageView(Context context) {
        super(context);
        init();
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawable == null) {
            drawable = getDrawable();
        }

        if (drawable != null) {
            int width = getWidth();
            int height = getHeight();
            int halfHeight = height / 2;

            // 绘制原始图片
            drawable.setBounds(0, 0, width, height);
            drawable.draw(canvas);

            if (progress > 0) {
                // 设置透明度遮罩，从中间向下
                int alpha = (int) (progress * 255);
                paint.setAlpha(alpha);
                canvas.drawRect(0, halfHeight, width, height, paint);
            }
        }
    }

    public void setProgress(float progress) {
        if (progress < 0) {
            this.progress = 0;
        } else if (progress > 1) {
            this.progress = 1;
        } else {
            this.progress = progress;
        }
        invalidate();
    }
}