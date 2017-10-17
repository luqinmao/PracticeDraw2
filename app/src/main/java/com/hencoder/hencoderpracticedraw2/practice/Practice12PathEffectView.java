package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect  //圆角拐角
        paint.setPathEffect(new CornerPathEffect(20));
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect //偏离
        paint.setPathEffect(new DiscretePathEffect(20,5));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect //虚线
        paint.setPathEffect(new DashPathEffect(new float[]{20, 10, 5, 10}, 0));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect //用一个 Path 来绘制虚线
        Path shade = new Path();
//        shade.addCircle(10,10,20, Path.Direction.CCW);
        shade.lineTo(20, -30);
        shade.lineTo(40, 0);
        shade.close();

        paint.setPathEffect(new PathDashPathEffect(shade,40,5, PathDashPathEffect.Style.TRANSLATE));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect
        DiscretePathEffect discretePathEffect = new DiscretePathEffect(20,5);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        paint.setPathEffect(new SumPathEffect(discretePathEffect,dashPathEffect));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect
        DiscretePathEffect discretePathEffect2 = new DiscretePathEffect(20,5);
        DashPathEffect dashPathEffect2 = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        paint.setPathEffect(new ComposePathEffect(dashPathEffect2,discretePathEffect2));
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
