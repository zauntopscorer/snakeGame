package com.example.final_test.game;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_test.CommonData;
import com.example.final_test.game.bean.GridBean;
import com.example.final_test.game.view.GameView;

public class MainActivity extends AppCompatActivity {
    private GridBean gridBean;

    private static final int WHAT_REFRESH = 200;
    private int time = 200;// 每次刷新时间间隔
    private GameView gameView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(WHAT_REFRESH == msg.what){
                if(CommonData.isAdd){
                    CommonData.isBeEaten=true;
                    if(time - 10 >0){
                        time = time -10;
                    }
                    CommonData.scorer++;
                    CommonData.isAdd = false;
                }
                boolean isFailed = gameView.refreshView();
                if(!isFailed){//没有结束继续发线程
                    sendControlMessage();
                }else{
                    // 结束告诉程序你输了
//                    Toast.makeText(MainActivity.this,"GameOver",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        gameView.setClickable(true);
        setContentView(gameView);
        sendControlMessage();
    }

    private void sendControlMessage(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(WHAT_REFRESH);
            }
        },time);
    }
}
