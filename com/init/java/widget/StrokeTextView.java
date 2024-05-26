package com.init.weblica;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
import android.graphics.Typeface;

public class StrokeTextView extends TextView {
    private int strokeColor = Color.WHITE;
    private float strokeWidth = 5f;
    private Paint strokePaint;
    private Typeface typeface;

    public StrokeTextView(Context context) {
        super(context);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Get text and setup stroke paint
        String text = getText().toString();
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setColor(strokeColor);
        strokePaint.setTextSize(getTextSize());
        strokePaint.setTypeface(typeface != null ? typeface : getTypeface());

        // Calculate x and y positions
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(typeface != null ? typeface : getTypeface());

        int x = (getWidth() - (int) paint.measureText(text)) / 2;
        int y = (int) ((getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));

        // Draw the stroke
        canvas.drawText(text, x, y, strokePaint);

        // Draw the text itself
        canvas.drawText(text, x, y, paint);
    }

    public void setStrokeColor(int color) {
        strokeColor = color;
        invalidate();
    }

    public void setStrokeWidth(float width) {
        strokeWidth = width;
        invalidate();
    }

    public void setTypeface(Typeface tf) {
        typeface = tf;
        invalidate();
    }
}
