package com.aaron;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GestureDetector gestureDetector;

    My2048View my2048View;

    TextView tvScore;

    Matrix matrix = new Matrix();

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrix.placeRandomNum();
        matrix.placeRandomNum();

        my2048View = (My2048View) findViewById(R.id.my2048View);
        tvScore = (TextView) findViewById(R.id.tvScore);

        for (int i = 0; i < Matrix.NUM; i ++)
            for (int j = 0; j < Matrix.NUM; j ++) {
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setText(matrix.getMat()[i][j] == 0 ? "" : String.valueOf(matrix.getMat()[i][j]));
                textView.setTextColor(getResources().getColor(R.color.colorAccent));
                textView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                textView.setTextSize(30);
                my2048View.addView(textView);
        }

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                int dir = 0;

                if (e2.getX() < e1.getX()  && Math.abs(velocityX) > Math.abs(velocityY)) {
                    dir = Matrix.GESTURE_LEFT;
                } else if (e2.getX() > e1.getX()  && Math.abs(velocityX) > Math.abs(velocityY)) {
                    dir = Matrix.GESTURE_RIGHT;
                } else if (e2.getY() < e1.getY()  && Math.abs(velocityX) < Math.abs(velocityY)) {
                    dir = Matrix.GESTURE_UP;
                } else if (e2.getY() > e1.getY()  && Math.abs(velocityX) < Math.abs(velocityY)) {
                    dir = Matrix.GESTURE_DOWN;
                }
                doResult(dir);
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void doResult(int dir) {
        boolean flag1 = matrix.fillGap(dir);
        int newScore = matrix.mergeSameNum(dir);
        if (flag1 || newScore != 0) {
            score += newScore;
            tvScore.setText("当前得分：" + score );
            matrix.fillGap(dir);
            matrix.placeRandomNum();
            refreshView();
        } else if (matrix.isFull()){
            Toast.makeText(MainActivity.this, "挑战失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void refreshView() {
        for (int i = 0; i < Matrix.NUM; i ++)
            for (int j = 0; j < Matrix.NUM; j ++) {
                TextView textView = (TextView) my2048View.getChildAt(i * Matrix.NUM + j);
                textView.setText(matrix.getMat()[i][j] == 0 ? "" : String.valueOf(matrix.getMat()[i][j]));
            }
    }
}
