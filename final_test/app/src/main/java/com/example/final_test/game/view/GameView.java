package com.example.final_test.game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.final_test.CommonData;
import com.example.final_test.game.bean.GridBean;
import com.example.final_test.game.bean.SnakeBean;
import com.example.final_test.game.Control;
import com.example.final_test.game.bean.GridBean;
import com.example.final_test.game.bean.PointBean;
import com.example.final_test.game.bean.SnakeBean;

import java.util.List;
import java.util.Random;

/**
 * 游戏的主界面
 */

public class GameView extends View {


    private boolean isFailed  = false;
    private Paint   paint     = new Paint();

    private GridBean gridBean;
    private SnakeBean snakeBean;

    //初始方向向上
    private Control control = Control.UP;

    public GameView(Context context) {
        super(context);
        init();

    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        gridBean = new GridBean();//创建格子对象，画格子时候使用
        snakeBean = new SnakeBean();//创建一个蛇对象。
        PointBean pointBean = new PointBean(gridBean.getGridSize()/2,gridBean.getGridSize()/2);

//        if (isBeEaten)generateCoordinate();

        //定义一个中心点 ，添加到蛇身上
        snakeBean.getSnake().add(pointBean);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(isFailed){
            String testString = "GameOver";
            //设置要绘制的字体
            Paint mPaint = new Paint();
            mPaint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
            mPaint.setTextSize(50);
            mPaint.setColor(Color.BLACK);
            //将文字用一个矩形包裹，进而算出文字的长和宽
            Rect bounds = new Rect();
            mPaint.getTextBounds(testString, 0, testString.length(), bounds);
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            //计算长宽
            int x = getMeasuredWidth() / 2 - bounds.width() / 2;
            int y = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
            canvas.drawText(testString, x, y, mPaint);
            return;
        }
        if (CommonData.isBeEaten){
            generateCoordinate();
            CommonData.isBeEaten=false;
        }
        drawFood(canvas);
        if (gridBean != null) {
            paint.setColor(Color.BLACK);
            drawGrid(canvas);
        }
        if (snakeBean != null) {
            paint.setColor(Color.GRAY);
            drawSnake(canvas);
        }
    }



    private void drawFood(Canvas canvas){
        Paint foodPaint = new Paint();
        int startX = gridBean.getOffset() + CommonData.foodX*gridBean.getGridWidth();
        int stopX  = startX + gridBean.getGridWidth();
        int startY = gridBean.getOffset() + CommonData.foodY*gridBean.getGridWidth();
        int stopY  = startY + +gridBean.getGridWidth();

        foodPaint.setColor(Color.RED);
        canvas.drawRect(startX, startY, stopX, stopY, foodPaint);
    }

    private void drawSnake(Canvas canvas) {
        List<PointBean> snake = snakeBean.getSnake();
        for (PointBean point : snake) {
            int startX = gridBean.getOffset() + gridBean.getGridWidth() * point.getX();
            int stopX = startX + gridBean.getGridWidth();
            int startY = gridBean.getOffset() + gridBean.getGridWidth() * point.getY();
            int stopY = startY + +gridBean.getGridWidth();
            canvas.drawRect(startX, startY, stopX, stopY, paint);
        }
    }

    private void drawGrid(Canvas canvas) {

        int startX = gridBean.getOffset();
        int stopX  = gridBean.getOffset()+gridBean.getGridSize()*gridBean.getGridWidth();
        int startY = gridBean.getOffset();
        int stopY  = gridBean.getOffset()+gridBean.getGridColumnSize()*gridBean.getGridWidth();
        //上边:startX：起始端点的X坐标。startY：起始端点的Y坐标。stopX：终止端点的X坐标。stopY：终止端点的Y坐标。
        canvas.drawLine(startX,startY,stopX,startY,paint);

        //左边left
        canvas.drawLine(startX,startY,startX,stopY,paint);

        //下边down
        canvas.drawLine(startX,stopY,stopX,stopY,paint);

        //右边right
        canvas.drawLine(stopX,startY,stopX,stopY,paint);
    }

    //生成坐标
    private void generateCoordinate(){
        Random random = new Random();
        CommonData.foodX = random.nextInt(gridBean.getGridSize()-1);
        CommonData.foodY = random.nextInt(gridBean.getGridColumnSize()-1);
    }


    //刷新界面，返回值判断程序输赢
    public boolean refreshView(){
        List<PointBean> pointList = snakeBean.getSnake();
        PointBean point = pointList.get(0);
//        LogUtil.i("point = " + point);
        PointBean pointNew = null;

        //移动
        if (control == Control.LEFT) {
            pointNew = new PointBean(point.getX() - 1, point.getY());
        } else if (control == Control.RIGHT) {
            pointNew = new PointBean(point.getX() + 1, point.getY());
        } else if (control == Control.UP) {
            pointNew = new PointBean(point.getX(), point.getY() - 1);
        } else if (control == Control.DOWN) {
            pointNew = new PointBean(point.getX(), point.getY() + 1);
        }

        //判断是否吃到了食物
        if (CommonData.foodX == point.getX() && CommonData.foodY == point.getY()){
            CommonData.isAdd = true;
            CommonData.scorer++;
            Toast.makeText(getContext(),CommonData.scorer+"",Toast.LENGTH_SHORT).show();
        }

        if (pointNew != null) {
            pointList.add(0, pointNew);
            if(!CommonData.isAdd){//添加蛇长
                pointList.remove(pointList.get(pointList.size() - 1));
            }
        }

        if(isFailed(point)){
            isFailed =true;
            invalidate();
            return true;
        }
        invalidate();//此处只是刷新页面,刷新页面会重新绘制
        return false;
    }


    //判断游戏是否结束
    private boolean isFailed( PointBean point){
        if (point.getY() == 0 && control == Control.UP ) {
            return true;
        } else if ( point.getX()  == 0 && control == Control.LEFT) {
            return true;
        } else if (point.getY() == gridBean.getGridColumnSize() - 1 && control == Control.DOWN  ) {
            return true;
        } else if (point.getX() == gridBean.getGridSize() - 1 && control == Control.RIGHT ) {
            return true;
        }
        return false;
    }



    int x;
    int y;
    @Override
    public boolean onTouchEvent(MotionEvent event) { //通过手势来改变蛇体运动方向
        int action = event.getAction()  & MotionEvent.ACTION_MASK;
        // TODO Auto-generated method stub
        if (action == KeyEvent.ACTION_DOWN) {
            //每次Down事件，都置为Null
            x = (int) (event.getX());
            y = (int) (event.getY());
        }
        if (action== KeyEvent.ACTION_UP) {
            //每次Down事件，都置为Null
            int x = (int) (event.getX());
            int y = (int) (event.getY());

//            新建一个滑动方向，
            Control control  = null ;
            // 滑动方向x轴大说明滑动方向为 左右
            if (Math.abs(x - this.x) > Math.abs(y - this.y)) {
                if (x > this.x) {
                    control = Control.RIGHT;
                }
                if (x < this.x) {
                    control = Control.LEFT;
                }
            }else{
                if (y < this.y) {
                    control = Control.UP;
                }
                if (y > this.y) {
                    control = Control.DOWN;
                }
            }

            if (this.control == Control.UP || this.control == Control.DOWN) {
                if(control==Control.UP ||Control.UP==Control.DOWN ){
                }else{
                    this.control = control;
                }
            } else if (this.control == Control.LEFT || this.control == Control.RIGHT) {
                if(control==Control.LEFT ||Control.UP==Control.RIGHT ){
                }else{
                    this.control = control;
                }
            }
        }
        //Log.e(TAG, "after adjust mSnakeDirection = " + mSnakeDirection);
        return super.onTouchEvent(event);
    }
}
