package zhaohg.crimson.timer;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.view.View;

import zhaohg.crimson.R;
import zhaohg.crimson.scene.Scene;

public class TimerScene extends Scene {

    public TimerScene(Context context, View view) {
        super(context, view);
    }

    @Override
    public void initScene() {
        int cx = width / 2;
        int cy = height / 2 - (int)(height * 0.01);
        int radius = (int)(Math.min(cx, cy) * 0.8);
        int diameter = radius * 2;

        TimerWidget timerWidget = new TimerWidget(context, view);
        timerWidget.setGeometry(cx - radius, cy - radius - (int)(height * 0.05), diameter, diameter);
        this.addChild(timerWidget);

        CurrentGoalWidget currentGoalWidget = new CurrentGoalWidget(context, view);
        currentGoalWidget.setGeometry(0, (int)(height * 0.85), width, (int)(height * 0.04));
        this.addChild(currentGoalWidget);

        ProgressWidget progressWidget = new ProgressWidget(context, view);
        progressWidget.setGeometry(0, (int)(height * 0.91), width, (int)(height * 0.06));
        this.addChild(progressWidget);
    }

    @Override
    public void selfDraw(Canvas canvas) {
        canvas.drawColor(ContextCompat.getColor(context, R.color.background_material_dark));
    }

}
