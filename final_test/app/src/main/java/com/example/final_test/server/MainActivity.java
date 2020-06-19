package com.example.final_test.server;

//import com.example.final_test.R;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.app.Activity;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
///**
// * 通过GET方式向服务器发送数据,通过GET方式上传数据主要适用于数
// * 据大小不超过2KB，且对安全性要求不高的情况下。
// */
//public class MainActivity extends Activity {
//    private EditText edtName,edtPwd;
//    private Button btnSend;
//    Handler handler=new Handler(){
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case SendDateToServer.SEND_SUCCESS:
//                    Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
//                    break;
//                case SendDateToServer.SEND_FAIL:
//                    Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
//                    break;
//
//                default:
//                    break;
//            }
//        };
//    };
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        edtName=(EditText)findViewById(R.id.edtName);
//        edtPwd=(EditText)findViewById(R.id.edtPwd);
//        btnSend=(Button)findViewById(R.id.btnSend);
//        btnSend.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                String name=edtName.getText().toString();
//                String pwd=edtPwd.getText().toString();
//                if (edtName.equals("")||edtPwd.equals("")) {
//                    Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
//                }else {
//                    new SendDateToServer(handler).SendDataToServer(name, pwd);
//                }
//            }
//        });
//    }
//}